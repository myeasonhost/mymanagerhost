package com.ds.money.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ds.money.entity.DsMemberMoneyLog;
import com.ds.money.entity.DsMoneyConfig;
import com.ds.money.service.MemberMoneyServiceImp;
import com.ds.money.service.MoneyCenterService;
import com.ds.money.untils.*;
import com.ds.money.vo.GetMoneyParam;
import com.ds.money.vo.MoneyLogByDateParam;
import com.ds.money.vo.MoneyLogParam;
import com.ds.money.vo.TransMoneyParam;
import net.sf.json.JSONException;
import org.apache.commons.lang.StringUtils;
import org.onetwo.common.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *
 * 项目名称：kg-money-api
 * 类名称：MemberMoneyController
 * 类描述：
 * 创建人：光光
 * 创建时间：2015-5-15 下午3:05:00
 * 修改人：光光
 * 修改时间：2015-5-15 下午3:05:00
 * 修改备注：
 * @version
 * 有很多需要改进的地方，比如多线程读取list表，可不可以不用临时表，表字段增加
 *
 */
@RestController
public class MemberMoneyController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	  //线程池
//    @Autowired
//    @Qualifier("taskExecutor")
//    private TaskExecutor	taskExecutor;
//	@Autowired
//	@Qualifier("taskExecutor")
//    private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private MemberMoneyServiceImp moneyService;
	@Autowired
	private MoneyCenterService moneyCenterService;
	/**
	 * 读取用户钱包金额
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getMoney",method=RequestMethod.POST)
	public @ResponseBody Object getMoney(HttpServletRequest request) {
		long start =System.currentTimeMillis();
	//	logger.info("===============调用查询接口=======================");
	//	RequestUtils.showParams(request);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		String username = request.getParameter("username");
		String fromKey = request.getParameter("fromKey");
		String key = request.getParameter("key");
		String siteId = request.getParameter("siteId");
		String hashCode = request.getParameter("hashCode");
		String ip = RequestUtils.getClientIp(request);
	//	logger.info(ip);
		data.put("username", username);
		result.put("data", data);
		if(StringUtils.isBlank(username)){
			logger.info("username is null");
			result.put("message", "username is null");
			result.put("code", 110009);
			return result;
		}
//		if(StringUtils.isBlank(fromKey)){
//			logger.info("fromKey is null");
//			result.put("message", "fromKey is null");
//			result.put("code", 110009);
//			return result;
//		}
		if(StringUtils.isBlank(key)){
			logger.info("key is null");
			result.put("message", "key is null");
			result.put("code", 110009);
			return result;
		}
		/*********************/
		if(StringUtils.isBlank(siteId)
				&& StringUtils.isBlank(hashCode)){
			logger.info("hashCode,siteId is null");
			result.put("message", "hashCode,siteId is null");
			result.put("code", 110009);
			return result;
		}
		if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
			logger.info("siteId is null");
			result.put("message", "siteId is wrong ");
			result.put("code", 110009);
			return result;
		}
		if(StringUtils.isNotBlank(hashCode)){
			Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
			if(map == null || map.size() == 0){
				logger.info("web config is wrong");
				result.put("message", "web config is wrong");
				result.put("code", 110001);
				return result;
			}
			if(map.get(hashCode) == null){
				logger.info( hashCode+"is not found");
				result.put("message", hashCode+"is not found");
				result.put("code", 110001);
				return result;
			}
			siteId = map.get(hashCode).toString();
		}
		/*********************/
		//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
		String md5key = MoneyConfigUtils.getKeyMd5(key);
		String toMd5 = EncryptUtils.toMD5(fromKey+username);
		if(!md5key.equals(toMd5)){
			logger.info("md5 is wrong");
			result.put("message", "md5 is wrong");
			result.put("code", 110009);
			return result;
		}
		Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
		DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
		if(moneyConfig == null){
			logger.info("fromKey is wrong");
			result.put("message", "fromKey is wrong");
			result.put("code", 110014);
			return result;
		}
