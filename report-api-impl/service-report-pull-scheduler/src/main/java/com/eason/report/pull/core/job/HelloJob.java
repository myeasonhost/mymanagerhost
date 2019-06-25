package com.eason.report.pull.core.job;

import com.eason.report.pull.core.api.FPullAPIService;
import com.eason.report.pull.core.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class HelloJob implements BaseJob {  
  
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);
    @Autowired
    private FPullAPIService fPullAPIServiceFallback;
     
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.info("Hello Job执行时间: " + new Date());
        try {
            List<ResponseModel> responseModelList=fPullAPIServiceFallback.getPullBetForDSPT();
            log.info(responseModelList.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}  
