package com.eason.live.zb.service.impl;

import com.eason.live.zb.config.LiveConfig;
import com.eason.live.zb.service.ILiveUrlService;
import com.eason.live.zb.utils.UrlUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/live")
public class LiveUrlServiceImpl implements ILiveUrlService {
    @Autowired
    private LiveConfig liveConfig;

    @RequestMapping(value = "/getPushUrl",method = RequestMethod.GET)
    @Override
    public String getPushUrl(String roomId) {
        String targetUrl=liveConfig.getPushUrl().get(liveConfig.getType());
        Date date= DateUtils.addDays(new Date(),1);
        Long txTime=date.getTime()/1000;
        String param= UrlUtils.getSafeUrl(liveConfig.getKey(),roomId,txTime);
        String zbCode=liveConfig.getBizid()+"_"+roomId+"_"+txTime;
        targetUrl=String.format(targetUrl,liveConfig.getBizid(),zbCode);
        return targetUrl+"?"+param;
    }

    @Override
    public String getPlayUrl(String roomId) {
        return null;
    }
}
