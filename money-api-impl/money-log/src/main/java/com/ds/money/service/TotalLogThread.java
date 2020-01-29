package com.ds.money.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.ds.money.dao.mapper.MoneyLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ds.money.entity.TotalLogVo;

public class TotalLogThread implements Runnable{
	static final Logger logger = LoggerFactory.getLogger(TotalLogThread.class);
	private MoneyLogDao moneyLogDao;
	private String moneyLogTable;
	private CountDownLatch cdl;
	public TotalLogThread(MoneyLogDao moneyLogDao, String moneyLogTable,
						  CountDownLatch cdl) {
		this.cdl = cdl;
		this.moneyLogDao = moneyLogDao;
		this.moneyLogTable = moneyLogTable;
	}

	@Override
	public void run() {
		try {
			logger.info("开始获取日志");
			List<TotalLogVo> list = moneyLogDao.gettotalLogFromLog(moneyLogTable);
			if(list == null || list.size() == 0){
				return;
			}
			moneyLogDao.batchInsertOrUpdateLog(list);
		} catch (Exception e) {
			logger.error("TotalLogThread统计出错",e);
		}finally{
			cdl.countDown();
		}
	}

}
