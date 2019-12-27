package com.eason.transfer.openapi.core.system.schedule;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
public class MyJob extends QuartzJobBean{

	public void work() {  
		 System.out.println("schedule test date:" + new Date().toString());
	 }

	@Override
	protected void executeInternal(JobExecutionContext jobContext)
			throws JobExecutionException {
		 System.out.println("schedule test date:" + new Date().toString()+" ,jobContext="+jobContext);			
	}
}
