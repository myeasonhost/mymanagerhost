package com.eason.report.pull.core.api.fallback;

import com.eason.report.pull.core.api.FPullAPIService;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.FeignException;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.ResponseModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("fPullAPIServiceFallback")
public class FPullAPIServiceFallback implements FPullAPIService {

    @Override
    public List<ResponseModel> getPullBet() throws FeignException {
        throw new FeignException();
    }

    @Override
    public List<ResponseModel> getPullBetForDSJD() throws FeignException {
        throw new FeignException();
    }

    @Override
    public List<ResponseModel> getPullBetForMdtJD() throws FeignException {
        throw new FeignException();
    }

    @Override
    public List<ResponseModel> getPullBetForMG() throws FeignException {
        throw new FeignException();
    }

    @Override
    public ResponseModel getPullBet(Long maxId, Integer length, BaseConfig config) throws FeignException{
        throw new FeignException("此接口对外不暴露");
    }

    @Override
    public ResponseModel getPullBet(String maxId, Integer length, BaseConfig config) throws ServiceException {
        throw new FeignException("此接口对外不暴露");
    }
}
