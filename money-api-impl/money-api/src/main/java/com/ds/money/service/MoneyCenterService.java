package com.ds.money.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.PostConstruct;
import com.ds.money.dao.*;
import com.ds.money.dao.mapper.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.ds.money.entity.DSGameInfo;
import com.ds.money.entity.DSGameInfoExample;
import com.ds.money.entity.DSIPList;
import com.ds.money.entity.DSIPListExample;
import com.ds.money.entity.DsFromKeyType;
import com.ds.money.entity.DsFromKeyTypeExample;
import com.ds.money.entity.DsMoneyConfig;
import com.ds.money.entity.DsMoneyConfigExample;
import com.ds.money.entity.DsSiteHashcode;
import com.ds.money.entity.DsSiteHashcodeExample;

@Service
public class MoneyCenterService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
//	private final Lock r = lock.readLock();
	private final Lock w = lock.writeLock();
//	@Autowired
//	MoneyCenterConfigMapper configMapper;
    @Autowired
    private DsMoneyConfigMapper configMapper;
	@Autowired
	private DsSiteHashcodeMapper siteHashcodeMapper;
	@Autowired
	private DsFromKeyTypeMapper fromKeyTypeMapper;
	@Autowired
	private DSIPListMapper ipListMapper;
	@Autowired
	private DSGameInfoMapper gameInfoMapper;


	private Map<String,Integer> siteHashcodeMap = new HashMap<String, Integer>();
	//configMap用于存放网站配置信息
	private Map<String,DsMoneyConfig> moneyConfigMap = new HashMap<String,DsMoneyConfig>();
	//
	private Map<String,Integer> gameTypeMap = new HashMap<String,Integer>();

	private Map<String,Set<Long>> fromKeyTypeMap = new HashMap<String,Set<Long>>();
	//保存gameInfo信息
	private Map<String,String> gameInfoMap = new HashMap<String,String>();

	private boolean running = true;
	private Set<String> ipSet = new HashSet<String>();

	@PostConstruct
	public List<DsMoneyConfig> initMoneyConfigList(){
		w.lock();
		try {
			logger.info("init service.....");
			//moneyConfigMap.clear();
			//fromKeyTypeMap.clear();
			DsMoneyConfigExample e = new DsMoneyConfigExample();
			e.createCriteria().andStateEqualTo(Short.valueOf("50"));
			List<DsMoneyConfig> list = configMapper.selectByExample(e);
			if (list != null && list.size() > 0) {
				for (DsMoneyConfig moneyConfig : list) {
					moneyConfigMap.put(moneyConfig.getFromKey(), moneyConfig);
					setfromKeyTypeMap(moneyConfig.getFromKey());
				}
			}
			logger.info("init service ok.....");
			return list;
		} finally {
			w.unlock();
		}
	}
	/**
	 * 初始化fromTypeMap
	 * @return
	 */

	public void setfromKeyTypeMap(String fromKey){
		DsFromKeyTypeExample e = new DsFromKeyTypeExample();
		e.createCriteria().andFkFromKeyEqualTo(fromKey).andStateEqualTo(50);
		List<DsFromKeyType> list = fromKeyTypeMapper.selectByExample(e);
		if(list!=null && list.size()>0){
			Set<Long> set = new HashSet<Long>();
			for (DsFromKeyType fromKeyType : list) {
				set.add(fromKeyType.getId());
			}
			fromKeyTypeMap.put(fromKey, set);
		}
	}
	@PostConstruct
	public List<DsSiteHashcode> initSiteHashMapList(){
		w.lock();
		try {
			//siteHashcodeMap.clear();
			DsSiteHashcodeExample e = new DsSiteHashcodeExample();
			List<DsSiteHashcode> list = siteHashcodeMapper.selectByExample(e);
			if (list != null && list.size() > 0) {
				for (DsSiteHashcode siteHashcode : list) {
					siteHashcodeMap.put(siteHashcode.getHashcode(),
							siteHashcode.getSiteId());
				}
			}
			return list;
		} finally {
			w.unlock();
		}
	}
	@PostConstruct
	public void initgameTypeMap(){
		w.lock();
		try {
			//gameTypeMap.clear();
			DsFromKeyTypeExample e = new DsFromKeyTypeExample();
			e.createCriteria().andStateEqualTo(50);

			List<DsFromKeyType> list = fromKeyTypeMapper.selectByExample(e);
			if (list != null && list.size() > 0) {
				for (DsFromKeyType dsFromKeyType : list) {
					if(StringUtils.isNotBlank(dsFromKeyType.getGameType())){
						gameTypeMap.put(dsFromKeyType.getGameType(), dsFromKeyType.getId().intValue());
					}
				}
			}
			logger.info("init initgameTypeMap ok.....");
		} finally {
			w.unlock();
		}
	}
	@PostConstruct
	public void initIPList(){
		w.lock();
		try {
			logger.info("initIPList service.....");
			//ipSet.clear();
			DSIPListExample e = new DSIPListExample();
			e.createCriteria().andStateEqualTo(50);
			List<DSIPList> ipList = ipListMapper.selectByExample(e);
			for (DSIPList dsip : ipList) {
				ipSet.add(dsip.getIp());
			}
			logger.info("initIPList ok.....");
		} finally {
			w.unlock();
		}
	}
	@PostConstruct
	public void initGameInfo(){
		w.lock();
		try {
			logger.info("initGameInfo service.....");
			//gameInfoMap.clear();
			DSGameInfoExample e = new DSGameInfoExample();
			e.createCriteria().andStateEqualTo(50);
			List<DSGameInfo> list = gameInfoMapper.selectByExample(e);
			for (DSGameInfo dsGameInfo : list) {
				gameInfoMap.put(dsGameInfo.getGameType(), dsGameInfo.getGameName());
			}
			logger.info("initGameInfo ok.....");
		} finally {
			w.unlock();
		}
	//	logger.info(gameInfoMap.toString());
	}
	@Scheduled(cron = "0 0/5 * * * ?")
	public void init(){
		this.initMoneyConfigList();
		this.initSiteHashMapList();
		this.initIPList();
		this.initGameInfo();
		this.initgameTypeMap();
	}

	public Set<String> getIpSet() {
		return ipSet;
	}
	public void setIpSet(Set<String> ipSet) {
		this.ipSet = ipSet;
	}
	public DsMoneyConfig getConfigByfromKey(String fromKey){
		DsMoneyConfigExample e = new DsMoneyConfigExample();
		e.createCriteria().andFromKeyEqualTo(fromKey).andStateEqualTo(Short.valueOf("50"));
		List<DsMoneyConfig> list = configMapper.selectByExample(e);
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
	/**
	 * gameId=LIVE/3/259359 视讯类
	 * gameId=BJC|519693 彩票类
	 * @param memo
	 * @param gameType
	 * @param gameId
	 * @return
	 */
	public String setMemo(String memo,String gameType,String gameId){
		StringBuffer sb = new StringBuffer();
		String gameName = null;
		String qishu = null;
		try {
			if(StringUtils.isNotBlank(gameType)
					&&StringUtils.isNotBlank(gameId)){
				if(memo.startsWith("CANCEL")){
					sb.append("注单取消,返还本金:");
				}
				if(gameType.equals("LIVE")){
					String [] gameIdArr = gameId.split("\\/");
					String gameMapKey = gameIdArr[1];
					gameName = gameInfoMap.get(gameMapKey);
				}else if(gameType.equals("LOTTERY")){
					String [] gameIdArr = gameId.split("\\|");
					String gameMapKey = gameIdArr[0];
					gameName = gameInfoMap.get(gameMapKey);
					qishu = gameIdArr[1];
				}else if(gameType.equals("LOTTO")){
					String [] gameIdArr = gameId.split("\\/");
					String gameMapKey = gameIdArr[0];
					gameName = gameInfoMap.get(gameMapKey);
					qishu = gameIdArr[1];
				}
				logger.info("gameName:"+gameName);
				logger.info("qishu:"+qishu);
				if(StringUtils.isNotBlank(gameName)){
					sb.append(gameName);
				}
				if(StringUtils.isNotBlank(qishu)){
					sb.append(",下注期数:"+qishu);
				}

			}else{
				if(memo.equals("BET")){
					sb.append("下注");
				}else if(memo.equals("WIN_LOSS")){
					sb.append("派彩");
				}else if(memo.startsWith("CANCEL")){
					sb.append("注单取消,返还本金");
				}else{
					sb.append(memo);
				}
			}


		} catch (Exception e) {
			sb = new StringBuffer();
		}
		return sb.toString();
	}
	public Map<String, DsMoneyConfig> getMoneyConfigMap() {
		return moneyConfigMap;
	}

	public void setMoneyConfigMap(Map<String, DsMoneyConfig> moneyConfigMap) {
		this.moneyConfigMap = moneyConfigMap;
	}
	public Map<String, Integer> getSiteHashcodeMap() {
		return siteHashcodeMap;
	}
	public void setSiteHashcodeMap(Map<String, Integer> siteHashcodeMap) {
		this.siteHashcodeMap = siteHashcodeMap;
	}
	public Map<String, Set<Long>> getFromKeyTypeMap() {
		return fromKeyTypeMap;
	}
	public void setFromKeyTypeMap(Map<String, Set<Long>> fromKeyTypeMap) {
		this.fromKeyTypeMap = fromKeyTypeMap;
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public Map<String, Integer> getGameTypeMap() {
		return gameTypeMap;
	}
	public void setGameTypeMap(Map<String, Integer> gameTypeMap) {
		this.gameTypeMap = gameTypeMap;
	}




}
