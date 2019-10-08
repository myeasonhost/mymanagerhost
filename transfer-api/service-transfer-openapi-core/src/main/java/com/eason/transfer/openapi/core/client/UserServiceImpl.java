package com.eason.transfer.openapi.core.client;

import com.eason.transfer.openapi.core.annotation.ApiMethod;
import com.eason.transfer.openapi.core.annotation.ApiMethodCategory;
import com.eason.transfer.openapi.core.client.model.DemoRequest;
import com.eason.transfer.openapi.core.client.model.DemoResponse;
import org.springframework.stereotype.Service;

@ApiMethodCategory(cateCnName = "用户API", cateEnName = "UserAPI", cateDescription = "公共用户模块")
@Service("userServiceImpl")
public class UserServiceImpl {

    @ApiMethod(method = "user.test")
    public DemoResponse test(DemoRequest request) {
        System.out.println(request);
        DemoResponse response = new DemoResponse();
        response.setResult("demo返回结果2222222222222222");
        return response;
    }

}
