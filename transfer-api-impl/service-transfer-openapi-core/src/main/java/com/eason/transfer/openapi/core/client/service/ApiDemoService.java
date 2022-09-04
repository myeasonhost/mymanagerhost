package com.eason.transfer.openapi.core.client.service;


import com.eason.transfer.openapi.core.client.model.PayNotifyRequest;
import com.eason.transfer.openapi.core.client.model.PayNotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @apiDefine 支付回调测试API
 */
@Slf4j
@Service
public class ApiDemoService {

   public PayNotifyResponse payNotify(PayNotifyRequest request){
       log.info("商户端收到回调通知="+request);
       PayNotifyResponse response=new PayNotifyResponse();
       response.setResult("订单回调成功");
       return response;
   }

}
