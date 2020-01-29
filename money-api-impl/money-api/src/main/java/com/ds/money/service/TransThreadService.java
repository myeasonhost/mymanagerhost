//package com.ds.money.service;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.Vector;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import com.alibaba.fastjson.JSON;
//import com.ds.money.controller.vo.TransMoneyVo;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ds.money.entity.DsMoneyConfig;
//import com.ds.money.untils.DataUtils;
//import com.ds.money.untils.EncryptUtils;
//import com.ds.money.untils.MoneyConfigUtils;
//import com.ds.money.vo.TransMoneyParam;
//
//public class TransThreadService implements Runnable{
//	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//	private MemberMoneyServiceImp moneyService;
//	private MoneyCenterService moneyCenterService;
//	private TransMoneyParam transMoneyParam;
//	private TransMoneyVo transMoneyVo;
//	private String ip;
//	private Vector<Object> dataList;
//	private AtomicInteger count;
//
//	public TransThreadService(MemberMoneyServiceImp moneyService,
//			MoneyCenterService moneyCenterService,
//			TransMoneyParam transMoneyParam, String ip,
//			Vector<Object> dataList, AtomicInteger count) {
//		super();
//		this.moneyService = moneyService;
//		this.moneyCenterService = moneyCenterService;
//		this.transMoneyParam = transMoneyParam;
//		this.ip = ip;
//		this.dataList = dataList;
//		this.count = count;
//	}
//
//	public TransThreadService(MemberMoneyServiceImp moneyService,
//							  MoneyCenterService moneyCenterService,
//							  TransMoneyVo transMoneyVo, String ip,
//							  Vector<Object> dataList, AtomicInteger count) {
//		super();
//		this.moneyService = moneyService;
//		this.moneyCenterService = moneyCenterService;
//		this.transMoneyVo = transMoneyVo;
//		this.ip = ip;
//		this.dataList = dataList;
//		this.count = count;
//	}
//	@Override
//	public void run() {
//		TransMoneyParam transMoneyParam=null;
//		if(this.transMoneyParam !=null){
//			transMoneyParam = this.transMoneyParam;
//		}else{
//			transMoneyParam = JSON.parseObject(JSON.toJSONString(transMoneyVo),TransMoneyParam.class);
//		}
//		Object result = transBatch(transMoneyParam,ip);
//		dataList.add(result);
//	}
//
//	private Object transBatch(TransMoneyParam transMoneyParam,String ip){
//		Map<String,Object> result = new HashMap<String,Object>();
//		Map<String,Object> data = new HashMap<String,Object>();
//		try{
//			String username = transMoneyParam.getUsername();
//			String remitno = transMoneyParam.getRemitno();
//			String transType = transMoneyParam.getTransType();
//			String remit = transMoneyParam.getRemit();
//			String wagerCancel = transMoneyParam.getWagerCancel();
//			String fromKey = transMoneyParam.getFromKey();
//			String siteId = transMoneyParam.getSiteId();
//			String hashCode = transMoneyParam.getHashCode();
//			String key = transMoneyParam.getKey();
//			String fromKeyType = transMoneyParam.getFromKeyType();
//			String memo = transMoneyParam.getMemo();
//			String gameType = transMoneyParam.getGameType();
//			String gameId = transMoneyParam.getGameId();
//			if(StringUtils.isNotBlank(gameType)){
//				Map<String,Integer> gameTypeMap = moneyCenterService.getGameTypeMap();
//				fromKeyType = gameTypeMap.get(gameType+"_"+transType.toUpperCase()).toString();
//			}
//
//			if(StringUtils.isBlank(username)){
//				result.put("message", "username is null");
//				result.put("code", 110009);
//				logger.info("username is null");
//				count.incrementAndGet();
//				return result;
//			}
//			if(StringUtils.isBlank(remitno)){
//				result.put("message", "remitno is null");
//				result.put("code", 110009);
//				logger.info("remitno is null");
//				count.incrementAndGet();
//				return result;
//			}
//			if(StringUtils.isBlank(transType)
//				||(!transType.equals("in")&&!transType.equals("out"))
//				&&(!transType.equals("IN")&&!transType.equals("OUT"))){
//				result.put("message", "transType error");
//				logger.info("transType is error");
//				result.put("code", 110009);
//				count.incrementAndGet();
//				return result;
//			}
//			remit = DataUtils.getStringNumber(remit);
//			if(StringUtils.isBlank(remit)
//					||!DataUtils.checkNum(remit)
//					||Double.valueOf(remit)<0){//去掉等于 0 的条件
//				result.put("message", "remit must be equal or than 0");
//				logger.info("remit must be equal or than 0");
//				result.put("code", 110009);
//				count.incrementAndGet();
//				return result;
//			}
//			if(!StringUtils.isBlank(wagerCancel)
//					&&(!wagerCancel.equals("0")&&!wagerCancel.equals("1"))){
//					result.put("message", "wagerCancel is wrong");
//					result.put("code", 110009);
//					logger.info("wagerCancel is wrong");
//					count.incrementAndGet();
//					return result;
//		    }
//			if(StringUtils.isBlank(fromKey)){
//				result.put("message", "fromKey is null");
//				result.put("code", 110009);
//				logger.info("fromKey is null");
//				count.incrementAndGet();
//				return result;
//			}
//			if(StringUtils.isBlank(key)){
//				result.put("message", "key is null");
//				logger.info("key is null");
//				count.incrementAndGet();
//				result.put("code", 110009);
//				return result;
//			}
//
//			if(StringUtils.isBlank(siteId)
//					&& StringUtils.isBlank(hashCode)){
//				result.put("message", "hashCode,siteId is null");
//				result.put("code", 110009);
//				logger.info("hashCode,siteId is null");
//				count.incrementAndGet();
//				return result;
//			}
//			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
//				result.put("message", "siteId is wrong");
//				logger.info("siteId is wrong");
//				result.put("code", 110009);
//				count.incrementAndGet();
//				return result;
//			}
//			if(StringUtils.isNotBlank(hashCode)){
//				Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
//				if(map == null || map.size() == 0){
//					result.put("message", "web config is wrong");
//					result.put("code", 110001);
//					logger.info("web config is wrong");
//					count.incrementAndGet();
//					return result;
//				}
//				if(map.get(hashCode) == null){
//					result.put("message", hashCode+" hashCode is not found");
//					result.put("code", 110001);
//					logger.info(hashCode+" hashCode is not found");
//					count.incrementAndGet();
//					return result;
//				}
//				siteId = map.get(hashCode).toString();
//			}
//
//			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
//			String md5key = MoneyConfigUtils.getKeyMd5(key);
//			String toMd5 = EncryptUtils.toMD5(fromKey+username+remitno);
//			if(!md5key.equals(toMd5)){
//				result.put("message", "md5 is wrong");
//				result.put("code", 110009);
//				logger.info("md5 is wrong");
//				count.incrementAndGet();
//				return result;
//			}
//			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
//			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
//			if(moneyConfig == null){
//				result.put("message", "fromKey is wrong");
//				result.put("code", 110014);
//				logger.info("fromKey is wrong");
//				count.incrementAndGet();
//				return result;
//			}
////			if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
////					!moneyConfig.getIplist().contains(ip)){
////				result.put("message", "ip is not allowed");
////				result.put("code", 110014);
////				return result;
////			}
//
//			if(StringUtils.isBlank(fromKeyType) || !DataUtils.checkNum(fromKeyType)){
//				result.put("message", "fromKeyType is wrond");
//				result.put("code", 110009);
//				count.incrementAndGet();
//				return result;
//			}else{
//				Map<String,Set<Long>> fromKeyTypeMap = moneyCenterService.getFromKeyTypeMap();
//				Set fromKeyTypeSet = fromKeyTypeMap.get(fromKey);
//				if(fromKeyTypeSet == null){
//					result.put("message", "fromKeyType is not found in db");
//					result.put("code", 110009);
//					count.incrementAndGet();
//					return result;
//				}
//				if(!fromKeyTypeSet.contains(Long.valueOf(fromKeyType))){
//					result.put("message", "fromKeyType is not found in db");
//					result.put("code", 110009);
//					count.incrementAndGet();
//					return result;
//				}
//
//			}
//
//			if(StringUtils.isBlank(memo)){
//				result.put("message", "memo is null");
//				result.put("code", 110009);
//				count.incrementAndGet();
//				return result;
//			}
//			memo = moneyCenterService.setMemo(memo,gameType,gameId);
//			result.put("data", data);
//			data.put("username", username);
//			moneyService.transMoney(username,Integer.valueOf(siteId),hashCode,remitno,transType,remit,wagerCancel,result,data,fromKey,ip,fromKeyType,memo,MoneyConfigUtils.NOT_SINGLE);
//		}catch(Exception e){
//
//			logger.error(e.getMessage());
//			Integer code = (Integer) result.get("code");
//			if(code == null
//					|| code == 100000
//					|| code == 110015){
//				result.put("message", "system error");
//				result.put("code", 110001);
//			}
//		}
//		logger.info(">>转账结果:remitno:"+transMoneyParam.getRemitno()+","+result.toString());
//		count.incrementAndGet();
//		return result;
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//	public Vector<Object> getDataList() {
//		return dataList;
//	}
//
//
//
//	public void setDataList(Vector<Object> dataList) {
//		this.dataList = dataList;
//	}
//
//
//
//	public AtomicInteger getCount() {
//		return count;
//	}
//
//
//
//	public void setCount(AtomicInteger count) {
//		this.count = count;
//	}
//
//
//
//}
