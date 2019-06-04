package com.eason.report.pull.core.mq.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSConfig implements InitializingBean {
    @Value("${target.siteId}")
    private String siteId;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("target.siteId", siteId);
    }
}
