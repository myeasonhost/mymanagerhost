package com.eason.transfer.openapi.core.sdk.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("service-transfer-api-user")
public interface DemoServiceImpl {

    @PostMapping(value = "/test")
    DemoResponse test(DemoRequest request);

}
