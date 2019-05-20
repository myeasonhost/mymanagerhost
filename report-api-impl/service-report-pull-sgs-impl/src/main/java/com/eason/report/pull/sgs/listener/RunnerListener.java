//package com.eason.report.pull.sgs.listener;
//
//import com.eason.report.pull.ag.config.AgInfoConfig;
//import com.eason.report.pull.ag.exception.AgException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@Slf4j
//public class RunnerListener implements CommandLineRunner {
//
//    @Autowired
//    private AgInfoConfig agInfoConfig;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate10;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Object pullUrl = stringRedisTemplate10.boundHashOps("sgs").get("pullUrl");
//        Object pidtoken = stringRedisTemplate10.boundHashOps("sgs").get("pidtoken");
//        if (pullUrl == null) {
//            throw new AgException("AG读取缓存的配置，pullUrl为空，请正确配置");
//        }
//        if (pidtoken == null) {
//            throw new AgException("AG读取缓存的配置，pidtoken为空，请正确配置");
//        }
//        agInfoConfig.setPidtoken(pidtoken.toString());
//        agInfoConfig.setPullUrl(pullUrl.toString());
//        log.info("AG读取缓存的配置：pullUrl=" + pullUrl + ",pidtoken=" + pidtoken);
//    }
//
//}
