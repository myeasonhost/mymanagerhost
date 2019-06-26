package com.eason.report.pull.core.api;

import com.eason.report.pull.core.api.fallback.FPullAPIServiceFallback;
import com.eason.report.pull.core.exception.FeignException;
import com.eason.report.pull.core.model.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "service-report-pull-data-center", fallback = FPullAPIServiceFallback.class)
public interface FPullAPIService{

    @PostMapping(value = "/dsgf/getPullBet")
    List<ResponseModel> getPullBet() throws FeignException;

    @PostMapping(value = "/dsjd/getPullBet")
    List<ResponseModel> getPullBetForDSJD() throws FeignException;

    @PostMapping(value = "/mdtjd/getPullBet")
    List<ResponseModel> getPullBetForMdtJD() throws FeignException;

    @PostMapping(value = "/dsmg/getPullBet")
    List<ResponseModel> getPullBetForDSMG() throws FeignException;

    @PostMapping(value = "/ky/getPullBet")
    List<ResponseModel> getPullBetForKY() throws FeignException;

    @PostMapping(value = "/dslmg/getPullBet")
    List<ResponseModel> getPullBetForDSLMG() throws FeignException;

    @PostMapping(value = "/dspt/getPullBet")
    List<ResponseModel> getPullBetForDSPT() throws FeignException;

    @PostMapping(value = "/dssgs/getPullBet")
    List<ResponseModel> getPullBetForDSSGS() throws FeignException;

}