//		if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
//				!moneyConfig.getIplist().contains(ip)){
//			result.put("message", "ip not allowed");
//			logger.info("此"+ip+"无权访问");
//			result.put("code", 110014);
//			return result;
//		}
		try{
			moneyService.getMoney(username,Integer.valueOf(siteId),result,data,hashCode);
		//	moneyService.getMoney(username);
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
//		logger.info(">>>>>>>>>>>>查询结果:time:"+(end-start)+">>>"+result.toString());
		return result;
	}



	@RequestMapping(value="getMoneyBatch",method=RequestMethod.POST)
	public @ResponseBody Object getMoneyBatch(HttpServletRequest request) {
		String jsonStr = RequestUtils.getParams(request);
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			//	List<TransMoneyParam> transMoneyParamList = JSON.parseObject(jsonStr,List.class);
				List<GetMoneyParam> getMoneyParamList = JSON.parseArray(jsonStr, GetMoneyParam.class);
				if(getMoneyParamList == null || getMoneyParamList.size()<1){
					result.put("message", "user list can not null");
					result.put("code", 110009);
					return result;
				}
				String ip = RequestUtils.getClientIp(request);
				logger.info("请求批量查询，ip："+ip);
				List<Object> dataList = new ArrayList<Object>();
				for (GetMoneyParam getMoneyParam : getMoneyParamList) {
					Object objResult = getMoneyBatch(getMoneyParam, ip);
					dataList.add(objResult);
				}
				result.put("data", dataList);
				result.put("message", "ok");
				result.put("code", 100000);
				logger.info("批量查询result:"+result);
			} catch (Exception e) {
				logger.error(e.getMessage());
				result.put("message", "system error");
				result.put("code", 110001);
			}
			return result;
	}
	private Object getMoneyBatch(GetMoneyParam getMoneyParam, String ip) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			String username = getMoneyParam.getUsername();
			String fromKey = getMoneyParam.getFromKey();
			String key = getMoneyParam.getKey();
			String siteId = getMoneyParam.getSiteId();
			String hashCode = getMoneyParam.getHashCode();

			data.put("username", username);
			result.put("data", data);
			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(fromKey)){
				result.put("message", "fromKey is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(key)){
				result.put("message", "key is null");
				result.put("code", 110009);
				return result;
			}
			/*********************/
			if(StringUtils.isBlank(siteId)
					&& StringUtils.isBlank(hashCode)){
				result.put("message", "hashCode,siteId is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
				result.put("message", "siteId is wrong");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(hashCode)){
				Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
				if(map == null || map.size() == 0){
					result.put("message", "web config is wrong");
					result.put("code", 110001);
					return result;
				}
				if(map.get(hashCode) == null){
					result.put("message", hashCode+" hashCode is not found");
					result.put("code", 110001);
					return result;
				}
				siteId = map.get(hashCode).toString();
			}
			/*********************/
			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username);
			if(!md5key.equals(toMd5)){
				result.put("message", "md5 is wrong");
				result.put("code", 110009);
				return result;
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "fromKey is wrong");
				result.put("code", 110014);
				return result;
			}
