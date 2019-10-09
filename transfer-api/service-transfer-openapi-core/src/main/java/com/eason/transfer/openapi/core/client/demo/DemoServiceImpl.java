package com.eason.transfer.openapi.core.client.demo;

import com.eason.transfer.openapi.core.annotation.ApiMethod;
import com.eason.transfer.openapi.core.annotation.ApiMethodCategory;
import com.eason.transfer.openapi.core.client.demo.model.DemoRequest;
import com.eason.transfer.openapi.core.client.demo.model.DemoResponse;
import org.springframework.stereotype.Service;

@ApiMethodCategory(cateCnName = "模板API", cateEnName = "DemoAPI", cateDescription = "公共用户模块")
@Service("demoServiceImpl")
public class DemoServiceImpl {

    @ApiMethod(method = "user.test")
    public DemoResponse test(DemoRequest request) {
        System.out.println(request);
        DemoResponse response = new DemoResponse();
        response.setResult("demo返回结果2222222222222222");
        return response;
    }

}
