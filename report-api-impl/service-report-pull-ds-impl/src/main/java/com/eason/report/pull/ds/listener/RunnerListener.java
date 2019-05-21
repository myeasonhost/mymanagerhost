package com.eason.report.pull.ds.listener;

import com.eason.report.pull.ds.exception.DsException;
import com.eason.report.pull.ds.manager.DtGFMgr;
import com.eason.report.pull.ds.manager.DtJDMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RunnerListener implements CommandLineRunner {

//    @Autowired
//    private DtGFMgr dtGFMgr;
//    @Autowired
//    private DtJDMgr dtJDMgr;


    @Override
    public void run(String... args) throws Exception {
//        try {
//            dtGFMgr.loadConfig();
//            dtJDMgr.loadConfig();
//        }catch (Exception e){
//            throw new DsException(e.getMessage());
//        }

    }

}
