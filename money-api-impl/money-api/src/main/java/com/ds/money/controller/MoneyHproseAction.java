package com.ds.money.controller;

import hprose.server.HproseTcpServer;
import hprose.server.TcpContext;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.onetwo.common.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ds.money.entity.DsMemberMoneyLog;
import com.ds.money.entity.DsMoneyConfig;
import com.ds.money.service.MemberMoneyServiceImp;
import com.ds.money.service.MoneyCenterService;
import com.ds.money.untils.DataUtils;
import com.ds.money.untils.DateUtils;
import com.ds.money.untils.EncryptUtils;
import com.ds.money.untils.MoneyConfigUtils;
import com.ds.money.untils.RequestUtils;
import com.ds.money.vo.MoneyLogByDateParam;
import com.ds.money.vo.MoneyLogParam;
import com.ds.money.vo.TransMoneyParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * 项目名称：kg-money-api
 * 类名称：MoneyHproseAction
 * 类描述：
 * 创建人：光光
 * 创建时间：2015-5-16 下午3:11:03
 * 修改人：光光
 * 修改时间：2015-5-16 下午3:11:03
 * 修改备注：
 * @version
 *
 */
@RestController
public class MoneyHproseAction {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberMoneyServiceImp moneyService;
	private MoneyCenterService moneyCenterService;


	public MoneyHproseAction(MemberMoneyServiceImp moneyService,
			MoneyCenterService moneyCenterService) {
		super();
		this.moneyService = moneyService;
		this.moneyCenterService = moneyCenterService;
	}

