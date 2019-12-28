package com.eason.transfer.openapi.zb.api.app;


import com.eason.transfer.openapi.core.sdk.chess.IIndexService;
import com.eason.transfer.openapi.core.sdk.chess.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class IndexListImpl implements IIndexService {


    @Override
    public IndexResponse getIndexList(ReporttIndexList reporttIndexList) {
        IndexResponse indexResponse = new IndexResponse();
        try {
            // 1. 参数验证
            String code = null;
            String result = null;
            if (StringUtils.isBlank(reporttIndexList.getCategory())) {
                code = "category";
                result = "category不能为空";
            } else if (StringUtils.isBlank(String.valueOf(reporttIndexList.getPage()))) {
                code = "page";
                result = "page不能为空";
            } else if (StringUtils.isBlank(String.valueOf(reporttIndexList.getPosition()))) {
                code = "position";
                result = "position不能为空";
            } else if (StringUtils.isBlank(String.valueOf(reporttIndexList.getPageSize()))) {
                code = "pageSize";
                result = "pageSize不能为空";
            }
            if (StringUtils.isNotEmpty(result)) {
                indexResponse.addErrInfo(code, result, null);
                indexResponse.setSuccessCount(0);
                return indexResponse;
            }
            indexResponse.setTotal((long) 40);
            List<IndexListVo> list = new ArrayList<>();
            IndexListVo indexListVo = new IndexListVo();
            indexListVo.setRows(1);
            indexListVo.setRoomId(5001);
            indexListVo.setZbId(9);
            indexListVo.setRoomId(9);
            indexListVo.setZbNickName("香香");
            indexListVo.setZbHeadImg("呜呜");
            indexListVo.setZbId(10);
            indexListVo.setRoomTitle("开心");
            indexListVo.setRoomType("normal");
            indexListVo.setOnlineUser(10);
            indexListVo.setMachineUser(3);
            indexListVo.setViewCount(2);
            indexListVo.setWatchCount(3);
            indexListVo.setRoomBackgroundImg("sadsaffa");
            indexListVo.setRoomStatus(1);
            indexListVo.setStartTime(Timestamp.valueOf("2019-03-04 12:23:30"));
            indexListVo.setGameIcon("edsfsdfs");
            indexListVo.setPlayUrl("http://www.svnchina.com/");
            indexListVo.setIsCharge(1);
            indexListVo.setRoomPlanId(1);
            list.add(indexListVo);
            indexResponse.setList(list);

        } catch (Exception e) {
            log.error("获取信息失败:" + e);
            e.printStackTrace();
        }
        return indexResponse;
    }

    @Override
    public BannerResponse getBannerList(Bannerrequest request) {
        BannerResponse bannerResponse=new BannerResponse();
        try{
            // 1. 参数验证
            String code = null;
            String result = null;
            if (StringUtils.isBlank(request.getCategory())) {
                code = "category";
                result = "category不能为空";
            }
            if (StringUtils.isNotEmpty(result)) {
                bannerResponse.addErrInfo(code, result, null);
                bannerResponse.setSuccessCount(0);
                return bannerResponse;
            }
            List<BannerResponseVo> list=new ArrayList<>();
            BannerResponseVo bannerResponseVo=new BannerResponseVo();
            bannerResponseVo.setUrl("http://www.svnchina.com/");
            bannerResponseVo.setId(12312);
            bannerResponseVo.setThumb_img_url("http://www.svnchina.com/");
            bannerResponseVo.setTitle("捕鱼达人");
            bannerResponseVo.setType(1);
            list.add(bannerResponseVo);
            bannerResponse.setTotal((long) 52);
            bannerResponse.setList(list);
        }catch (Exception e){
            log.error("获取信息失败:" + e);
            e.printStackTrace();
        }
        return bannerResponse;
    }

    @Override
    public MsgNotificationResponse getMsgNotification(MsgNotificationRequest request){
        MsgNotificationResponse msgNotificationResponse=new MsgNotificationResponse();
        try{
            // 1. 参数验证
            String code = null;
            String result = null;
            if (StringUtils.isBlank(request.getCategory())) {
                code = "category";
                result = "category不能为空";
            }
            if (StringUtils.isNotEmpty(result)) {
                msgNotificationResponse.addErrInfo(code, result, null);
                msgNotificationResponse.setSuccessCount(0);
                return msgNotificationResponse;
            }
            MsgNotificationResponseVo msgNotificationResponseVo=new MsgNotificationResponseVo();
            msgNotificationResponseVo.setId(112);
            msgNotificationResponseVo.setTitle("今晚休息.......");
            msgNotificationResponseVo.setUrl("http://www.svnchina.com/");
            List<MsgNotificationResponseVo> list=new ArrayList<>();
            list.add(msgNotificationResponseVo);
            msgNotificationResponse.setTotal((long) 20);
            msgNotificationResponse.setList(list);
        }catch (Exception e){
            log.error("获取信息失败:" + e);
            e.printStackTrace();
        }
        return msgNotificationResponse;
    }


}
