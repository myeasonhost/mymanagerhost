package com.eason.transfer.openapi.user.api;

import com.eason.transfer.openapi.core.sdk.user.DemoRequest;
import com.eason.transfer.openapi.core.sdk.user.DemoResponse;
import com.eason.transfer.openapi.core.sdk.user.DemoServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userServiceImpl")
public class UserServiceImpl implements DemoServiceImpl {

    @RequestMapping(value = "/test")
    public DemoResponse test(@RequestBody DemoRequest request) {
        System.out.println(request);
        DemoResponse response=new DemoResponse();
        response.setResult("demo返回结果2222222222222222");
        return response;
    }

}
