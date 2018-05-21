package com.eason.api.zb.service.impl;

import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.service.FMgrService;
import com.eason.api.zb.vo.zhubo.MgrStartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayResponseVo;
import org.springframework.stereotype.Component;

@Component("mgrServiceImpl")
public class FMgrServiceImpl implements FMgrService {
    @Override
    public StartPlayResponseVo createRoom(Integer zbId, String token, MgrStartPlayRequestVo mgrStartPlayRequestVo) throws ServiceException {
        throw new ServiceException();
    }
}
