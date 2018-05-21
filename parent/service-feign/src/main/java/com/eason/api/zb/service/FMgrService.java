package com.eason.api.zb.service;

import com.eason.api.zb.IMgrService;
import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.service.impl.FMgrServiceImpl;
import com.eason.api.zb.vo.zhubo.MgrStartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-api-zb-impl",fallback = FMgrServiceImpl.class )
public interface FMgrService extends IMgrService {
    @RequestMapping(value = "/mgr/{zbId}/createRoom", method = RequestMethod.POST)
    StartPlayResponseVo createRoom(@RequestParam(value = "zbId") Integer zbId, @RequestParam(value = "token") String token, @RequestBody MgrStartPlayRequestVo mgrStartPlayRequestVo) throws ServiceException;
}
