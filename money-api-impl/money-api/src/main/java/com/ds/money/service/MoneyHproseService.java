//package com.ds.money.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import com.ds.money.controller.MoneyHproseAction;
//import hprose.io.HproseMode;
//import hprose.server.HproseTcpServer;
//
//public class MoneyHproseService implements ApplicationListener<ContextRefreshedEvent> {
//	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//	private final String hprose_tcp_url = "";
//	@Autowired
//	private MemberMoneyServiceImp moneyService;
//	@Autowired
//	private MoneyCenterService moneyConfigService;
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {//root application context 没有parent，他就是老大.
//
//		if (event.getApplicationContext().getParent() == null) {
//			System.out.println(hprose_tcp_url);
//
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//					HproseTcpServer server = new HproseTcpServer(hprose_tcp_url);
//
//				//	server.add("getMoney", MoneyHproseAction.class);
//					server.add("getMoney", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("transMoney", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("memberMoneyLog", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("moneyLogByDate", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("setMemberData", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("memberMoneyLogByLevel", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("updateUserState", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("getTotalBalance", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("transMoneyBatch", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.add("changeAgentMoney", new MoneyHproseAction(moneyService,moneyConfigService));
//					server.start();
//					HproseMode  mode = server.getMode();
////					boolean pool = server.isEnabledThreadPool();
////					server.setEnabledThreadPool(true);
////					server.setThreadCount(1000);
//					//server.setThreadPool(value);
//					logger.info("HproseTcp mode:"+mode.name());
////					logger.info("HproseTcp ThreadPool:"+pool);
//					logger.info("HproseTcp.........START");
//					System.in.read();
//			//		server.stop();
//		//			logger.info("HproseTcp.........end");
//				} catch (Exception e) {
//
//					logger.info("HproseTcp......exception。。。。");
//				}
//				}
//			}).start();
//
//		}
//	}
//}
