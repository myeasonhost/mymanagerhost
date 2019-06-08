package com.eason.report.pull.core.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
@Slf4j
public class FeignConfigListener {
    @Bean
    public ErrorDecoder errorDecoder(){
        return new ErrorDecoder(){
            @Override
            public Exception decode(String s, Response response) {
                try {
                    String info= Util.toString(response.body().asReader());
                    log.error(info);
                    ObjectMapper objectMapper = new ObjectMapper();
                    //{"timestamp":"2019-06-07T03:00:00.022+0000","status":404,"error":"Not Found","message":"No message available","path":"/dsgf/getPullBet"}
                    Map<String, Object> resultMap =objectMapper.readValue(info, Map.class);
                    return new HystrixBadRequestException((String) resultMap.get("message"));
                } catch (IOException e) {
                    e.printStackTrace();
                    return new HystrixBadRequestException(e.getMessage());
                }
            }
        };
    }
}
