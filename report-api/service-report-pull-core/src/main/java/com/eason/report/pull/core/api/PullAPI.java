package com.eason.report.pull.core.api;

import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.ResponseModel;

import java.util.List;

public interface PullAPI {
    List<ResponseModel> getPullBet() throws ServiceException;
    ResponseModel getPullBet(Long maxId, Integer length, BaseConfig config) throws ServiceException;
    ResponseModel getPullBet(String maxId,Integer length, BaseConfig config) throws ServiceException;
}
