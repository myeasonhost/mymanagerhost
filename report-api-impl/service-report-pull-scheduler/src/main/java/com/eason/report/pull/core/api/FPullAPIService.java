package com.eason.report.pull.core.api;

import com.eason.report.pull.core.api.fallback.FPullAPIServiceFallback;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.FeignException;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-report-pull-data-center", fallback = FPullAPIServiceFallback.class)
public interface FPullAPIService extends PullAPI{

    @PostMapping(value = "/dsgf/getPullBet")
    List<ResponseModel> getPullBet() throws FeignException;

    @PostMapping(value = "/dsjd/getPullBet")
    List<ResponseModel> getPullBetForDSJD() throws FeignException;

    @PostMapping(value = "/getPullBet/{maxId}/{length}")
    ResponseModel getPullBet(@PathVariable(value = "maxId") Long maxId,
                             @PathVariable(value = "length") Integer length,
                             @RequestBody BaseConfig config) throws ServiceException;

    @PostMapping(value = "/getPullBet/{maxId}/{length}")
    ResponseModel getPullBet(@PathVariable(value = "maxId") String maxId,
                             @PathVariable(value = "length") Integer length,
                             @RequestBody BaseConfig config) throws ServiceException;
}
