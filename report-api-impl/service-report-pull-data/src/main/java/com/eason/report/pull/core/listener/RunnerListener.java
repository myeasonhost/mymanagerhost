package com.eason.report.pull.core.listener;

import com.eason.report.pull.core.exception.MgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RunnerListener implements CommandLineRunner {
//
//    @Autowired
//    private DtMGMgr dtMGMgr;

    @Override
    public void run(String... args) throws MgException {
//        try {
//            dtMGMgr.loadConfig();
//        }catch (Exception e){
//            throw new MgException(e.getMessage());
//        }

    }

}
