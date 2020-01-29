package com.ds.money.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ds.money.dao.mapper.DsMoneyDao;
import com.ds.money.untils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.onetwo.common.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;

import com.ds.money.entity.CheckEntity;
import com.ds.money.entity.DsMemberMoney;
import com.ds.money.entity.DsMemberMoneyLog;
import com.ds.money.entity.MemberEntity;
import com.ds.money.untils.DataUtils;
import com.ds.money.untils.MoneyConfigUtils;
import com.ds.money.vo.MoneyLogByDateParam;
import com.ds.money.vo.MoneyLogByDateVo;
import com.ds.money.vo.MoneyLogParam;

import javax.annotation.Resource;

@Service
public class MemberMoneyServiceImp{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String userpassword = DataUtils.get()+"";
	//@Resource(name="jmsTemplate")
    //private JmsTemplate jmsTemplate;
//	@Resource(name = "moneyQueueDestination")
//	private Destination moneyQueueDestination;
//	@Autowired
//	private DsMemberMoneyLogMapper moneyLogMapper;
//	@Autowired
//	protected RedisTemplate redisTemplate;
	@Resource
	private DsMoneyDao moneyDao;
//	@Autowired
//	private MemberEntityMapper memberMapper;
//	@Autowired
//	private CheckEntityMapper checkMapper;
//	@Autowired
//	private DsMemberMoneyLogMapper moneyLogMapper;
	/**
	 * 获取用户金额 （先从缓存中获取数据，如果没有数据再从mysql数据库中获取数据）
	 * @param username
	 * @param result
	 * @param data
	 * @throws Exception
	 *
	 */
	public void getMoney(String username,Integer siteId, Map<String, Object> result, Map<String, Object> data,String hashcode)throws Exception {
		//String money = (String) redisTemplate.opsForValue().get(siteId+"_"+username);
		String money =null;
		//缓存中有数据
		if(!StringUtils.isBlank(money)){
		//	logger.info("从缓存中获取数据");
			result.put("message", "Nomal");
			result.put("code", 100000);
			data.put("money", money);
			if(StringUtils.isNotBlank(hashcode)){
				data.put("hashCode", hashcode);
			}else{
				data.put("siteId", siteId);
			}
			result.put("data", data);
			return;
		}
		//缓存中没有数据，从数据库读取

		//获取用户所在的表名
		String memberMoneyTable = MoneyConfigUtils.getTableName(username);

		DsMemberMoney memberMoney = getMemberMoney(username,siteId,memberMoneyTable);
		//用户存在
		if(memberMoney == null){
			//用户不存在
			memberMoney = new DsMemberMoney();
			memberMoney.setUsername(username);
			memberMoney.setState(50);
			memberMoney.setSiteId(siteId);
			memberMoney.setMoney(new BigDecimal(0.00));
			memberMoney.setPassword(MD5Utils.Md5String(username + userpassword + memberMoney.getMoney().setScale(5)));
			memberMoney.setVersion(1);
			//	moneyMapper.insert(memberMoney);
			/////////////////
			moneyDao.insert(memberMoney,memberMoneyTable);
		}
		result.put("message", "Nomal");
		result.put("code", 100000);
		data.put("money", memberMoney.getMoney().setScale(5, BigDecimal.ROUND_HALF_UP));
		if(StringUtils.isNotBlank(hashcode)){
			data.put("hashCode", hashcode);
		}else{
			data.put("siteId", siteId);
		}
		result.put("data", data);
		addRedis(memberMoney);
		return;

	}
	private DsMemberMoney getMemberMoney(String username, Integer siteId,String tableName){
//		DsMemberMoneyExample e = new DsMemberMoneyExample();
//		e.createCriteria().andUsernameEqualTo(username).andSiteIdEqualTo(siteId);
//		List<DsMemberMoney> list = moneyMapper.selectByExample(e);

		List<DsMemberMoney> list = moneyDao.selectUserMoney(username,siteId,tableName);

		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	private void addRedis(DsMemberMoney memberMoney){
		//redisTemplate.opsForValue().set(memberMoney.getSiteId()+"_"+memberMoney.getUsername(), memberMoney.getMoney().toString());
	}


	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
	public void transMoney(String username, Integer siteId, String hashcode,String remitno, String transType,
			String remit, String wagerCancel, Map<String, Object> result, Map<String, Object> data,String fromKey,String ip, String fromKeyType, String memo,String transNum) throws Exception {
		/****************************/
		result.put("transId", remitno);
		//添加check验证
		String checkTable = MoneyConfigUtils.getCheckTableName(username,transNum);
		try{
			CheckEntity check = new CheckEntity();
			check.setTransId(fromKey+"_"+siteId+"_"+username+"_"+remitno);//添加 时间戳，以防重复
			check.setStatus("true");
			check.setId(UUID.randomUUID().toString());
		//	checkMapper.insert(check);//transId 有重复，会报错
			moneyDao.insertCheck(siteId,check,checkTable);

		}catch(Exception e){
		//
			logger.info(e.getCause().toString());
			String cause = e.getCause().toString();
			if(cause.contains("Duplicate entry")){
				//判断成功还是失败
				CheckEntity check = getTransId(siteId,fromKey+"_"+siteId+"_"+username+"_"+remitno,checkTable);
				if(check.getStatus().equals("true")){
					result.put("message", "transMoney repeat check submit");//transMoney false
					data.put("money", getMoney(username,siteId));
					result.put("data", data);
					result.put("code", 110015);//重复请求
					return;
				}

			}
			throw new Exception("db cause exception");
		}
		/****************************/

		//获取用户所在的表名
		String memberMoneyTable = MoneyConfigUtils.getTableName(username);
		//缓存没有数据，说明没有重复提交（认为没有重复提交）
		DsMemberMoney memberMoney = getMemberMoney(username,siteId,memberMoneyTable);
		//用户存在
		if(memberMoney == null){
			memberMoney = new DsMemberMoney();
			memberMoney.setUsername(username);
			memberMoney.setState(50);
			memberMoney.setSiteId(siteId);
			memberMoney.setMoney(new BigDecimal(0.00));
			memberMoney.setPassword(MD5Utils.Md5String(username+userpassword+memberMoney.getMoney().setScale(5)));
			memberMoney.setVersion(1);
		//	moneyMapper.insert(memberMoney);
			moneyDao.insert(memberMoney, memberMoneyTable);


		}
		/***********************/
		//添加验证用户state验证
		Integer state = memberMoney.getState();
		if(state != 50){
			result.put("message", "user is not start ,this account is not use");
			result.put("code", 110006);
			throw new Exception("user is not start ,this account is not use");
		//	return;
		}

		/***********************/
		//判断用户合法性，数据是否被串改
		String password = MD5Utils.Md5String(username+userpassword+memberMoney.getMoney().setScale(5));
		if(!password.equals(memberMoney.getPassword())){
			result.put("message", "this account is unusual");
			result.put("code", 110002);
			throw new Exception("this account is unusual");
		//	return;
		}
		/********************************************/
		logger.info("ok.....");
		/********************************************/
		BigDecimal beforeMoney = memberMoney.getMoney();

		//转账操作    转入操作
		BigDecimal remitMoney = new BigDecimal(remit);
		if(transType.equals("in")||transType.equals("IN")){
			BigDecimal oldMoney = memberMoney.getMoney();
			BigDecimal newMoney = oldMoney.add(remitMoney).setScale(5, BigDecimal.ROUND_HALF_UP);
			memberMoney.setMoney(newMoney);
			memberMoney.setPassword(MD5Utils.Md5String(username+userpassword+memberMoney.getMoney().setScale(5)));
			/***添加版本控制 version*********************/
			//moneyMapper.updateByPrimaryKey(memberMoney);
			Integer version = memberMoney.getVersion();
			memberMoney.setVersion(version+1);
			int update = updateMoneyByVersion(memberMoney,version,memberMoneyTable);
			if(update == 0){
				result.put("message", "this account request is too many");
				result.put("code", 111111);
				throw new Exception("this account request is too many");
			//	return;
			}

		}else{
			//转出操作
			BigDecimal oldMoney = memberMoney.getMoney();
			//判断是不是注单取消，注单取消可以是负值
			if(StringUtils.isNotBlank(wagerCancel)
					&&wagerCancel.equals("1")){

			}else{
				//不是注单取消情况，则必须判断金额是否
				if(oldMoney.compareTo(remitMoney)<0){
					result.put("message", "money is not enough");
					result.put("code", 110012);
					throw new Exception("money is not enough");
				//	return;
				}

			}
			BigDecimal newMoney = oldMoney.subtract(remitMoney);
			memberMoney.setMoney(newMoney);
			memberMoney.setPassword(MD5Utils.Md5String(username+userpassword+memberMoney.getMoney().setScale(5)));

			Integer version = memberMoney.getVersion();
			memberMoney.setVersion(version+1);
			int update = updateMoneyByVersion(memberMoney,version,memberMoneyTable);
			if(update == 0){
				result.put("message", "this account request is too many");
				result.put("code", 111111);
				throw new Exception("this account request is too many");
			//	return;
			}
		}

		addRedis(memberMoney);

		//写日志(一些字段待补充)
		DsMemberMoneyLog moneyLog = new DsMemberMoneyLog();
		moneyLog.setCreateTime(new Date());
		moneyLog.setBeforeMoney(beforeMoney);
		moneyLog.setAfterMoney(memberMoney.getMoney());
		moneyLog.setRemit(remitMoney);//交易金额
		moneyLog.setTransId(remitno);
		moneyLog.setTransType(transType);
		moneyLog.setUsername(username);
		moneyLog.setFromKey(fromKey);
		moneyLog.setSiteId(siteId);
		moneyLog.setHashcode(hashcode);
		moneyLog.setRequestIp(ip);
		moneyLog.setFromKeyType(Integer.valueOf(fromKeyType));
		moneyLog.setMemo(memo);
		try {
			addQueue(moneyLog);
		} catch (Exception e) {
			//添加到MQ失败时候，redis需要改变成以前的金额
			memberMoney.setMoney(beforeMoney);
			addRedis(memberMoney);
			throw new Exception("添加到MQ异常");
		}
	//	moneyLogMapper.insert(moneyLog);
		result.put("message", "Nomal");
		result.put("code", 100000);
		data.put("money", memberMoney.getMoney().setScale(5));

		result.put("data", data);
		if(StringUtils.isNotBlank(hashcode)){
			data.put("hashCode", hashcode);
		}else{
			data.put("siteId", siteId);
		}

	}
	 private CheckEntity getTransId(Integer siteId,String transId,String tableName){
		 List<CheckEntity> list = moneyDao.findCheckByTransId(siteId,transId,tableName);
		  if(list!=null && list.size()>0){
		   return list.get(0);
		  }
		  return null;
	 }


	private String getMoney(String username,Integer siteId){
		String money = null;
		//money = (String) redisTemplate.opsForValue().get(siteId+"_"+username);
		//缓存中有数据
		if(!StringUtils.isBlank(money)){
			logger.info("从缓存中获取数据");
			return money;
		}
		//缓存中没有数据，从数据库读取
		//获取用户所在的表名
		String memberMoneyTable = MoneyConfigUtils.getTableName(username);

		DsMemberMoney memberMoney = getMemberMoney(username,siteId,memberMoneyTable);
		//用户存在
		if(memberMoney!=null){

		}else{
		//用户不存在
			memberMoney = new DsMemberMoney();
			memberMoney.setUsername(username);
			memberMoney.setState(50);
			memberMoney.setSiteId(siteId);
			memberMoney.setMoney(new BigDecimal(0.00));
			memberMoney.setPassword(MD5Utils.Md5String(username+userpassword+memberMoney.getMoney().setScale(5)));
			memberMoney.setVersion(1);
		//	moneyMapper.insert(memberMoney);
			moneyDao.insert(memberMoney, memberMoneyTable);
		}
		money = memberMoney.getMoney().setScale(5, BigDecimal.ROUND_HALF_UP).toString();
		addRedis(memberMoney);
		return money;
	}
	/**
	 * 加入消息队列
	 * @param moneyLog
	 * 以json字符串形式保存在MQ中
	 */
	private void addQueue(DsMemberMoneyLog moneyLog){


//		String objectJson = JSON.toJSONString(moneyLog);
//		redisTemplate.opsForList().leftPush(logListStr, objectJson);
		String json = JSONObject.toJSONString(moneyLog);
		long start = System.currentTimeMillis();
		//jmsTemplate.convertAndSend(moneyQueueDestination, json);
		long end = System.currentTimeMillis();
		logger.info(">>>>>>time:"+(end -start));
	}
	public int updateMoneyByVersion(DsMemberMoney money,Integer version,String tableName){
//		DsMemberMoneyExample e = new DsMemberMoneyExample();
//		e.createCriteria().andIdEqualTo(money.getId()).andVersionEqualTo(version);
//		return moneyMapper.updateByExample(money, e);
		return moneyDao.updateMoneyByVersion(money,version,tableName);


	}
	public void checktransMoney(String username, Integer siteId,
			String remitno, Map<String, Object> result,
			Map<String, Object> data, String fromKey, String hashCode) {

		/****************************/
		String checkTable = MoneyConfigUtils.getCheckTableName(username,MoneyConfigUtils.SINGLE);
//		String checkTable2 = MoneyConfigUtils.getCheckTableName(username,MoneyConfigUtils.NOT_SINGLE);
		//添加check验证
		//flag = 插入数据库 如果插入single 数据库出错，则flag=1，否则flag=2
		//flag作为一个判断标识
//		int errorFlag = 1;
		try{
			CheckEntity check = new CheckEntity();
			check.setTransId(fromKey+"_"+siteId+"_"+username+"_"+remitno);
			check.setStatus("false");
			check.setId(UUID.randomUUID().toString());
		//	checkMapper.insert(check);
			moneyDao.insertCheck(siteId,check, checkTable);//single
		//	errorFlag = 2;
		//	moneyDao.insertCheck(check, checkTable2);//not_single

			//能够正常插入，证明就是没有成功转账
			result.put("message", "Normal");
			result.put("code", 100000);
			data.put("remitno", remitno);
			data.put("username", username);
			data.put("status", "false");//转账失败
			result.put("data", data);
			if(StringUtils.isNotBlank(hashCode)){
				data.put("hashCode", hashCode);
			}else{
				data.put("siteId", siteId);
			}
			return;

		}catch(Exception e){
			//判断是哪个表插入失败

			//不能成功插入，则先看下数据库状态
//			CheckEntityExample ex = new CheckEntityExample();
//			ex.createCriteria().andTransIdEqualTo(fromKey+"_"+siteId+"_"+username+"_"+remitno);
//			List<CheckEntity> checkList = checkMapper.selectByExample(ex);
			List<CheckEntity> checkList = moneyDao.findCheckByTransId(siteId,fromKey+"_"+siteId+"_"+username+"_"+remitno, checkTable);;
//			if(errorFlag == 1){
//				checkList = moneyDao.findCheckByTransId(fromKey+"_"+siteId+"_"+username+"_"+remitno, checkTable);//single
//			}else{
//				checkList = moneyDao.findCheckByTransId(fromKey+"_"+siteId+"_"+username+"_"+remitno, checkTable2);//not_single
//
//			}

			String status = checkList.get(0).getStatus();
			if(status.equals("true")){//转账成功
				result.put("message", "Normal");
				result.put("code", 100000);
				data.put("remitno", remitno);
				data.put("username", username);
				data.put("status", "true");//转账成功
				result.put("data", data);
				if(StringUtils.isNotBlank(hashCode)){
					data.put("hashCode", hashCode);
				}else{
					data.put("siteId", siteId);
				}
				return;
			}else{
				result.put("message", "Normal");
				result.put("code", 100000);
				data.put("remitno", remitno);
				data.put("username", username);
				data.put("status", "false");//转账失败
				result.put("data", data);
				if(StringUtils.isNotBlank(hashCode)){
					data.put("hashCode", hashCode);
				}else{
					data.put("siteId", siteId);
				}
				return;
			}
		}

	}
	public void memberMoneyLog(JSONObject result,JSONObject data,MoneyLogParam queryParam, Page<DsMemberMoneyLog> pagation) {

		String agentLevel = queryParam.getAgentLevel();
		if(StringUtils.isNotBlank(agentLevel)){
			List<DsMemberMoneyLog> list=moneyDao.findMoneyLogAgentLevelByPage(new RowBounds(pagation.getFirst()-1, pagation.getPageSize()), queryParam);
			result.put("data", list);
			Long totalCount= moneyDao.countMoneyLogAgentLevelByPage(queryParam);
			Map<String, Object> pagationMap = new HashMap<String, Object>();
			result.put("pagation", pagationMap);
			pagationMap.put("page", pagation.getPageNo());
			pagationMap.put("pageSize", pagation.getPageSize());
			pagationMap.put("totalNumber", totalCount);
			return;
		}

		String userInfoIsDetail = queryParam.getUserInfoIsDetail();
		//查用户明细
		if(StringUtils.isNotBlank(userInfoIsDetail) && userInfoIsDetail.equals("1")){
		//	List<DsMemberMoneyLog> list=moneyDao.findMoneyLogByPage(pagation.getFirst()-1,pagation.getPageSize() , queryParam);
			List<DsMemberMoneyLog> list=moneyDao.findMoneyLogByPage(new RowBounds(pagation.getFirst()-1, pagation.getPageSize()), queryParam);
			result.put("data", list);
			Long totalCount= moneyDao.countMoneyLogByPage(queryParam);
			Map<String, Object> pagationMap = new HashMap<String, Object>();
			result.put("pagation", pagationMap);
			pagationMap.put("page", pagation.getPageNo());
			pagationMap.put("pageSize", pagation.getPageSize());
			pagationMap.put("totalNumber", totalCount);
		}
		//查统计
		String  fromKeyTypeIsTotal = queryParam.getFromKeyTypeIsTotal();
		if(StringUtils.isNotBlank(fromKeyTypeIsTotal) && fromKeyTypeIsTotal.equals("1")){
			Map<String,BigDecimal> typeMap = new HashMap<String,BigDecimal>();
			List<Integer> fromKeyTypeList = queryParam.getFromKeyTypeList();
			for (Integer type : fromKeyTypeList) {
				Double typeMoneyTotal = moneyDao.fromKeyTypeSum(type,queryParam);
				typeMap.put(type.toString(), new BigDecimal(typeMoneyTotal==null?0:typeMoneyTotal).setScale(5, BigDecimal.ROUND_HALF_UP));
			}
			result.put("fromKeyTotal", typeMap);
		}


	}
	public void moneyLogByDate(com.alibaba.fastjson.JSONObject result,
			MoneyLogByDateParam queryParam) {
		List<MoneyLogByDateVo> moneyLogList  = moneyDao.moneyLogByDate(queryParam);
		result.put("data", moneyLogList);
		BigDecimal totalMoney = new BigDecimal(0);
		if(moneyLogList!=null && moneyLogList.size()>0){
			for (MoneyLogByDateVo moneyLogByDateVo : moneyLogList) {
				totalMoney = totalMoney.add(moneyLogByDateVo.getTransMoney());
			}
		}
		result.put("totalMoney", totalMoney);
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
	public void setMemberData(net.sf.json.JSONObject result, String username,
			String siteId, String agents, String world, String corprator,
			String superior, String company) {
		MemberEntity member = getMemberByUsername(Integer.valueOf(siteId),username);
		if(member == null){
			member = new MemberEntity();
			member.setCreateTime(new Date());
			member.setAgents(agents);
			member.setCompany(company);
			member.setCorprator(corprator);
			member.setSiteId(Integer.valueOf(siteId));
			member.setSuperior(superior);
			member.setUsername(username);
			member.setWorld(world);
		//	memberMapper.insert(member);
			moneyDao.insertMember(member);
			logger.info("插入username："+username+",siteId:"+siteId);
		}else{
			member.setUpdateTime(new Date());
			member.setAgents(agents);
			member.setCompany(company);
			member.setCorprator(corprator);
			member.setSiteId(Integer.valueOf(siteId));
			member.setSuperior(superior);
			member.setUsername(username);
			member.setWorld(world);
	//		memberMapper.updateByPrimaryKey(member);
			moneyDao.updateMember(member);
			logger.info("更新username："+username+",siteId:"+siteId);
		}

	}
	private MemberEntity getMemberByUsername(Integer siteId, String username) {
//		MemberEntityExample e = new MemberEntityExample();
//		e.createCriteria().andSiteIdEqualTo(siteId).andUsernameEqualTo(username);
//		List<MemberEntity> list = memberMapper.selectByExample(e);
		List<MemberEntity> list = moneyDao.getUserByName(siteId,username);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
//	public void updateUserState(net.sf.json.JSONObject result, com.alibaba.fastjson.JSONObject obj) {
//		String agentLevel = obj.getString("agentLevel");
//		if(StringUtils.isNotBlank(agentLevel)){
//			/*************/
//			MemberEntityExample e = new MemberEntityExample();
//			Criteria c  = e.createCriteria().andSiteIdEqualTo(obj.getInteger("siteId"));
//			if(agentLevel.equals("agents")){
//				c.andAgentsEqualTo(obj.getString("agentName"));
//			}else if(agentLevel.equals("world")){
//				c.andWorldEqualTo(obj.getString("agentName"));
//			}else if(agentLevel.equals("corprator")){
//				c.andCorpratorEqualTo(obj.getString("agentName"));
//			}else if(agentLevel.equals("superior")){
//				c.andSuperiorEqualTo(obj.getString("agentName"));
//			}else if(agentLevel.equals("company")){
//				c.andCompanyEqualTo(obj.getString("agentName"));
//			}
//			List<MemberEntity> memberList = memberMapper.selectByExample(e);
//			for (MemberEntity memberEntity : memberList) {
//				getMoney(memberEntity.getUsername(), obj.getInteger("siteId"));
//			}
//			/*************/
//			//批量处理
//			moneyDao.updateUserState(agentLevel,obj.getString("agentName"),obj.getInteger("state"),obj.getInteger("siteId"));
//			result.put("message", "ok");
//			result.put("code", 100000);
//		}else{
//			/*************/
//			//先给用户注册钱包，然后再查询
//			getMoney(obj.getString("username"), obj.getInteger("siteId"));
//			/*************/
//			//获取用户所在的表名
//			String memberMoneyTable = MoneyConfigUtils.getTableName(obj.getString("username"));
//			DsMemberMoney memberMoney = getMemberMoney(obj.getString("username"), obj.getInteger("siteId"), memberMoneyTable);
////			DsMemberMoneyExample e = new DsMemberMoneyExample();
////			e.createCriteria().andUsernameEqualTo(obj.getString("username")).andSiteIdEqualTo(obj.getInteger("siteId"));
////			List<DsMemberMoney> list = moneyMapper.selectByExample(e);
////			if(list==null || list.size() == 0){
////				result.put("message", "user :"+obj.getString("username")+" not fount");
////				result.put("code", 110009);
////				return;
////			}
////			DsMemberMoney memberMoney = list.get(0);
////			if(memberMoney == null){
////				result.put("message", "user :"+obj.getString("username")+" not fount");
////				result.put("code", 110009);
////				return;
////			}
//			memberMoney.setState(obj.getInteger("state"));
////			moneyMapper.updateByPrimaryKey(memberMoney);
//			result.put("message", "ok");
//			result.put("code", 100000);
//		}
//	}
	public void getTotalBalance(net.sf.json.JSONObject result,
			com.alibaba.fastjson.JSONObject obj) {
		BigDecimal totalstate0Money = moneyDao.getTotalBalanceByState(obj,0);
		BigDecimal totalstate50Money = moneyDao.getTotalBalanceByState(obj,50);
		JSONObject data = new JSONObject();
		data.put("invalidateMoney", totalstate0Money);
		data.put("validateMoney", totalstate50Money);
		result.put("data", data);
		result.put("message", "ok");
		result.put("code", 100000);
	}
	public void getTransMoneyInfo(Map<String, Object> result,
			Map<String, Object> data, String username, Integer siteId,
			String remitno) {
		DsMemberMoneyLog moneyLog = getMoneyLogByNo(username, siteId, remitno);
		if(moneyLog != null){
			data.put("remit", moneyLog.getRemit());
			data.put("remitno", remitno);
			data.put("transType", moneyLog.getTransType());
			data.put("username", username);
			data.put("siteId", siteId);
		}
		result.put("data", data);
		result.put("message", "ok");
		result.put("code", 100000);

	}
	private DsMemberMoneyLog getMoneyLogByNo(String username,Integer siteId,String remitno){
//		DsMemberMoneyLogExample e = new DsMemberMoneyLogExample();
//		e.createCriteria().andSiteIdEqualTo(siteId).andUsernameEqualTo(username).andTransIdEqualTo(remitno);
//		List<DsMemberMoneyLog> list = moneyLogMapper.selectByExample(e);
		List<DsMemberMoneyLog> list = moneyDao.getMoneyLogByNo(username,siteId,remitno);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;

	}
	public void updateMoneyLog(Map<String, Object> result,
			Map<String, Object> data, String username, Integer siteId,
			String remitno,String memo) {
		DsMemberMoneyLog log = getMoneyLogByNo(username, siteId, remitno);
		if(log == null){
			result.put("message", "remitno:"+remitno+" not exist");
			result.put("code", 100010);
			return;
		}
		log.setMemo(log.getMemo()+","+memo);
	//	moneyLogMapper.updateByPrimaryKey(log);
		moneyDao.updateMoneyLogMemo(log.getSiteId(),log.getId(),log.getMemo());

		result.put("message", "ok");
		result.put("code", 100000);
	}
	private MemberEntity getMemberInfo(Integer siteId,String userLevel,String username,String agentLevel,String agentName){
//		MemberEntityExample e = new MemberEntityExample();
//		Criteria c = e.createCriteria();
//		c.andSiteIdEqualTo(siteId);
//		if(userLevel.equals("agents")){
//			c.andAgentsEqualTo(username);
//		}
//		if(userLevel.equals("world")){
//			c.andWorldEqualTo(username);
//		}
//		if(userLevel.equals("corprator")){
//			c.andCorpratorEqualTo(username);
//		}
//		if(userLevel.equals("superior")){
//			c.andSuperiorEqualTo(username);
//		}
//		if(userLevel.equals("username")){
//			c.andUsernameEqualTo(username);
//		}
//
//		if(agentLevel.equals("agents")){
//			c.andAgentsEqualTo(agentName);
//		}
//		if(agentLevel.equals("world")){
//			c.andWorldEqualTo(agentName);
//		}
//		if(agentLevel.equals("corprator")){
//			c.andCorpratorEqualTo(agentName);
//		}
//		if(agentLevel.equals("superior")){
//			c.andSuperiorEqualTo(agentName);
//		}
//		if(agentLevel.equals("company")){
//			c.andCompanyEqualTo(agentName);
//		}
//		List<MemberEntity> list = memberMapper.selectByExample(e);
		List<MemberEntity> list = moneyDao.getMemberInfo(siteId,userLevel,username,agentLevel,agentName);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;

	}
	public void changeAgentMoney(net.sf.json.JSONObject result, JSONObject param,String ip){
		Integer siteId = param.getInteger("siteId");
		String username = param.getString("username");
		String agentLevel = param.getString("agentLevel");
		String userLevel = param.getString("userLevel");
		String agentName = param.getString("agentName");
		String remitno = param.getString("remitno");//转账唯一标识
		String remit = param.getString("remit");
		String memo = param.getString("memo");
		String agentMemo = param.getString("agentMemo");
		String agentTransType = param.getString("agentTransType");//in 转入代理，out代理转出

		//验证上下级关系

		MemberEntity member = getMemberInfo(siteId, userLevel, username, agentLevel, agentName);
		if(member == null){
			result.put("code", 110020);
			result.put("message", "user and agent not have level");
			return;
		}
//		String userType = param.getString("userType");
		String fromKeyTypeIn = param.getString("fromKeyTypeIn");
		String fromKeyTypeOut = param.getString("fromKeyTypeOut");
		String fromKeyTypeError = param.getString("fromKeyTypeError");
//		if(userType.equals("2")){
//			fromKeyTypeIn = "10050";
//			fromKeyTypeOut = "10051";
//			fromKeyTypeError = "10055";
//		}else{
//			fromKeyTypeIn = "10053";
//			fromKeyTypeOut = "10054";
//			fromKeyTypeError = "10056";
//		}
		Map<String, Object> transUserResult = new HashMap<String, Object>();
		Map<String, Object> transUserData = new HashMap<String, Object>();
		if(agentTransType.equals("in")){//代理转入
			//会员先转出
			while(true){
				try {
					this.transMoney(username, siteId, "", remitno+"_out", "out", remit, "0", transUserResult, transUserData, "ds_money_key", ip, fromKeyTypeOut, memo,MoneyConfigUtils.SINGLE);
				} catch (Exception e) {
					logger.error("会员转出异常",e);
				}
				if(transUserResult.get("code").equals(111111)){
					continue;
				}else{
					break;
				}
			}//end whlie
			if(!transUserResult.get("code").equals(100000)){
				result.put("code", 110017);
				result.put("message", "user money trans out error");
				return;
			}
			//会员转出成功，开始代理转入
			while(true){
				try {
					this.transMoney(agentName, siteId, "", remitno+"_in", "in", remit, "0", transUserResult, transUserData, "ds_money_key", ip, fromKeyTypeIn, agentMemo,MoneyConfigUtils.SINGLE);
				} catch (Exception e) {
					logger.error("会员转出成功，代理转入异常",e);
				}
				if(transUserResult.get("code").equals(111111)){
					continue;
				}else{
					break;
				}
			}//end whlie

			//上级转入不成功，则钱退还到用户账号
			if(!transUserResult.get("code").equals(100000)){
				while(true){
					try {
						this.transMoney(username, siteId, "", remitno+"_in2", "in", remit, "0", transUserResult, transUserData, "ds_money_key", ip, fromKeyTypeError, "",MoneyConfigUtils.SINGLE);
					} catch (Exception e) {
						logger.error("上级转入不成功，则钱退还到用户账号异常",e);
					}
					if(transUserResult.get("code").equals(111111)){
						continue;
					}else{
						break;
					}
				}//end whlie
				if(!transUserResult.get("code").equals(100000)){
					result.put("code", 110018);
					result.put("message", "user money trans in error");
					return;
				}
				result.put("message", "trans error,money give back");
				result.put("code", 110019);
				return;
			}
			//流程处理成功，返回结果
			result.put("message", "ok");
			result.put("code", 100000);
			return;

		}//end agentTransType = in
		else{
			//代理先转出
			while(true){
				try {
					this.transMoney(agentName, siteId, "", remitno+"_out", "out", remit, "0", transUserResult, transUserData, "ds_money_key", ip, fromKeyTypeOut, agentMemo,MoneyConfigUtils.SINGLE);
				} catch (Exception e) {
					logger.error("代理转出异常",e);
				}
				if(transUserResult.get("code").equals(111111)){
					continue;
				}else{
					break;
				}
			}//end whlie
			if(!transUserResult.get("code").equals(100000)){
				result.put("code", 110017);
				result.put("message", "agentName money trans out error");
				return;
			}
			//代理转出成功，开始会员转入
			while(true){
				try {
					this.transMoney(username, siteId, "", remitno+"_in", "in", remit, "0", transUserResult, transUserData, "ds_money_key", ip, fromKeyTypeIn, memo,MoneyConfigUtils.SINGLE);
				} catch (Exception e) {
					logger.error("代理转出成功，会员转入异常",e);
				}
				if(transUserResult.get("code").equals(111111)){
					continue;
				}else{
					break;
				}
			}//end whlie
			//如果转账不成功，则退还到原来的账号
			if(!transUserResult.get("code").equals(100000)){
				while(true){
					try {
						this.transMoney(agentName, siteId, "", remitno+"_in2", "in", remit, "0", transUserResult, transUserData, "ds_money_key", ip, fromKeyTypeError,"",MoneyConfigUtils.SINGLE);
					} catch (Exception e) {
						logger.error("代理转出失败，退还到原来的账号",e);
					}
					if(transUserResult.get("code").equals(111111)){
						continue;
					}else{
						break;
					}
				}//end whlie
				if(!transUserResult.get("code").equals(100000)){
					result.put("code", 110018);
					result.put("message", "user money trans in error");
					return;
				}
				result.put("message", "trans error,money give back");
				result.put("code", 110019);
				return;
			}
			//流程处理成功，返回结果
			result.put("message", "ok");
			result.put("code", 100000);
			return;


		}//end agentTransType = out
	}
}