//			if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
//					!moneyConfig.getIplist().contains(ip)){
//				result.put("message", "ip not allowed");
//				logger.info("此"+ip+"无权访问");
//				result.put("code", 110014);
//				return result;
//			}

			moneyService.getMoney(username,Integer.valueOf(siteId),result,data,hashCode);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info(">>>>>>>>>>>>查询结果:"+result.toString());
		return result;
	}
	private Object transBatch(TransMoneyParam transMoneyParam, String ip){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		try{
			String username = transMoneyParam.getUsername();
			String remitno = transMoneyParam.getRemitno();
			String transType = transMoneyParam.getTransType();
			String remit = transMoneyParam.getRemit();
			String wagerCancel = transMoneyParam.getWagerCancel();
			String fromKey = transMoneyParam.getFromKey();
			String siteId = transMoneyParam.getSiteId();
			String hashCode = transMoneyParam.getHashCode();
			String key = transMoneyParam.getKey();
			String fromKeyType = transMoneyParam.getFromKeyType();
			String memo = transMoneyParam.getMemo();


			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(transType)
				||(!transType.equals("in")&&!transType.equals("out"))
				&&(!transType.equals("IN")&&!transType.equals("OUT"))){
				result.put("message", "transType error");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(remit)
					||!DataUtils.checkNum(remit)
					||Double.valueOf(remit)<0){//去掉等于 0 的条件
				result.put("message", "remit must be equal or than 0");
				result.put("code", 110009);
				return result;
			}
			if(!StringUtils.isBlank(wagerCancel)
					&&(!wagerCancel.equals("0")&&!wagerCancel.equals("1"))){
					result.put("message", "wagerCancel is wrong");
					result.put("code", 110009);
					return result;
		    }
			if(StringUtils.isBlank(fromKey)){
				result.put("message", "fromKey is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(key)){
				result.put("message", "key is null");
				result.put("code", 110009);
				return result;
			}

			if(StringUtils.isBlank(siteId)
					&& StringUtils.isBlank(hashCode)){
				result.put("message", "hashCode,siteId is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
				result.put("message", "siteId is wrong");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(hashCode)){
				Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
				if(map == null || map.size() == 0){
					result.put("message", "web config is wrong");
					result.put("code", 110001);
					return result;
				}
				if(map.get(hashCode) == null){
					result.put("message", hashCode+" hashCode is not found");
					result.put("code", 110001);
					return result;
				}
				siteId = map.get(hashCode).toString();
			}

			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username+remitno);
			if(!md5key.equals(toMd5)){
				result.put("message", "md5 is wrong");
				result.put("code", 110009);
				return result;
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "fromKey is wrong");
				result.put("code", 110014);
				return result;
			}
//			if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
//					!moneyConfig.getIplist().contains(ip)){
//				result.put("message", "ip is not allowed");
//				result.put("code", 110014);
//				return result;
//			}

			if(StringUtils.isBlank(fromKeyType) || !DataUtils.checkNum(fromKeyType)){
				result.put("message", "fromKeyType is wrond");
				result.put("code", 110009);
				return result;
			}else{
				Map<String,Set<Long>> fromKeyTypeMap = moneyCenterService.getFromKeyTypeMap();
				Set fromKeyTypeSet = fromKeyTypeMap.get(fromKey);
				if(fromKeyTypeSet == null){
					result.put("message", "fromKeyType is not found in db");
					result.put("code", 110009);
					return result;
				}
				if(!fromKeyTypeSet.contains(Long.valueOf(fromKeyType))){
					result.put("message", "fromKeyType is not found in db");
					result.put("code", 110009);
					return result;
				}

			}

			if(StringUtils.isBlank(memo)){
				result.put("message", "memo is null");
				result.put("code", 110009);
				return result;
			}

			result.put("data", data);
			data.put("username", username);

			moneyService.transMoney(username,Integer.valueOf(siteId),hashCode,remitno,transType,remit,wagerCancel,result,data,fromKey,ip,fromKeyType,memo, MoneyConfigUtils.NOT_SINGLE);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info(">>>>>>>>>>>>转账结果:"+result.toString());
		return result;
	}
	/**
	 * 批量处理转账
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transMoneyBatch")
	public @ResponseBody Object transMoneyBatch(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		String jsonStr = RequestUtils.getParams(request);
		logger.info("==========transMoneyBatch=========jsonStr:"+jsonStr);
		Map<String,Object> result = new HashMap<String,Object>();
		try {
		//	List<TransMoneyParam> transMoneyParamList = JSON.parseObject(jsonStr,List.class);
			List<TransMoneyParam> transMoneyParamList = JSON.parseArray(jsonStr, TransMoneyParam.class);
			if(transMoneyParamList == null || transMoneyParamList.size()<1){
				result.put("message", "user list can not null");
				result.put("code", 110009);
				return result;
			}
			String ip = RequestUtils.getClientIp(request);
			logger.info("请求批量转账>>>>>>>>，ip："+ip);
	//		List<Object> dataList = new ArrayList<Object>();
			Vector<Object> dataList = new Vector<Object>();
			AtomicInteger count  = new AtomicInteger();
			Integer size = transMoneyParamList.size();
			for (TransMoneyParam transMoneyParam : transMoneyParamList) {
//				while((taskExecutor.getActiveCount()+2) >= taskExecutor.getMaxPoolSize()) {
//					/*
//					 * 线程池需要预留两条线程来保证及时信息可以马上发送出去，
//					 * 因此当可用线程不够两条且信息不是及时信息时则本次不进行发送
//					 */
//					logger.info(taskExecutor.getActiveCount() + "条线程正在使用，剩余不足两条，休息片刻");
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						logger.info("休息片刻出错：" + e.getMessage());
//					}
//				}
//				logger.info("多线程执行中.......");
//				taskExecutor.execute(new TransThreadService(moneyService,moneyCenterService,transMoneyParam,ip,dataList, count));

			}

			while(count.intValue() != size || dataList.size() != size){
			//	logger.info("count:"+count+",size"+size);
				Thread.sleep(5);
			}

			result.put("data", dataList);
			result.put("message", "ok");
			result.put("code", 100000);
			logger.info("批量转账result:"+result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info("批量转账time:"+(end-start));
		return result;
	}

	@RequestMapping(value="transMoney",method=RequestMethod.POST)
	public @ResponseBody Object transMoney(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		logger.info("===============调用转账接口=======================");
		RequestUtils.showParams(request);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		try{
			String username = request.getParameter("username");
			String remitno = request.getParameter("remitno");//流水号
			String transType = request.getParameter("transType");//转入转出标识
			String remit = request.getParameter("remit");
			String wagerCancel = request.getParameter("wagerCancel");//注单是否取消 1：取消    0否

			String fromKey = request.getParameter("fromKey");
			String siteId = request.getParameter("siteId");
			String hashCode = request.getParameter("hashCode");

			String key = request.getParameter("key");
			String ip = RequestUtils.getClientIp(request);

			String fromKeyType = request.getParameter("fromKeyType");
			String memo = request.getParameter("memo");
			String gameType = request.getParameter("gameType");
			String gameId = request.getParameter("gameId");

			if(StringUtils.isNotBlank(gameType)){
				Map<String,Integer> gameTypeMap = moneyCenterService.getGameTypeMap();
				fromKeyType = gameTypeMap.get(gameType+"_"+transType.toUpperCase()).toString();
			}
			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(transType)
				||(!transType.equals("in")&&!transType.equals("out"))
				&&(!transType.equals("IN")&&!transType.equals("OUT"))){
				result.put("message", "transType is wrong");
				result.put("code", 110009);
				return result;
			}
			transType = transType.toLowerCase();
			remit = DataUtils.getStringNumber(remit);
			if(StringUtils.isBlank(remit)
					||!DataUtils.checkNum(remit)
					||Double.valueOf(remit)<0){ //去掉等于 0 的条件
				result.put("message", "remit must be >= 0");
				result.put("code", 110009);
				return result;
			}
			if(!StringUtils.isBlank(wagerCancel)
					&&(!wagerCancel.equals("0")&&!wagerCancel.equals("1"))){
					result.put("message", "wagerCancel is wrong");
					result.put("code", 110009);
					return result;
		    }
			if(StringUtils.isBlank(fromKey)){
				result.put("message", "fromKey is wrong");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(key)){
				result.put("message", "key is null");
				result.put("code", 110009);
				return result;
			}

			if(StringUtils.isBlank(siteId)
					&& StringUtils.isBlank(hashCode)){
				result.put("message", "hashCode,siteId is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
				result.put("message", "siteId is wrong");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(hashCode)){
				Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
				if(map == null || map.size() == 0){
					result.put("message", "web config is wrong");
					result.put("code", 110001);
					return result;
				}
				if(map.get(hashCode) == null){
					result.put("message", hashCode+"hashCode is wrong");
					result.put("code", 110001);
					return result;
				}
				siteId = map.get(hashCode).toString();
			}

			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username+remitno);
			if(!md5key.equals(toMd5)){
				result.put("message", "md5 is wrong");
				result.put("code", 110009);
				return result;
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "fromKey is wrong");
				result.put("code", 110014);
				return result;
			}
