package com.eason.transfer.openapi.core.client;

import com.eason.transfer.openapi.core.annotation.ApiMethod;
import com.eason.transfer.openapi.core.annotation.ApiMethodCategory;
import com.eason.transfer.openapi.core.client.model.DemoRequest;
import com.eason.transfer.openapi.core.client.model.DemoResponse;

@ApiMethodCategory(cateCnName = "用户API",cateEnName = "UserAPI",cateDescription = "公共用户模块")
public class UserServiceImpl {

    @ApiMethod(method = "user.test")
    public DemoResponse test(DemoRequest request) {
        System.out.println(request);
        DemoResponse response=new DemoResponse();
        response.setResult("demo返回结果111111111111111111111111");
        return response;
    }

}
