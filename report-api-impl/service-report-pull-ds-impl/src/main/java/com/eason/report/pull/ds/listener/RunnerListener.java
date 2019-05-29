package com.eason.report.pull.ds.listener;

import com.eason.report.pull.ds.config.GFAppInfoConfig;
import com.eason.report.pull.ds.config.JDAppInfoConfig;
import com.eason.report.pull.ds.exception.DsException;
import com.eason.report.pull.ds.manager.DtGFMgr;
import com.eason.report.pull.ds.manager.DtJDMgr;
import com.eason.report.pull.ds.manager.MdtJDMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RunnerListener implements CommandLineRunner {

    @Autowired
    private DtGFMgr dtGFMgr;
    @Autowired
    private DtJDMgr dtJDMgr;
    @Autowired
    private MdtJDMgr mdtJDMgr;

    @Override
    public void run(String... args) throws Exception {
        try {
            dtGFMgr.loadConfig();
            dtJDMgr.loadConfig();
            mdtJDMgr.loadConfig();
        }catch (Exception e){
            throw new DsException(e.getMessage());
        }

    }

}
