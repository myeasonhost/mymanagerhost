package com.eason.report.pull.core.listener;

import com.eason.report.pull.core.exception.DsException;
import com.eason.report.pull.core.manager.MGMgr;
import com.eason.report.pull.core.manager.DtGFMgr;
import com.eason.report.pull.core.manager.DtJDMgr;
import com.eason.report.pull.core.manager.MdtJDMgr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RunnerListener implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try {

        }catch (Exception e){
            throw new DsException(e.getMessage());
        }

    }

}
