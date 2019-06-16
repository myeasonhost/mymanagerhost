package com.eason.report.pull.core.listener;

import com.eason.report.pull.core.exception.DataException;
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
    public void run(String... args) throws DataException {
//        try {
//            dtMGMgr.loadConfig();
//        }catch (Exception e){
//            throw new DataException(e.getMessage());
//        }

    }

}
