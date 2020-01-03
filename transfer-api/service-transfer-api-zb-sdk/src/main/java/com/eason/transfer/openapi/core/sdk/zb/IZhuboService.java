package com.eason.transfer.openapi.core.sdk.zb;


import com.eason.transfer.openapi.core.sdk.zb.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @apiDefine zhubo  主播API
 */
@FeignClient(contextId = "zb#IZhuboService",value = "service-transfer-api-zb")
public interface IZhuboService {

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/zhubo/start")
    ZhuboStartResponse start(@RequestBody ZhuboStartRequest request) throws Exception;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/zhubo/stop")
    ZhuboStopResponse stop(@RequestBody ZhuboStopRequest request) throws Exception;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/zhubo/create")
    ZhuboCreateResponse create(@RequestBody ZhuboCreateRequest request) throws Exception;

}
