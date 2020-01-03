package com.eason.transfer.openapi.zb.api.room;


import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.core.sdk.zb.IRoomService;
import com.eason.transfer.openapi.core.sdk.zb.model.*;
import com.eason.transfer.openapi.zb.api.entity.*;
import com.eason.transfer.openapi.zb.api.mapper.*;
import com.eason.transfer.openapi.zb.api.room.model.RRoom;
import com.eason.transfer.openapi.zb.api.zhubo.model.RZhubo;
import com.eason.transfer.openapi.zb.utils.FtpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.api.condition.Conditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private RedissonClient redisson;
    @Autowired
    private ZbZhuboPoMapper zbZhuboPoMapper;
    @Autowired
    private UserPoMapper userPoMapper;
    @Autowired
    private ZbRoomReportMapper roomReportMapper;
    @Autowired
    private FtpClientUtils ftpClientUtils;
    @Autowired
    private RelationFriendsPoMapper relationFriendsPoMapper;
    @Override
    public RoomFindAllResponse findAll(RoomFindAllRequest request) throws Exception {
        RoomFindAllResponse response=new RoomFindAllResponse();
        List<RoomVo> list=new ArrayList<>();
        RLiveObjectService liveObjectService=redisson.getLiveObjectService();
        Collection<RRoom> roomCollection=liveObjectService.find(RRoom.class, Conditions.eq("status",0));
        roomCollection.forEach(rRoom -> {
            RoomVo roomVo=new RoomVo();
            roomVo.setPlanSeqNo(rRoom.getZbSeqNo());
            roomVo.setId(rRoom.getId());
            roomVo.setNickName(rRoom.getRZhubo().getNickName());
            roomVo.setAvatar(rRoom.getRZhubo().getAvatar());
            roomVo.setRoomName(rRoom.getRoomName());
            roomVo.setRoomBgImage(rRoom.getRoomBgImage());
            roomVo.setUsername(rRoom.getRZhubo().getUsername());
            roomVo.setStartTime(DateFormatUtils.format(rRoom.getStartTime().getTime(),DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
            roomVo.setUserCount(rRoom.getUserList().size());
            roomVo.setGiftCount(redisson.getAtomicLong("giftCount_"+rRoom.getId()).get());
            roomVo.setNewFans(redisson.getAtomicLong("newFans_"+rRoom.getId()).get());
            roomVo.setViewCount(redisson.getAtomicLong("viewCount_"+rRoom.getId()).get());
            list.add(roomVo);
        });
        response.setList(list);
        return response;
    }

    @Override
    public RoomCreateResponse create(RoomCreateRequest request) throws Exception {
        RoomCreateResponse response=new RoomCreateResponse();
        String code = null;
        String result = null;
        if(StringUtils.isEmpty(request.getImUrl())){
            code="imUrl";
            result ="聊天室地址不能为空";
        }else if(StringUtils.isEmpty(request.getLiveUrl())){
            code="liveUrl";
            result ="流媒体地址不能为空";
        }
        ZbZhuboPo zhuboPo=zbZhuboPoMapper.selectByPrimaryKey(Long.parseLong(request.getUserId()));
        if(zhuboPo==null){
            code="zhubo";
            result ="主播不存在，请联系管理员";
        }
        if(zhuboPo.getStatus()==1){
            code="zhubo";
            result ="直播间已经被封，请联系管理员";
        }
        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        RLiveObjectService liveObjectService=redisson.getLiveObjectService();
        RRoom rRoom=new RRoom();
        rRoom.setId(Long.parseLong(request.getUserId()));

        rRoom.setZbSeqNo(DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATETIME_FORMAT.getPattern())+"_"+request.getUserId());
        rRoom.setRoomName(zhuboPo.getRoomname());
        rRoom.setRoomBgImage(zhuboPo.getRoombgimage());
        rRoom.setImUrl(request.getImUrl());
        rRoom.setLiveUrl(request.getLiveUrl());
        rRoom.setUserList(redisson.getList("userList_"+rRoom.getId()));
        rRoom.setGiftCount(redisson.getAtomicLong("giftCount_"+rRoom.getId()));
        rRoom.setNewFans(redisson.getAtomicLong("newFans_"+rRoom.getId()));
        rRoom.setViewCount(redisson.getAtomicLong("viewCount_"+rRoom.getId()));
        rRoom.setStartTime(new Timestamp(System.currentTimeMillis()));

        UserPo userPo=userPoMapper.selectByPrimaryKey(zhuboPo.getId());
        RZhubo rZhubo=new RZhubo();
        rZhubo.setId(request.getUserId());
        liveObjectService.attach(rZhubo);
        rZhubo.setUsername(userPo.getUsername());
        rZhubo.setNickName(userPo.getNickName());
        rZhubo.setAvatar(userPo.getAvatar());
//        rZhubo.setFansNum(redisson.getAtomicLong("fansNum_"+rRoom.getId()));
        rZhubo.setIsFans(Boolean.FALSE);
        if(liveObjectService.isExists(rZhubo)){
            rZhubo=liveObjectService.merge(rZhubo);
        }else{
            rZhubo=liveObjectService.persist(rZhubo);
        }

        rRoom.setStatus(zhuboPo.getStatus()+0);
        rRoom.setRZhubo(rZhubo);

        if(liveObjectService.isExists(rRoom)){
            liveObjectService.merge(rRoom);
        }else{
            liveObjectService.persist(rRoom);
        }

        response.setResult("直播间创建成功");
        response.setRoomId(rRoom.getId());
        response.setRoomName(rRoom.getRoomName());
        response.setRoomBgImage(rRoom.getRoomBgImage());
        return response;
    }

    @Override
    public RoomGetInfoResponse getInfo(RoomGetInfoRequest request) throws Exception {
        RoomGetInfoResponse response=new RoomGetInfoResponse();
        ZbZhuboPo zhuboPo=zbZhuboPoMapper.selectByPrimaryKey(Long.parseLong(request.getUserId()));
        String code = null;
        String result = null;
        if(zhuboPo==null){
            code="zhubo";
            response.addErrInfo(code, "主播不存在，请联系管理员;", null);
            response.setSuccessCount(0);
            return response;
        }
        if(zhuboPo.getStatus()==1){
            code="zhubo";
            response.addErrInfo(code, "直播间已经被封，请联系管理员;", null);
            response.setSuccessCount(0);
            return response;
        }
        response.setResult("直播间信息获取成功");
        response.setRoomId(zhuboPo.getId()+"");
        response.setRoomName(zhuboPo.getRoomname());
        response.setRoomBgImage(zhuboPo.getRoombgimage());
        return response;
    }

    @Override
    public RoomUpdateResponse update(RoomUpdateRequest request) throws Exception {
        RoomUpdateResponse response=new RoomUpdateResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getRoomName())) {
            code ="roomName";
            result ="直播间名称不能为空";
        } else if (request.getRoomBgImage()==null) {
            code ="roomBgImage";
            result ="直播间背景图不能为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        FileItem fileImg=request.getRoomBgImage();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileImg.getContent());

        String path=request.getAppKey()+"/room";
        String host=ftpClientUtils.uploadFile(byteArrayInputStream,path,fileImg.getFileName()+"_"+request.getUserId());
        String pic = host +"/"+path+"/"+fileImg.getFileName()+"_"+request.getUserId();
        ZbZhuboPo zhuboPo=new ZbZhuboPo();
        zhuboPo.setId(Long.parseLong(request.getUserId()));
        zhuboPo.setRoomname(request.getRoomName());
        zhuboPo.setRoombgimage(pic);
        zhuboPo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        zhuboPo.setUpdateBy(request.getUserId());
        zbZhuboPoMapper.updateByPrimaryKey(zhuboPo);
        response.setResult("直播间信息更新成功");
        return null;
    }

    @Override
    public RoomDestoryResponse destory(RoomDestoryRequest request) throws Exception {
        RoomDestoryResponse response=new RoomDestoryResponse();
        RLiveObjectService liveObjectService=redisson.getLiveObjectService();
        RRoom rRoom=liveObjectService.get(RRoom.class,Long.parseLong(request.getUserId()));
        if(rRoom==null){
            response.setResult("直播间不存在");
            return response;
        }
        Boolean flag=liveObjectService.delete(RRoom.class,Long.parseLong(request.getUserId()));
        if(!flag){
            response.setResult("直播间销毁失败");
            return response;
        }
        ZbRoomReport roomReport=new ZbRoomReport();
        roomReport.setRoomname(rRoom.getRoomName());
        roomReport.setRoomid(rRoom.getId());
        roomReport.setZbPlanSeqno(rRoom.getZbSeqNo());
        roomReport.setViewCount(redisson.getAtomicLong("viewCount_"+rRoom.getId()).get());
        roomReport.setNewFans(redisson.getAtomicLong("newFans_"+rRoom.getId()).get());
        roomReport.setGiftCount(redisson.getAtomicLong("giftCount_"+rRoom.getId()).get());
        Long time=System.currentTimeMillis()-rRoom.getStartTime().getTime();
        roomReport.setLiveTimeCount(DateFormatUtils.format(time,DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern()));
        roomReport.setStartTime(rRoom.getStartTime());
        roomReport.setStopTime(new Date());
        this.roomReportMapper.insert(roomReport);

        response.setResult("直播间销毁成功");
        return response;
    }

    @Override
    public RelationFriendsResponse relationFriends(RelationFriendsRequest request) throws Exception {
        RelationFriendsResponse response=new RelationFriendsResponse();
        RLiveObjectService liveObjectService=redisson.getLiveObjectService();
        RRoom rRoom=liveObjectService.get(RRoom.class,Long.parseLong(request.getUserId()));
        if(rRoom==null){
            response.setResult("直播间不存在");
            return response;
        }
        RelationFriendsPo relationFriendsPo=new RelationFriendsPo();
        relationFriendsPo.setUserId(request.getUserId());
        relationFriendsPo.setRelationTime(new Date());
        relationFriendsPo.setRelationUserid(request.getRelationUserId());
        relationFriendsPoMapper.insert(relationFriendsPo);
        response.setResult("关注成功");
        return response;
    }

}
