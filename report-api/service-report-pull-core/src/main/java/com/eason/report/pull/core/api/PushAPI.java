package com.eason.report.pull.core.api;

import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.ResponseModel;

public interface PushAPI {
    ResponseModel getPushBet(Integer siteId,String prex,String startId,String endId) throws ServiceException;
    ResponseModel getPushBet(Integer siteId,String prex,Long startId,Long endId) throws ServiceException;
}
