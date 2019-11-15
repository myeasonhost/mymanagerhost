package com.eason.transfer.openapi.user.api;

import com.eason.transfer.openapi.core.sdk.user.*;
import com.eason.transfer.openapi.user.aop.TransferStart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceImpl implements UserService {

    @TransferStart
    @Override
    public LoginResponse login(LoginRequest request) {
        System.out.println(request);
        LoginResponse response=new LoginResponse();
        response.setResult("demo返回结果="+request.toString());
        return response;
    }

    @TransferStart
    @Override
    public TransferInResponse transferIn(TransferInRequest request) {
        System.out.println(request);
        TransferInResponse response=new TransferInResponse();
        response.setResult("demo返回结果="+request.toString());
        return response;
    }

    @TransferStart
    @Override
    public TransferOutResponse transferOut(TransferOutRequest request) {
        System.out.println(request);
        TransferOutResponse response=new TransferOutResponse();
        response.setResult("demo返回结果="+request.toString());
        return response;
    }
}
