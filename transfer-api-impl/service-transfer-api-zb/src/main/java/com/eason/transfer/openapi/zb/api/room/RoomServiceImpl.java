package com.eason.transfer.openapi.zb.api.room;


import com.corundumstudio.socketio.SocketIOServer;
import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.core.sdk.zb.IRoomService;
import com.eason.transfer.openapi.core.sdk.zb.model.*;
import com.eason.transfer.openapi.zb.api.entity.UserPo;
import com.eason.transfer.openapi.zb.api.entity.ZbZhuboPo;
import com.eason.transfer.openapi.zb.api.mapper.UserPoMapper;
import com.eason.transfer.openapi.zb.api.mapper.ZbZhuboPoMapper;
import com.eason.transfer.openapi.zb.api.room.model.RRoom;
import com.eason.transfer.openapi.zb.api.zhubo.model.RZhubo;
import com.eason.transfer.openapi.zb.utils.FtpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

@RestController
@Slf4j
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private RedissonClient redisson;
    @Autowired
    private SocketIOServer socketIOServer;
    @Autowired
    private ZbZhuboPoMapper zbZhuboPoMapper;
    @Autowired
    private UserPoMapper userPoMapper;
    @Autowired
    private FtpClientUtils ftpClientUtils;

    @Override
    public RoomCreateResponse create(RoomCreateRequest request) throws Exception {
        RoomCreateResponse response=new RoomCreateResponse();
        String code = null;
        String result = null;
        ZbZhuboPo zhuboPo=zbZhuboPoMapper.selectByPrimaryKey(Long.parseLong(request.getUserId()));
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
        RLiveObjectService liveObjectService=redisson.getLiveObjectService();
        RRoom rRoom = liveObjectService.get(RRoom.class, request.getUserId());
        if(rRoom!=null){
            response.setResult("直播间已经存在");
            response.setRoomId(rRoom.getId());
            response.setRoomName(rRoom.getRoomName());
            response.setRoomBgImage(rRoom.getRoomBgImage());
            return response;
        }
        rRoom=new RRoom();
        rRoom.setId(request.getUserId());
        rRoom.setRoomName(zhuboPo.getRoomname());
        rRoom.setRoomBgImage(zhuboPo.getRoombgimage());
        rRoom.setStartTime(new Timestamp(System.currentTimeMillis()));

        UserPo userPo=userPoMapper.selectByPrimaryKey(zhuboPo.getId());
        RZhubo rZhubo=new RZhubo();
        rZhubo.setId(request.getUserId());
        rZhubo.setUsername(userPo.getUsername());
        rZhubo.setNickName(userPo.getNickName());
        rZhubo.setAvatar(userPo.getAvatar());
        rZhubo.setFansNum(0);
        rZhubo.setIsFans(Boolean.FALSE);

        rRoom.setRZhubo(rZhubo);
        liveObjectService.persist(rRoom);

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
        return null;
    }
}