	public String getMoney(String paramjsonStr){
		logger.info("===============调用查询接口===="+paramjsonStr);
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try{
			JSONObject paramJson = JSONObject.fromObject(paramjsonStr);
			if(paramJson == null){
				result.put("message", "username is null");
				result.put("code", 110009);
				return result.toString();
			}
			String username = getString(paramJson,"username");
			String fromKey = getString(paramJson,"fromKey");
			String key = getString(paramJson,"key");

			String siteId = getString(paramJson,"siteId");
			String hashCode = getString(paramJson,"hashCode");



			String ip = getTCPClientIp();
			logger.info("请求者IP......"+ip);
			data.put("username", username);

			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
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

			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username);
			if(!md5key.equals(toMd5)){
				result.put("message", "authentication fail");
				result.put("code", 110009);
				return result.toString();
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "authentication is not allowed");
				result.put("code", 110014);
				return result.toString();
			}
			Set<String> ipSet = moneyCenterService.getIpSet();
			if(!ipSet.contains(ip)){
				result.put("message", "ip is not allowed");
				logger.info(ip+"not allowed");
				result.put("code", 110014);
				return result.toString();
			}
			moneyService.getMoney(username,Integer.valueOf(siteId),result,data,hashCode);
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}

		logger.info(">>>>>>>>>>>>查询结果:"+result.toString());
		return result.toString();
	}

	public String transMoney(String paramjsonStr) {
		logger.info("====调用转账接口======paramjsonStr:"+paramjsonStr);
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try{
			JSONObject paramJson = JSONObject.fromObject(paramjsonStr);
			String username = getString(paramJson,"username");
			String remitno = getString(paramJson,"remitno");//流水号
			String transType = getString(paramJson,"transType");//转入转出标识
			String remit = getString(paramJson,"remit");//存取款金额，必须为整数
			String wagerCancel = getString(paramJson,"wagerCancel");//注单是否取消 1：取消    0否
			String fromKey = getString(paramJson,"fromKey");
			String key = getString(paramJson,"key");
			String siteId = getString(paramJson,"siteId");
			String hashCode = getString(paramJson,"hashCode");

			String fromKeyType = getString(paramJson,"fromKeyType");
			String memo = getString(paramJson,"memo");


			String ip = getTCPClientIp();
			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
					return result.toString();
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno is null");
				result.put("code", 110009);
					return result.toString();
			}
			if(StringUtils.isBlank(transType)
				||(!transType.equals("in")&&!transType.equals("out"))){
				result.put("message", "transType format is wrong");
				result.put("code", 110009);
					return result.toString();
			}
			if(StringUtils.isBlank(remit)
					||!DataUtils.checkNum(remit)
					||Double.valueOf(remit)<0){
				result.put("message", "remit must be than 0");
				result.put("code", 110009);
				return result.toString();
			}
			if(!StringUtils.isBlank(wagerCancel)
					&&(!wagerCancel.equals("0")&&!wagerCancel.equals("1"))){
					result.put("message", "wagerCancel format is wrong");
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

			if(StringUtils.isBlank(fromKeyType) || !DataUtils.checkNum(fromKeyType)){
				result.put("message", "fromKeyType is not right");
				result.put("code", 110009);
				return result.toString();
			}else{
				Map<String,Set<Long>> fromKeyTypeMap = moneyCenterService.getFromKeyTypeMap();
				Set fromKeyTypeSet = fromKeyTypeMap.get(fromKey);
				if(fromKeyTypeSet == null){
					result.put("message", "fromKeyType is not found in db");
					result.put("code", 110009);
					return result.toString();
				}
				if(!fromKeyTypeSet.contains(Long.valueOf(fromKeyType))){
					result.put("message", "fromKeyType is not found in db");
					result.put("code", 110009);
					return result.toString();
				}

			}


			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username+remitno);
			if(!md5key.equals(toMd5)){
				result.put("message", "authentication fail");
				result.put("code", 110009);
					return result.toString();
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "authentication is not allowed");
				result.put("code", 110014);
					return result.toString();
			}
			Set<String> ipSet = moneyCenterService.getIpSet();
			if(!ipSet.contains(ip)){
				result.put("message", "ip is not allowed");
				logger.info(ip+"not allowed");
				result.put("code", 110014);
				return result.toString();
			}


			data.put("username", username);

			moneyService.transMoney(username,Integer.valueOf(siteId),hashCode,remitno,transType,remit,wagerCancel,result,data,fromKey,ip,fromKeyType,memo,MoneyConfigUtils.SINGLE);
		}catch(JSONException e){
			result.put("message", e.getMessage());
			result.put("code", 110009);
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info(">>>>>>>>>>>>转账结果:"+result.toString());
		return result.toString();
	}

	public String transMoneyBatch(String paramjsonStr){
		logger.info("====调用transMoneyBatch转账接口======paramjsonStr:"+paramjsonStr);
		JSONObject result = new JSONObject();
		//JSONObject data = new JSONObject();
		try {
			List<TransMoneyParam> transMoneyParamList = JSON.parseArray(paramjsonStr, TransMoneyParam.class);
			if(transMoneyParamList == null || transMoneyParamList.size()<1){
				result.put("message", "user list can not null");
				result.put("code", 110009);
				return result.toString();
			}
			String ip = getTCPClientIp();
			logger.info("请求批量转账，ip："+ip);
			List<Object> dataList = new ArrayList<Object>();
			for (TransMoneyParam transMoneyParam : transMoneyParamList) {
				Object objResult = transBatch(transMoneyParam, ip);
				dataList.add(objResult);
			}
			result.put("data", dataList);
			result.put("message", "ok");
			result.put("code", 100000);
			logger.info("批量转账result:"+result);
		} catch (Exception e) {

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info(">>>>>>>>>>>>转账结果:"+result.toString());
		return result.toString();

	}
	private Object transBatch(TransMoneyParam transMoneyParam,String ip){
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
				result.put("message", "username参数不能为空");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno参数不能为空");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(transType)
				||(!transType.equals("in")&&!transType.equals("out"))
				&&(!transType.equals("IN")&&!transType.equals("OUT"))){
				result.put("message", "transType参数错误");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(remit)
					||!DataUtils.checkNum(remit)
					||Double.valueOf(remit)<=0){
				result.put("message", "remit必须大于0");
				result.put("code", 110009);
				return result;
			}
			if(!StringUtils.isBlank(wagerCancel)
					&&(!wagerCancel.equals("0")&&!wagerCancel.equals("1"))){
					result.put("message", "wagerCancel参数错误");
					result.put("code", 110009);
					return result;
		    }
			if(StringUtils.isBlank(fromKey)){
				result.put("message", "fromKey参数不能为空");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isBlank(key)){
				result.put("message", "key参数不能为空");
				result.put("code", 110009);
				return result;
			}

			if(StringUtils.isBlank(siteId)
					&& StringUtils.isBlank(hashCode)){
				result.put("message", "hashCode,siteId参数不能同时为空");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
				result.put("message", "siteId参数错误");
				result.put("code", 110009);
				return result;
			}
			if(StringUtils.isNotBlank(hashCode)){
				Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
				if(map == null || map.size() == 0){
					result.put("message", "网站配置为空，请联系程序员");
					result.put("code", 110001);
					return result;
				}
				if(map.get(hashCode) == null){
					result.put("message", hashCode+"对应网站未找到");
					result.put("code", 110001);
					return result;
				}
				siteId = map.get(hashCode).toString();
			}

			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username+remitno);
			if(!md5key.equals(toMd5)){
				result.put("message", "鉴权失败");
				result.put("code", 110009);
				return result;
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "无权访问");
				result.put("code", 110014);
				return result;
			}
			Set<String> ipSet = moneyCenterService.getIpSet();
			if(!ipSet.contains(ip)){
				result.put("message", "ip is not allowed");
				logger.info(ip+"not allowed");
				result.put("code", 110014);
				return result.toString();
			}

			if(StringUtils.isBlank(fromKeyType) || !DataUtils.checkNum(fromKeyType)){
				result.put("message", "fromKeyType参数错误");
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
				result.put("message", "memo参数不能为空");
				result.put("code", 110009);
				return result;
			}

			result.put("data", data);
			data.put("username", username);

			moneyService.transMoney(username,Integer.valueOf(siteId),hashCode,remitno,transType,remit,wagerCancel,result,data,fromKey,ip,fromKeyType,memo,MoneyConfigUtils.NOT_SINGLE);
		}catch(Exception e){

			logger.error(e.getMessage());
			result.put("message", "发生系统错误");
			result.put("code", 110001);
		}
		logger.info(">>>>>>>>>>>>转账结果:"+result.toString());
		return result;
	}



	public String memberMoneyLog(String paramjsonStr) {
		logger.info("====调用现金流日志接口======paramjsonStr:"+paramjsonStr);
		com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
		com.alibaba.fastjson.JSONObject data = new com.alibaba.fastjson.JSONObject();
		try{
			JSONObject paramJson = JSONObject.fromObject(paramjsonStr);
			String username = getString(paramJson,"username");
			String remitno = getString(paramJson,"remitno");

			String beginTime = getString(paramJson,"beginTime");
			String endTime = getString(paramJson,"endTime");
			String page = getString(paramJson,"page");
			String pageSize = getString(paramJson,"pageSize");
			String fromKey = getString(paramJson,"fromKey");
			String key = getString(paramJson,"key");
			String siteId = getString(paramJson,"siteId");
			String hashCode = getString(paramJson,"hashCode");
			String fromKeyType = getString(paramJson,"fromKeyType");
			String agentLevel = getString(paramJson,"agentLevel");
			String agentName = getString(paramJson,"agentName");

			String fromKeyTypeIsTotal = getString(paramJson,"fromKeyTypeIsTotal");//否是统计现金流
			String userInfoIsDetail = getString(paramJson,"userInfoIsDetail");//是否查询用户的详细信息

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
			//	queryParam.setBeginTime(DateUtil.addHours(DateUtil.parseByPatterns(beginTime, "yyyy-MM-dd HH:mm:ss"),12));
				queryParam.setBeginTime(DateUtils.getGMT4Date(beginTime, "yyyy-MM-dd HH:mm:ss"));
			}
			if(StringUtils.isNotBlank(endTime)){
			//	queryParam.setEndTime(DateUtil.addHours(DateUtil.parseByPatterns(endTime, "yyyy-MM-dd HH:mm:ss"),12));
				queryParam.setEndTime(DateUtils.getGMT4Date(endTime, "yyyy-MM-dd HH:mm:ss"));
			}
			queryParam.setFromKeyTypeList(formatFromKeyType(fromKeyType));
			queryParam.setFromKeyTypeIsTotal(fromKeyTypeIsTotal);
			queryParam.setUserInfoIsDetail(userInfoIsDetail);
			queryParam.setAgentLevel(agentLevel);
			queryParam.setAgentName(agentName);
			queryParam.setRemitno(remitno);
			moneyService.memberMoneyLog(result,data,queryParam,pagation);
			result.put("message", "ok");
			result.put("code", 100000);
		}catch(JSONException e){
			result.put("message", e.getMessage());
			result.put("code", 110009);
		}catch(Exception e){

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info("调用现金流日志接口result:"+result.toString());
		return JSON.toJSONString(result, SerializerFeature.BrowserCompatible);
	}

	public String memberMoneyLogByLevel(String paramjsonStr) {
		logger.info("====调用现金流日志memberMoneyLogByLevel接口======paramjsonStr:"+paramjsonStr);
		com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
		com.alibaba.fastjson.JSONObject data = new com.alibaba.fastjson.JSONObject();
		try{
			JSONObject paramJson = JSONObject.fromObject(paramjsonStr);
			String username = getString(paramJson,"username");
			String beginTime = getString(paramJson,"beginTime");
			String endTime = getString(paramJson,"endTime");
			String page = getString(paramJson,"page");
			String pageSize = getString(paramJson,"pageSize");
			String fromKey = getString(paramJson,"fromKey");
			String key = getString(paramJson,"key");
			String siteId = getString(paramJson,"siteId");
			String hashCode = getString(paramJson,"hashCode");
			String fromKeyType = getString(paramJson,"fromKeyType");

			String fromKeyTypeIsTotal = getString(paramJson,"fromKeyTypeIsTotal");//否是统计现金流
			String userInfoIsDetail = getString(paramJson,"userInfoIsDetail");//是否查询用户的详细信息

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

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info("调用现金流日志接口result:"+result.toString());
		return JSON.toJSONString(result, SerializerFeature.BrowserCompatible);
	}
	public String moneyLogByDate(String paramjsonStr) {
		logger.info("====moneyLogByDate======paramjsonStr:"+paramjsonStr);
		com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
		try {
			com.alibaba.fastjson.JSONObject paramJson = com.alibaba.fastjson.JSONObject.parseObject(paramjsonStr);
			String beginTime = paramJson.getString("beginTime");
			String endTime = paramJson.getString("endTime");
//			String key = result.getString("key");
			String siteId = paramJson.getString("siteId");
			String fromKeyType = paramJson.getString("fromKeyType");

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

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info("moneyLogByDate======result:"+result.toString());
		return JSON.toJSONString(result, SerializerFeature.BrowserCompatible);
	}
	public String checkTransMoney(String paramjsonStr) {
		logger.info("====调用查询转账接口======paramjsonStr:"+paramjsonStr);
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			JSONObject paramJson = JSONObject.fromObject(paramjsonStr);
			String username = getString(paramJson,"username");
			String remitno = getString(paramJson,"remitno");//流水号
			String fromKey = getString(paramJson,"fromKey");
			String key = getString(paramJson,"key");
			String siteId = getString(paramJson,"siteId");
			String hashCode = getString(paramJson,"hashCode");
			String ip = getTCPClientIp();
			if(StringUtils.isBlank(username)){
				result.put("message", "username is null");
				result.put("code", 110009);
					return result.toString();
			}
			if(StringUtils.isBlank(remitno)){
				result.put("message", "remitno is null");
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

			//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
			String md5key = MoneyConfigUtils.getKeyMd5(key);
			String toMd5 = EncryptUtils.toMD5(fromKey+username+remitno);
			if(!md5key.equals(toMd5)){
				result.put("message", "authentication fail");
				result.put("code", 110009);
					return result.toString();
			}
			Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
			DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
			if(moneyConfig == null){
				result.put("message", "authentication is not allowed");
				result.put("code", 110014);
					return result.toString();
			}
			Set<String> ipSet = moneyCenterService.getIpSet();
			if(!ipSet.contains(ip)){
				result.put("message", "ip is not allowed");
				logger.info(ip+"not allowed");
				result.put("code", 110014);
				return result.toString();
			}
			data.put("username", username);
			moneyService.checktransMoney(username,Integer.valueOf(siteId),remitno,result,data,fromKey,hashCode);
		}catch(JSONException e){
			result.put("message", e.getMessage());
			result.put("code", 110009);
		}catch (Exception e) {

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info(">>>>>>>>>>>>查询转账结果:"+result.toString());
		return result.toString();
	}
	public String setMemberData(String paramjsonStr){
		logger.info("===============调用setMemberData接口===="+paramjsonStr);
		JSONObject result = new JSONObject();
		try {
			com.alibaba.fastjson.JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(paramjsonStr);
			String username = obj.getString("username");
			String siteId = obj.getString("siteId");
			String agents = obj.getString("agents");
			String world = obj.getString("world");
			String corprator = obj.getString("corprator");
			String superior = obj.getString("superior");
			String company = obj.getString("company");
			String ip = getTCPClientIp();
			logger.info("请求者IP......"+ip);
			if(StringUtils.isBlank(username)){
				result.put("message", "username param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(siteId) || !DataUtils.checkNum(siteId)){
				result.put("message", "siteId param is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(agents)){
				result.put("message", "agents param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(world)){
				result.put("message", "world param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(corprator)){
				result.put("message", "corprator param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(superior)){
				result.put("message", "superior param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(company)){
				result.put("message", "company param  cant not null");
				result.put("code", 110009);
				return result.toString();
			}

			Set<String> ipSet = moneyCenterService.getIpSet();
			if(!ipSet.contains(ip)){
				result.put("message", "ip is not allowed");
				logger.info(ip+"not allowed");
				result.put("code", 110014);
				return result.toString();
			}

			moneyService.setMemberData(result,username,siteId,agents,world,corprator,superior,company);
			result.put("message", "ok");
			result.put("code", 100000);
			logger.info("username："+username+",siteId:"+siteId+",>>>>>>>>>result:"+result);
		} catch (Exception e) {

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		return result.toString();
	}
//	public String updateUserState(String paramjsonStr){
//		logger.info("===============调用updateUserState接口===="+paramjsonStr);
//		JSONObject result = new JSONObject();
//		try {
//			com.alibaba.fastjson.JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(paramjsonStr);
//			String username = obj.getString("username");
//			String siteId = obj.getString("siteId");
//			String agentLevel = obj.getString("agentLevel");
//			String agentName = obj.getString("agentName");
//			String state = obj.getString("state");
//			String ip = getTCPClientIp();
//			logger.info("请求者IP......"+ip);
//			if(StringUtils.isBlank(agentLevel) && StringUtils.isBlank(username)){
//				result.put("message", "username param  cant not null");
//				result.put("code", 110009);
//				return result.toString();
//			}
//			if(StringUtils.isBlank(siteId) || !DataUtils.checkNum(siteId)){
//				result.put("message", "siteId param is wrong");
//				result.put("code", 110009);
//				return result.toString();
//			}
//			if(StringUtils.isBlank(state) || !Arrays.asList(new Object[]{"-50","0","50"}).contains(state)){
//				result.put("message", "state param  is wrong");
//				result.put("code", 110009);
//				return result.toString();
//			}
//			if(StringUtils.isNotBlank(agentLevel) && !Arrays.asList(new Object[]{"agents","world","corprator","superior","company"}).contains(agentLevel)){
//				result.put("message", "agentLevel param  is wrong,must be agents world corprator superior company");
//				result.put("code", 110009);
//				return result.toString();
//			}
//			if(StringUtils.isNotBlank(agentLevel) && StringUtils.isBlank(agentName)){
//				result.put("message", "if agentLevel is not null, agentName can not null");
//				result.put("code", 110009);
//				return result.toString();
//			}
//
//			Set<String> ipSet = moneyCenterService.getIpSet();
//			if(!ipSet.contains(ip)){
//				result.put("message", "ip is not allowed");
//				logger.info(ip+"not allowed");
//				result.put("code", 110014);
//				return result.toString();
//			}
//			moneyService.updateUserState(result,obj);
//
//		} catch (Exception e) {
//
//			logger.error(e.getMessage());
//			result.put("message", "system error");
//			result.put("code", 110001);
//		}
//		logger.info("===============调用updateUserState接口====,>>>>>>>>>result:"+result);
//		return result.toString();
//	}
	public String getTotalBalance(String paramjsonStr){
		logger.info("===============调用getTotalBalance接口===="+paramjsonStr);
		JSONObject result = new JSONObject();
		try {
			com.alibaba.fastjson.JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(paramjsonStr);
			String siteId = obj.getString("siteId");
			if(StringUtils.isBlank(siteId) || !DataUtils.checkNum(siteId)){
				result.put("message", "siteId param is wrong");
				result.put("code", 110009);
				return result.toString();
			}
			moneyService.getTotalBalance(result,obj);
		} catch (Exception e) {

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		logger.info("===============调用getTotalBalance接口====,>>>>>>>>>result:"+result);
		return result.toString();
	}

	public String changeAgentMoney(String paramjsonStr){
		logger.info("===============调用changeAgentMoney接口======================="+paramjsonStr);
		long start =System.currentTimeMillis();
		String ip = getTCPClientIp();
		net.sf.json.JSONObject result = new net.sf.json.JSONObject();
		try {
			com.alibaba.fastjson.JSONObject param = com.alibaba.fastjson.JSONObject.parseObject(paramjsonStr);
			String siteId = param.getString("siteId");
			String username = param.getString("username");
			String agentLevel = param.getString("agentLevel");
			String agentName = param.getString("agentName");
			String remitno = param.getString("remitno");//转账唯一标识
			String remit = param.getString("remit");
			String userLevel = param.getString("userLevel");//低级别
			String agentTransType = param.getString("agentTransType");//in 转入代理，out代理转出
			String userType = param.getString("userType");//用户类型2：信用用户 1：现金用户
			/**
			 * by guangguang
			 *
			 */
			String fromKeyTypeIn = param.getString("fromKeyTypeIn");
			String fromKeyTypeOut = param.getString("fromKeyTypeOut");
			String fromKeyTypeError = param.getString("fromKeyTypeError");
			/*****************************/
			if(StringUtils.isBlank(fromKeyTypeIn) || !DataUtils.checkNum(fromKeyTypeIn)){
				result.put("message", "fromKeyTypeIn param is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(fromKeyTypeOut) || !DataUtils.checkNum(fromKeyTypeOut)){
				result.put("message", "fromKeyTypeOut param is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(fromKeyTypeError) || !DataUtils.checkNum(fromKeyTypeError)){
				result.put("message", "fromKeyTypeOut param is null");
				result.put("code", 110009);
				return result.toString();
			}
			if(StringUtils.isBlank(userType) || (!userType.equals("1") && !userType.equals("2"))){
				result.put("message", "userType param  must be 1 or 2");
				result.put("code", 110009);
				return result.toString();
			}
			if(userType.equals("1") && agentTransType.equals("in")){
				result.put("message", "userType is 1,agentuser can not transout");
				result.put("code", 110009);
				return result.toString();
			}
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
					return result.toString();
				}
			agentTransType = agentTransType.toLowerCase();
			remit = DataUtils.getStringNumber(remit);
			if(StringUtils.isBlank(remit)
					||!DataUtils.checkNum(remit)
					||Double.valueOf(remit)<0){ //去掉等于 0 的条件
				result.put("message", "remit must be >= 0");
				result.put("code", 110009);
				return result.toString();
			}
			Set<String> ipSet = moneyCenterService.getIpSet();
			if(!ipSet.contains(ip)){
				result.put("message", "ip is not allowed");
				logger.info(ip+"not allowed");
				result.put("code", 110014);
				return result.toString();
			}

			moneyService.changeAgentMoney(result,param,ip);

		} catch (Exception e) {

			logger.error(e.getMessage());
			result.put("message", "system error");
			result.put("code", 110001);
		}
		long end =System.currentTimeMillis();
		logger.info("===============调用changeAgentMoney接口====,>>>>>>>>time:"+(end-start)+">result:"+result);
		return result.toString();
	}



	private String getString(JSONObject objson,String str){
		if(objson.has(str)){
			return objson.getString(str);
		}
		return null;
	}

	private String getTCPClientIp(){
		TcpContext tcpContext = HproseTcpServer.getCurrentContext();
		if(tcpContext == null){
			return null;
		}
		Socket socket = tcpContext.getSocket();
		if(socket == null){
			return null;
		}
		InetAddress addr = socket.getInetAddress();
		logger.info("Connection from==>" + addr.getHostName()
			      + "/t" + addr.getHostAddress());
		return addr.getHostAddress();
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
