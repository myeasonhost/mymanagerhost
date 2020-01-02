package com.eason.transfer.openapi.zb.api.zhubo;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.core.sdk.zb.IZhuboService;
import com.eason.transfer.openapi.core.sdk.zb.model.*;
import com.eason.transfer.openapi.zb.api.entity.ZbZhuboPo;
import com.eason.transfer.openapi.zb.api.mapper.UserPoMapper;
import com.eason.transfer.openapi.zb.api.mapper.ZbZhuboPoMapper;
import com.eason.transfer.openapi.zb.api.room.RoomServiceImpl;
import com.eason.transfer.openapi.zb.im.dto.ChatObject;
import com.eason.transfer.openapi.zb.im.listener.SocketIOListener;
import com.eason.transfer.openapi.zb.utils.FtpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

@RestController
@Slf4j
public class ZhuboServiceImpl implements IZhuboService {
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
    @Autowired
    private RoomServiceImpl roomServiceImpl;
    @Autowired
    private SocketIOListener socketIOListener;


    @Override
    public ZhuboStartResponse start(ZhuboStartRequest request) throws Exception {
        ZhuboStartResponse response=new ZhuboStartResponse();
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
        String imUrl="/room/"+request.getUserId();

        RoomCreateRequest roomCreateRequest=new RoomCreateRequest();
        BeanUtils.copyProperties(request,roomCreateRequest);
        roomCreateRequest.setImUrl(imUrl);
        roomCreateRequest.setLiveUrl("rtmp://live.hkstv.hk.lxdns.com/live/hks");
        this.roomServiceImpl.create(roomCreateRequest);

        final SocketIONamespace chat1namespace = socketIOServer.addNamespace(imUrl);
        chat1namespace.addConnectListener(socketIOListener);
        chat1namespace.addDisconnectListener(socketIOListener);
        chat1namespace.addEventListener("message", ChatObject.class,socketIOListener);
        response.setResult("直播开播成功");
        return response;
    }

    @Override
    public ZhuboStopResponse stop(ZhuboStopRequest request) throws Exception {
        ZhuboStopResponse response1=new ZhuboStopResponse();
        socketIOServer.removeNamespace("/room_"+request.getUserId());
        RoomDestoryRequest roomDestoryRequest=new RoomDestoryRequest();
        BeanUtils.copyProperties(request,roomDestoryRequest);
        RoomDestoryResponse response2=this.roomServiceImpl.destory(roomDestoryRequest);
        response1.setResult(response2.getResult());
        return response1;
    }

    @Override
    public ZhuboCreateResponse create(ZhuboCreateRequest request) throws Exception {
        ZhuboCreateResponse response=new ZhuboCreateResponse();
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
        ZbZhuboPo zhuboPo=zbZhuboPoMapper.selectByPrimaryKey(Long.parseLong(request.getUserId()));
        if(zhuboPo==null){
            zhuboPo=new ZbZhuboPo();
            zhuboPo.setId(Long.parseLong(request.getUserId()));
            zhuboPo.setRoomname(request.getRoomName());

            FileItem fileImg=request.getRoomBgImage();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileImg.getContent());
            String path=request.getAppKey()+"/room";
            String host=ftpClientUtils.uploadFile(byteArrayInputStream,path,fileImg.getFileName()+"_"+request.getUserId());
            String pic = host +"/"+path+"/"+fileImg.getFileName()+"_"+request.getUserId();

            zhuboPo.setRoombgimage(pic);
            zhuboPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            zhuboPo.setCreateBy(request.getUserId());
            zhuboPo.setStatus(Byte.valueOf("0"));
            this.zbZhuboPoMapper.insert(zhuboPo);
            response.setResult("主播信息创建成功");
        }else{
            zhuboPo.setRoomname(request.getRoomName());

            FileItem fileImg=request.getRoomBgImage();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileImg.getContent());
            String path=request.getAppKey()+"/room";
            String host=ftpClientUtils.uploadFile(byteArrayInputStream,path,fileImg.getFileName()+"_"+request.getUserId());
            String pic = host +"/"+path+"/"+fileImg.getFileName()+"_"+request.getUserId();

            zhuboPo.setRoombgimage(pic);
            zhuboPo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            zhuboPo.setUpdateBy(request.getUserId());
            this.zbZhuboPoMapper.updateByPrimaryKey(zhuboPo);
            response.setResult("主播信息更新成功");
        }
        return response;
    }
}