//			if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
//					!moneyConfig.getIplist().contains(ip)){
//				result.put("message", "ip is not allowed");
//				logger.info("此"+ip+"无权访问");
//				result.put("code", 110014);
//				return result;
//			}

			if(StringUtils.isBlank(fromKeyType) || !DataUtils.checkNum(fromKeyType)){
				result.put("message", "fromKeyType is wrong");
				result.put("code", 110009);
				return result;
			}else{
				Map<String,Set<Long>> fromKeyTypeMap = moneyCenterService.getFromKeyTypeMap();
				Set fromKeyTypeSet = fromKeyTypeMap.get(fromKey);
				if(fromKeyTypeSet == null){
					result.put("message", "fromKeyType is not found in db");
					result.put("code", 110009);
					return result;
				}
				if(!fromKeyTypeSet.contains(Long.valueOf(fromKeyType))){
					result.put("message", "fromKeyType is not found in db");
					result.put("code", 110009);
					return result;
				}

			}

			if(StringUtils.isBlank(memo)){
				result.put("message", "memo is null");
				result.put("code", 110009);
				return result;
			}
			memo = moneyCenterService.setMemo(memo,gameType,gameId);

			result.put("data", data);
			data.put("username", username);
			/**
			 * 为提高效率，批量转账和单个转账分开
			 */
			String transNum = "single";

			moneyService.transMoney(username,Integer.valueOf(siteId),hashCode,remitno,transType,remit,wagerCancel,result,data,fromKey,ip,fromKeyType,memo,transNum);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			Integer code = (Integer) result.get("code");
			if(code == null
					|| code == 100000
					|| code == 110015){
				result.put("message", "system error");
				result.put("code", 110001);
			}
		}
		long end =System.currentTimeMillis();
		logger.info(">>>单个转账结果:time:"+(end-start)+">>>>remitno:"+request.getParameter("remitno")+","+result.toString());
		return result;
	}
	@RequestMapping(value="getTotalBalance",method=RequestMethod.POST)
	public @ResponseBody Object getTotalBalance(HttpServletRequest request){
		logger.info("===============调用getTotalBalance接口====");
		RequestUtils.showParams(request);
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		JSONObject obj = new JSONObject();
		try {
			String siteId = request.getParameter("siteId");
			if(StringUtils.isBlank(siteId) || !DataUtils.checkNum(siteId)){
				result.put("message", "siteId param is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			obj.put("siteId", siteId);
			moneyService.getTotalBalance(result,obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info("===============调用getTotalBalance接口====,>>>>>>>>>result:"+result);
		return result.toString();
	}
	@RequestMapping(value="checkTransMoney",method=RequestMethod.POST)
	public @ResponseBody Object checkTransMoney(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		logger.info("===============调用chackTransMoney接口=======================");
		RequestUtils.showParams(request);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		try{
			String remitno = request.getParameter("remitno");//流水号
			String username = request.getParameter("username");
			String fromKey = request.getParameter("fromKey");
			String siteId = request.getParameter("siteId");
			String hashCode = request.getParameter("hashCode");

			String key = request.getParameter("key");
			//String ip = RequestUtils.getClientIp(request);
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(fromKey)){
				result.put("message", "fromKey is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(key)){
				result.put("message", "key is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(siteId)
					&& StringUtils.isBlank(hashCode)){
				result.put("message", "hashCode,siteId is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
				result.put("message", "siteId is wrong");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(hashCode)){
				Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
				if(map == null || map.size() == 0){
					result.put("message", "web config is wrong");
					result.put("code", 110001);
					return result;
				}
				if(map.get(hashCode) == null){
					result.put("message", hashCode+" hashCode is wrong");
					result.put("code", 110001);
					return result;
				}
				siteId = map.get(hashCode).toString();
			}
			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username+remitno);
			if(!md5key.equals(toMd5)){
				result.put("message", "md5 is wrong");
				result.put("code", 110009);
				return result;
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "fromKey is wrong");
				result.put("code", 110014);
				return result;
			}
//			if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
//					!moneyConfig.getIplist().contains(ip)){
//				result.put("message", "ip is not allowed");
//				logger.info("此"+ip+"无权访问");
//				result.put("code", 110014);
//				return result;
//			}
			result.put("data", data);
			data.put("username", username);

			moneyService.checktransMoney(username,Integer.valueOf(siteId),remitno,result,data,fromKey,hashCode);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info(">>>>>>>>>>>>查询转账结果:time:"+(end-start)+result.toString());
		return result;
	}

	@RequestMapping(value="memberMoneyLog",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public @ResponseBody
	Object memberMoneyLog(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		logger.info("===============调用memberMoneyLog接口=======================");
		RequestUtils.showParams(request);
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			String jsonStr = RequestUtils.getParams(request);
			JSONObject param = JSON.parseObject(jsonStr);

			String username = param.getString("username");
			String remitno = param.getString("remitno");

			String beginTime = param.getString("beginTime");
			String endTime = param.getString("endTime");
			String page = param.getString("page");
			String pageSize = param.getString("pageSize");
			String fromKey = param.getString("fromKey");
			String key = param.getString("key");
			String siteId = param.getString("siteId");
			String hashCode = param.getString("hashCode");
			String fromKeyType = param.getString("fromKeyType");
			String agentLevel = param.getString("agentLevel");
			String agentName = param.getString("agentName");

			String fromKeyTypeIsTotal = param.getString("fromKeyTypeIsTotal");// 否是统计现金流
			String userInfoIsDetail = param.getString("userInfoIsDetail");// 是否查询用户的详细信息

			// String ip = getTCPClientIp();
			if (StringUtils.isNotBlank(beginTime)) {
				if (!DateUtils.isValidDate(beginTime)) {
					result.put("message", "beginTime is wrong");
					result.put("code", 110009);
					return result.toString();
				}
			}
			if (!StringUtils.isNotBlank(endTime)) {
				if (DateUtils.isValidDate(endTime)) {
					result.put("message", "endTime is wrong");
					result.put("code", 110009);
					return result.toString();
				}
			}

			if (StringUtils.isNotBlank(page) && !DataUtils.checkNum(page)) {
				result.put("message", "page param is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			if (StringUtils.isNotBlank(pageSize)
					&& !DataUtils.checkNum(pageSize)) {
				result.put("message", "pageSize param is wrong");
				result.put("code", 110009);
				return result.toString();
			}

			if (StringUtils.isBlank(fromKey)) {
				result.put("message", "fromKey is null");
				result.put("code", 110009);
				return result.toString();
			}
			if (StringUtils.isBlank(key)) {
				result.put("message", "key is null");
				result.put("code", 110009);
				return result.toString();
			}
			if (StringUtils.isBlank(siteId) && StringUtils.isBlank(hashCode)) {
				result.put("message", "hashCode,siteId is all null");
				result.put("code", 110009);
				return result.toString();
			}
			if (StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)) {
				result.put("message", "siteId is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			if (StringUtils.isNotBlank(hashCode)) {
				Map<String, Integer> map = moneyCenterService
						.getSiteHashcodeMap();
				if (map == null || map.size() == 0) {
					result.put("message", "site config is null");
					result.put("code", 110001);
					return result.toString();
				}
				if (map.get(hashCode) == null) {
					result.put("message", hashCode + "is not found");
					result.put("code", 110001);
					return result.toString();
				}
				siteId = map.get(hashCode).toString();
			}
			if (StringUtils.isNotBlank(fromKeyTypeIsTotal)
					&& fromKeyTypeIsTotal.equals("1")) {
				if (StringUtils.isBlank(fromKeyType)) {
					result.put("message",
							"if fromKeyTypeIsTotal param not null, fromKeyType param can not null");
					result.put("code", 110001);
					return result.toString();

				}
			}
			Page<DsMemberMoneyLog> pagation = new Page<DsMemberMoneyLog>();
			pagation.setPageNo(page == null ? 1 : Integer.valueOf(page));
			pagation.setPageSize(StringUtils.isBlank(pageSize) ? 20 : Integer
					.valueOf(pageSize));
			MoneyLogParam queryParam = new MoneyLogParam();
			queryParam.setUsername(username);
			queryParam.setSiteId(Integer.valueOf(siteId));
			if (StringUtils.isNotBlank(beginTime)) {
				queryParam.setBeginTime(DateUtils.getGMT4Date(beginTime, "yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotBlank(endTime)) {
				queryParam.setEndTime(DateUtils.getGMT4Date(endTime, "yyyy-MM-dd HH:mm:ss"));
			}
			queryParam.setFromKeyTypeList(formatFromKeyType(fromKeyType));
			queryParam.setFromKeyTypeIsTotal(fromKeyTypeIsTotal);
			queryParam.setUserInfoIsDetail(userInfoIsDetail);
			queryParam.setAgentLevel(agentLevel);
			queryParam.setAgentName(agentName);
			queryParam.setRemitno(remitno);
			moneyService.memberMoneyLog(result, data, queryParam, pagation);
			result.put("message", "ok");
			result.put("code", 100000);
		} catch (JSONException e) {
			result.put("message", e.getMessage());
			result.put("code", 110009);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info(">>>>>>>>>>>>调用现金流日志接:time:"+(end-start)+result.toString());
		return JSON.toJSONString(result, SerializerFeature.BrowserCompatible);
	}

	@RequestMapping(value="memberMoneyLogByLevel",method=RequestMethod.POST)
	public @ResponseBody Object memberMoneyLogByLevel(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		logger.info("===============调用memberMoneyLog接口=======================");
		RequestUtils.showParams(request);
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			String jsonStr = RequestUtils.getParams(request);
			JSONObject param = JSON.parseObject(jsonStr);

			String username = param.getString("username");
			String beginTime = param.getString("beginTime");
			String endTime = param.getString("endTime");
			String page = param.getString("page");
			String pageSize = param.getString("pageSize");
			String fromKey = param.getString("fromKey");
			String key = param.getString("key");
			String siteId = param.getString("siteId");
			String hashCode = param.getString("hashCode");
			String fromKeyType = param.getString("fromKeyType");

			String fromKeyTypeIsTotal = param.getString("fromKeyTypeIsTotal");//否是统计现金流
			String userInfoIsDetail = param.getString("userInfoIsDetail");//是否查询用户的详细信息

		//	String ip = getTCPClientIp();
			if(StringUtils.isNotBlank(beginTime)){
				if(!DateUtils.isValidDate(beginTime)){
					result.put("message", "beginTime is wrong");
					result.put("code", 110009);
					return result.toString();
				}
			}
			if(!StringUtils.isNotBlank(endTime)){
				if(DateUtils.isValidDate(endTime)){
					result.put("message", "endTime is wrong");
					result.put("code", 110009);
					return result.toString();
				}
			}

			if (StringUtils.isNotBlank(page) && !DataUtils.checkNum(page)) {
				result.put("message", "page param is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			if (StringUtils.isNotBlank(pageSize) && !DataUtils.checkNum(pageSize)) {
				result.put("message", "pageSize param is wrong");
				result.put("code", 110009);
				return result.toString();
			}

			if(StringUtils.isBlank(fromKey)){
				result.put("message", "fromKey is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(key)){
				result.put("message", "key is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(siteId)
					&& StringUtils.isBlank(hashCode)){
				result.put("message", "hashCode,siteId is all null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
				result.put("message", "siteId is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isNotBlank(hashCode)){
				Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
				if(map == null || map.size() == 0){
					result.put("message", "site config is null");
					result.put("code", 110001);
					return result.toString();
				}
				if(map.get(hashCode) == null){
					result.put("message", hashCode+"is not found");
					result.put("code", 110001);
					return result.toString();
				}
				siteId = map.get(hashCode).toString();
			}
			if(StringUtils.isNotBlank(fromKeyTypeIsTotal) && fromKeyTypeIsTotal.equals("1")){
				if(StringUtils.isBlank(fromKeyType)){
					result.put("message", "if fromKeyTypeIsTotal param not null, fromKeyType param can not null");
					result.put("code", 110001);
					return result.toString();

				}
			}
			Page<DsMemberMoneyLog> pagation = new Page<DsMemberMoneyLog>();
			pagation.setPageNo(page == null?1:Integer.valueOf(page));
			pagation.setPageSize(StringUtils.isBlank(pageSize)?20:Integer.valueOf(pageSize));
			MoneyLogParam queryParam = new MoneyLogParam();
			queryParam.setUsername(username);
			queryParam.setSiteId(Integer.valueOf(siteId));
			if(StringUtils.isNotBlank(beginTime)){
				queryParam.setBeginTime(DateUtils.getGMT4Date(beginTime, "yyyy-MM-dd HH:mm:ss"));
			}
			if(StringUtils.isNotBlank(endTime)){
				queryParam.setEndTime(DateUtils.getGMT4Date(endTime, "yyyy-MM-dd HH:mm:ss"));
			}
			queryParam.setFromKeyTypeList(formatFromKeyType(fromKeyType));
			queryParam.setFromKeyTypeIsTotal(fromKeyTypeIsTotal);
			queryParam.setUserInfoIsDetail(userInfoIsDetail);
			moneyService.memberMoneyLog(result,data,queryParam,pagation);
			result.put("message", "ok");
			result.put("code", 100000);
		}catch(JSONException e){
			result.put("message", e.getMessage());
			result.put("code", 110009);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info(">>>>>>>>>>>>调用现金流日志接口:time:"+(end-start)+result.toString());
		return JSON.toJSONString(result, SerializerFeature.BrowserCompatible);
	}
	@RequestMapping(value="moneyLogByDate",method=RequestMethod.POST)
	public @ResponseBody Object moneyLogByDate(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		logger.info("===============调用memberMoneyLog接口=======================");
		RequestUtils.showParams(request);
		JSONObject result = new JSONObject();
		try {
			String jsonStr = RequestUtils.getParams(request);
			JSONObject param = JSON.parseObject(jsonStr);

			String beginTime = param.getString("beginTime");
			String endTime = param.getString("endTime");
//			String key = result.getString("key");
			String siteId = param.getString("siteId");
			String fromKeyType = param.getString("fromKeyType");

			if(StringUtils.isBlank(beginTime)){
				result.put("message", "beginTime is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(endTime)){
				result.put("message", "endTime is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isNotBlank(beginTime)){
				if(!DateUtils.isValidDate(beginTime,"yyyy-MM-dd")){
					result.put("message", "beginTime is wrong");
					result.put("code", 110009);
					return result.toString();
				}
			}
			if(!StringUtils.isNotBlank(endTime)){
				if(DateUtils.isValidDate(endTime,"yyyy-MM-dd")){
					result.put("message", "endTime is wrong");
					result.put("code", 110009);
					return result.toString();
				}
			}
			if(StringUtils.isBlank(siteId)){
				result.put("message", "siteId is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
				result.put("message", "siteId is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			MoneyLogByDateParam queryParam  = new MoneyLogByDateParam();
			if(StringUtils.isNotBlank(beginTime)){
				queryParam.setBeginTime(DateUtils.getGMT4Date(beginTime+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			}
			if(StringUtils.isNotBlank(endTime)){
				queryParam.setEndTime(DateUtils.getGMT4Date(endTime+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			}
			queryParam.setFromKeyTypeList(formatFromKeyType(fromKeyType));
			queryParam.setSiteId(Integer.valueOf(siteId));
			moneyService.moneyLogByDate(result,queryParam);
			result.put("message", "ok");
			result.put("code", 100000);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info(">>>>>>>>>>>>moneyLogByDate接口:time:"+(end-start)+result.toString());
		return JSON.toJSONString(result, SerializerFeature.BrowserCompatible);
	}
//	@RequestMapping(value="updateState")
//	public @ResponseBody Object updateState() {
//
//	}
	@RequestMapping(value="getTransMoneyInfo",method=RequestMethod.POST)
	public @ResponseBody Object getTransMoneyInfo(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		logger.info("===============调用查询接口=======================");
		RequestUtils.showParams(request);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		String username = request.getParameter("username");
		//String key = request.getParameter("key");
		String siteId = request.getParameter("siteId");
		String hashCode = request.getParameter("hashCode");
		String remitno = request.getParameter("remitno");//流水号
		try {
			if (StringUtils.isBlank(siteId) && StringUtils.isBlank(hashCode)) {
				result.put("message", "hashCode,siteId is null");
				result.put("code", 110009);
				return result;
			}
			if (StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)) {
				result.put("message", "siteId is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
				return result;
			}
			moneyService.getTransMoneyInfo(result,data,username,Integer.valueOf(siteId),remitno);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info(">>>getTransMoneyInfo结果:time:"+(end-start)+">>>>remitno:"+request.getParameter("remitno")+","+result.toString());
		return result;

	}

	@RequestMapping(value="updateMoneyLog",method=RequestMethod.POST)
	public @ResponseBody Object updateMoneyLog(HttpServletRequest request) {
		long start =System.currentTimeMillis();
		logger.info("===============调用updateMoneyLog接口=======================");
		RequestUtils.showParams(request);
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			String jsonStr = RequestUtils.getParams(request);
			JSONObject param = JSON.parseObject(jsonStr);
			String username = param.getString("username");
			String siteId = param.getString("siteId");
			String hashCode = param.getString("hashCode");
			String remitno = param.getString("remitno");//流水号
			String memo = param.getString("memo");//备注
			if (StringUtils.isBlank(siteId) && StringUtils.isBlank(hashCode)) {
				result.put("message", "hashCode,siteId is null");
				result.put("code", 110009);
				return result;
			}
			if (StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)) {
				result.put("message", "siteId is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(memo)){
				result.put("message", "memo is null");
				result.put("code", 110009);
				return result;
			}
			moneyService.updateMoneyLog(result,data,username,Integer.valueOf(siteId),remitno,memo);

		} catch (Exception e) {
			logger.error("updateMoneyLog error", e);
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info(">>>updateMoneyLog结果:time:"+(end-start)+">>>>remitno:"+request.getParameter("remitno")+","+result.toString());
		return result;

	}

	@RequestMapping(value="/setMemberData",method=RequestMethod.POST)
	public @ResponseBody Object setMemberData(HttpServletRequest request) {
		logger.info("===============调用setMemberData接口=======================");
		long start =System.currentTimeMillis();
		RequestUtils.showParams(request);

		String jsonStr = RequestUtils.getParams(request);
		JSONObject param = JSON.parseObject(jsonStr);
		logger.info(param.toJSONString());

		String username = param.getString("username");
		String siteId = param.getString("siteId");
		String agents = param.getString("agents");
		String world = param.getString("world");
		String corprator = param.getString("corprator");
		String superior = param.getString("superior");
		String company = param.getString("company");
		String ip = request.getRemoteAddr();
		logger.info("请求者IP："+ip);
	//	Map<String,Object> result = new HashMap<String,Object>();
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			if(StringUtils.isBlank(username)){
				result.put("message", "username param  cant not null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(siteId) || !DataUtils.checkNum(siteId)){
				result.put("message", "siteId param is wrong");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(agents)){
				result.put("message", "agents param  cant not null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(world)){
				result.put("message", "world param  cant not null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(corprator)){
				result.put("message", "corprator param  cant not null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(superior)){
				result.put("message", "superior param  cant not null");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(company)){
				result.put("message", "company param  cant not null");
				result.put("code", 110009);
				return result;
			}
			moneyService.setMemberData(result,username,siteId,agents,world,corprator,superior,company);
			result.put("message", "ok");
			result.put("code", 100000);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info("time:"+(end-start)+"username："+username+",siteId:"+siteId+",>>>>>>>>>result:"+result);
		return result;
	}
//	@RequestMapping(value="/updateUserState",method=RequestMethod.POST)
//	public @ResponseBody Object updateUserState(HttpServletRequest request){
////		logger.info("===============调用updateUserState接口=======================");
////		long start =System.currentTimeMillis();
////		RequestUtils.showParams(request);
////
////		String jsonStr = RequestUtils.getParams(request);
////		com.alibaba.fastjson.JSONObject param = JSON.parseObject(jsonStr);
////		logger.info(param.toJSONString());
////
////		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
////		try {
////			String username = param.getString("username");
////			String siteId = param.getString("siteId");
////			String agentLevel = param.getString("agentLevel");
////			String agentName = param.getString("agentName");
////			String state = param.getString("state");
////			if(StringUtils.isBlank(agentLevel) && StringUtils.isBlank(username)){
////				result.put("message", "username param  cant not null");
////				result.put("code", 110009);
////				return result.toString();
////			}
////			if(StringUtils.isBlank(siteId) || !DataUtils.checkNum(siteId)){
////				result.put("message", "siteId param is wrong");
////				result.put("code", 110009);
////				return result.toString();
////			}
////			if(StringUtils.isBlank(state) || !Arrays.asList(new Object[]{"-50","0","50"}).contains(state)){
////				result.put("message", "state param  is wrong");
////				result.put("code", 110009);
////				return result.toString();
////			}
////			if(StringUtils.isNotBlank(agentLevel) && !Arrays.asList(new Object[]{"agents","world","corprator","superior","company"}).contains(agentLevel)){
////				result.put("message", "agentLevel param  is wrong,must be agents world corprator superior company");
////				result.put("code", 110009);
////				return result.toString();
////			}
////			if(StringUtils.isNotBlank(agentLevel) && StringUtils.isBlank(agentName)){
////				result.put("message", "if agentLevel is not null, agentName can not null");
////				result.put("code", 110009);
////				return result.toString();
////			}
////			moneyService.updateUserState(result,param);
////
////		} catch (Exception e) {
////			e.printStackTrace();
////			logger.error(e.getMessage());
////			result.put("message", "system error");
////			result.put("code", 110001);
////		}
////		long end =System.currentTimeMillis();
////		logger.info("===============调用updateUserState接口====,>>>>>>>>time:"+(end-start)+">result:"+result);
////		return result.toString();
//	}

	@RequestMapping(value="/changeAgentMoney",method=RequestMethod.POST)
	public @ResponseBody Object changeAgentMoney(HttpServletRequest request){
		logger.info("===============调用changeAgentMoney接口=======================");
		long start =System.currentTimeMillis();
		RequestUtils.showParams(request);

		String jsonStr = RequestUtils.getParams(request);
		JSONObject param = JSON.parseObject(jsonStr);
		logger.info(param.toJSONString());
		String ip = RequestUtils.getClientIp(request);
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			String siteId = param.getString("siteId");
			String username = param.getString("username");
			String agentLevel = param.getString("agentLevel");
			String agentName = param.getString("agentName");
			String remitno = param.getString("remitno");//转账唯一标识
			String remit = param.getString("remit");
			String userLevel = param.getString("userLevel");//低级别
			String agentTransType = param.getString("agentTransType");//in 转入代理，out代理转出
			if(StringUtils.isBlank(username)){
				result.put("message", "username param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(agentLevel)){
				result.put("message", "agentLevel param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(userLevel)){
				result.put("message", "userLevel param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(siteId) || !DataUtils.checkNum(siteId)){
				result.put("message", "siteId param is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno param  is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isNotBlank(agentLevel) && !Arrays.asList(new Object[]{"agents","world","corprator","superior","company"}).contains(agentLevel)){
				result.put("message", "agentLevel param  is wrong,must be agents world corprator superior company");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isNotBlank(userLevel) && !Arrays.asList(new Object[]{"agents","world","corprator","superior","username"}).contains(userLevel)){
				result.put("message", "userLevel param  is wrong,must be agents world corprator superior username");
				result.put("code", 110009);
				return result.toString();
			}
			if(agentLevel.equals(userLevel)){
				result.put("message", "userLevel param can not equals agentLevel");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(agentName)){
				result.put("message", "agentName can not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(agentTransType)
					||(!agentTransType.equals("in")&&!agentTransType.equals("out"))
					&&(!agentTransType.equals("IN")&&!agentTransType.equals("OUT"))){
					result.put("message", "agentTransType is wrong");
					result.put("code", 110009);
					return result;
				}
			agentTransType = agentTransType.toLowerCase();
			remit = DataUtils.getStringNumber(remit);
			if(StringUtils.isBlank(remit)
					||!DataUtils.checkNum(remit)
					||Double.valueOf(remit)<0){ //去掉等于 0 的条件
				result.put("message", "remit must be >= 0");
				result.put("code", 110009);
				return result;
			}
			moneyService.changeAgentMoney(result,param,ip);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info("===============调用changeAgentMoney接口====,>>>>>>>>time:"+(end-start)+">result:"+result);
		return result.toString();
	}

	@RequestMapping(value="resetData")
	public @ResponseBody Object resetData() {
		moneyCenterService.initMoneyConfigList();
		moneyCenterService.initSiteHashMapList();
		moneyCenterService.initIPList();
		moneyCenterService.initGameInfo();
		moneyCenterService.initgameTypeMap();
		return "ok";
	}
	@RequestMapping(value="shutdown")
	public @ResponseBody Object shutdown() {
		moneyCenterService.setRunning(false);
		return "ok";
	}
	private List<Integer> formatFromKeyType(String fromKeyTypeStr){
		if(StringUtils.isBlank(fromKeyTypeStr)){
			return null;
		}
		String[] tempArr = fromKeyTypeStr.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String fromKeyType : tempArr) {
			list.add(Integer.valueOf(fromKeyType));
		}
		return list;
	}
}
