//package com.ds.money.service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.ds.money.dao.MoneyLogDao;
//import com.ds.money.dao.mapper.MoneyLogDao;
//import com.ds.money.entity.DsMemberMoneyLog;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.jms.Destination;
//import javax.jms.TextMessage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service("moneyLogService")
//public class MoneyLogService {
//	static final Logger logger = LoggerFactory.getLogger(MoneyLogService.class);
//	private final Integer maxSize = Integer.valueOf(5000);
////	@Autowired
////	private ConsumerService consumerService;
//	@Resource(name = "moneyQueueDestination")
//	private Destination moneyQueueDestination;
//	@Autowired
//	private MoneyLogDao logDao;
//	private Map<Integer, List<DsMemberMoneyLog>> map = new HashMap<Integer, List<DsMemberMoneyLog>>();
//	private TextMessage lastTextMessage = null;
//
//	@PostConstruct
//	public void start() {
//		logger.info("mq 执行 start 开始......");
//		for (;;) {
//			try {
//				long start = System.currentTimeMillis();
//				TextMessage textMessage = this.consumerService
//						.receive(this.moneyQueueDestination);
//				long end = System.currentTimeMillis();
//				if (textMessage != null) {
//					this.lastTextMessage = textMessage;
//					String text = textMessage.getText();
//					DsMemberMoneyLog log = (DsMemberMoneyLog) JSONObject
//							.parseObject(text, DsMemberMoneyLog.class);
//					if (this.map.containsKey(log.getSiteId())) {
//						List<DsMemberMoneyLog> list = (List) this.map.get(log
//								.getSiteId());
//						list.add(log);
//					} else {
//						List<DsMemberMoneyLog> list = new ArrayList();
//						list.add(log);
//						this.map.put(log.getSiteId(), list);
//					}
//				} else {
//					logger.info("MQ的数据为null,list:" + this.map.size());
//					if (this.map.size() <= 0) {
//						continue;
//					}
//					insertLog(this.lastTextMessage);
//
//					continue;
//				}
//				if ((end - start >= 30L)
//						|| (this.map.size() >= this.maxSize.intValue())) {
//					if (this.map.size() >= this.maxSize.intValue()) {
//						insertLog(this.lastTextMessage);
//					} else if (this.map.size() > 0) {
//						insertLog(this.lastTextMessage);
//					}
//				}
//			} catch (Exception e) {
//				printErrorLogList();
//
//				try {
//					Thread.sleep(5000L);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				logger.error("发生异常", e);
//			}
//		}
//	}
//
//	public void printErrorLogList() {
//		logger.info("打印errorLogList");
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
//			RuntimeException.class, Exception.class })
//	public void insertLog(TextMessage textMessage) throws Exception {
//		for (Map.Entry<Integer, List<DsMemberMoneyLog>> entry : this.map
//				.entrySet()) {
//			long start = System.currentTimeMillis();
//			Integer siteId = (Integer) entry.getKey();
//			List<DsMemberMoneyLog> logList = (List) entry.getValue();
//			this.logDao.batchInsertLog(siteId, logList);
//			long end = System.currentTimeMillis();
//			logger.info("MQ插入到数据库:" + logList.size() + ",time:" + (end - start));
//		}
//		textMessage.acknowledge();
//		this.map.clear();
//	}
//}
