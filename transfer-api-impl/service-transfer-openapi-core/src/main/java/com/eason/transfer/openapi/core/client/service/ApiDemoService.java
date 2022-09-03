package com.eason.transfer.openapi.core.client.service;


import com.eason.transfer.openapi.core.client.vo.UserMoneyRequest;
import com.eason.transfer.openapi.core.client.vo.UserMoneyResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @apiDefine 4user app用户钱包API
 */
//@FeignClient(contextId = "money#UserMoneyService",value = "service-transfer-api-moneys")
@Service
public class ApiDemoService {

   public UserMoneyResponse getUserMoney(UserMoneyRequest request){
       UserMoneyResponse response=new UserMoneyResponse();
       response.setMoey(new BigDecimal(request.getUsername()));
       response.setResult("查询成功");
       return response;
   }

}
