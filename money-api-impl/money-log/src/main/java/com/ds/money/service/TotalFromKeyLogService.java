package com.ds.money.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;

import com.ds.money.dao.mapper.MoneyLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalFromKeyLogService implements Runnable{
	static final Logger logger = LoggerFactory.getLogger(TotalFromKeyLogService.class);
	@Autowired
	private MoneyLogDao moneyLogDao;
	/**
	 * 执行统计
	 */
	@PostConstruct
	public void exec(){
		logger.info("开始统计钱包日志============");
		new Thread(this).start();
	}
	public void run(){
		while(true){
			try {
				logger.info("开始统计钱包日志");
				long start = System.currentTimeMillis();
				List<String> tableList = getSiteTableList();
				CountDownLatch cdl = new CountDownLatch(tableList.size());
				for (String moneyLogTable : tableList) {
					new Thread(new TotalLogThread(moneyLogDao,moneyLogTable,cdl)).start();
				}
				cdl.await();
				long end = System.currentTimeMillis();
				logger.info("统计钱包日志完成,耗时:"+(end - start));
				Thread.sleep(60*1000);
			} catch (Exception e) {
				logger.error("统计钱包日志出错:",e);
				continue;
			}
			
		}
	}
	
	
	/**
	 * 获取所有网站的钱包日志表
	 * @return
	 */
	public List<String> getSiteTableList(){
		List<Integer> siteList = moneyLogDao.getSiteList();
		List<String> tablesList = new ArrayList<String>();
		for (Integer site : siteList) {
			tablesList.add(site+"_ds_member_money_log");
		}
		return tablesList;
	}
}
