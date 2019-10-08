package com.eason.transfer.openapi.user.api;

import com.eason.transfer.openapi.user.api.model.DemoRequest;
import com.eason.transfer.openapi.user.api.model.DemoResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("userServiceImpl")
public class UserServiceImpl {

    @RequestMapping(value = "/test")
    public DemoResponse test(Map<String,String> request) {
        System.out.println(request);
        DemoResponse response=new DemoResponse();
        response.setResult("demo返回结果2222222222222222");
        return response;
    }

}
