package com.ds.money.service;

import com.alibaba.fastjson.JSONObject;
import com.ds.money.entity.DsMemberMoneyLog;
import com.ds.money.exception.MoneyServiceException;
import com.ds.money.untils.*;
import com.ds.money.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.onetwo.common.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserMoneyServiceImp implements UserMoneyService{

    @Autowired
    private MoneyCenterService moneyCenterService;
    @Autowired
    private MemberMoneyServiceImp moneyService;

    @Override
    public UserMoneyResponse getUserMoney(UserMoneyRequest request) throws MoneyServiceException {
//      long start =System.currentTimeMillis();
        UserMoneyResponse response=new UserMoneyResponse();
        String code = null;
        String result = null;

        Map<String,Object> data = new HashMap<>();
        Map<String,Object> resultdata = new HashMap<>();
        String username = request.getUsername();
        String fromKey = request.getFromKey();
        String key = request.getKey();
        String siteId = request.getSiteId();
        //String hashCode = request.getHashCode();

        data.put("username", username);
        resultdata.put("data", data);
        //String ip = RequestUtils.getClientIp(request);
        //	logger.info(ip);
        if(StringUtils.isBlank(username)){
            log.info("username is null");
            code="username";
            result="username is null";
            getResponse(response,code,result);
            return response;
        }
        if(StringUtils.isBlank(key)){
            log.info("key is null");
            code="key";
            result="key is null";
            getResponse(response,code,result);
            return response;
        }
        /*********************/
        if(StringUtils.isBlank(siteId)){
            log.info("siteId is null");
            code="siteId";
            result="siteId is null";
            getResponse(response,code,result);
            return response;
        }
//        if(StringUtils.isNotBlank(hashCode)){
//            Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
//            if(map == null || map.size() == 0){
//                log.info("web config is wrong");
//                code="web";
//                result="web config is wrong";
//                getResponse(response,code,result);
//                return response;
//            }
//            if(map.get(hashCode) == null){
//                log.info( hashCode+"is not found");
//                code="hashCode";
//                result= hashCode+"is not found";
//                getResponse(response,code,result);
//                return response;
//            }
//            siteId = map.get(hashCode).toString();
//        }
        /*********************/
        //key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
        String md5key = MoneyConfigUtils.getKeyMd5(key);
        String toMd5 = EncryptUtils.toMD5(fromKey+username);
        if(!md5key.equals(toMd5)){
            log.info("md5 is wrong");
            code="md5";
            result="md5 is wrong";
            getResponse(response,code,result);
            return response;
        }
//        Map<String, DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
//        DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
//        if(moneyConfig == null){
//            log.info("fromKey is wrong");
//            code="fromKey";
//            result="fromKey is wrong";
//            getResponse(response,code,result);
//            return response;0
//        }
//		if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
//				!moneyConfig.getIplist().contains(ip)){
//			result.put("message", "ip not allowed");
//			logger.info("此"+ip+"无权访问");
//			result.put("code", 110014);
//			return result;
//		}
        try{
            moneyService.getMoney(username,Integer.valueOf(siteId),response,data);
        }catch(Exception e){
            log.error(e.getMessage());
            code="system";
            result= "system error";
            getResponse(response,code,result);
            return response;
        }
        response.setResult("查询余额成功！");
        return response;
    }

    public  void  getResponse(UserMoneyResponse response,String code,String result){
        response.addErrInfo(code, result, null);
        response.setSuccessCount(0);
    }

    @Override
    public TransMoneyResponse transMoney(TransMoneyRequest request) throws MoneyServiceException {
        TransMoneyResponse response=new TransMoneyResponse();
        String code = null;
        String result = null;
        String ip=null;
        Map<String,Object> resultData = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        try{
            String username = request.getUsername();
            String remitno = request.getRemitno();//流水号
            String transType = request.getTransType();//转入转出标识
            String remit = request.getRemit();
            String wagerCancel = request.getWagerCancel();//注单是否取消 1：取消    0否
            String fromKey = request.getFromKey();
            String siteId = request.getSiteId();

            String key = request.getKey();
            //String ip = RequestUtils.getClientIp(request);

            String fromKeyType = request.getFromKeyType();
            String memo = request.getMemo();
            String gameType = request.getGameType();
            String gameId = request.getGameId();

//            if(StringUtils.isNotBlank(gameType)){
//                Map<String,Integer> gameTypeMap = moneyCenterService.getGameTypeMap();
//                fromKeyType = gameTypeMap.get(gameType+"_"+transType.toUpperCase()).toString();
//            }
            if(StringUtils.isBlank(username)){
                code="username";
                result= "username is null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if(StringUtils.isBlank(remitno)){
                code="remitno";
                result= "remitno is null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if(StringUtils.isBlank(transType)
                    ||(!transType.equals("in")&&!transType.equals("out"))
                    &&(!transType.equals("IN")&&!transType.equals("OUT"))){
                code="transType";
                result= "transType is wrong";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
        }
            transType = transType.toLowerCase();
            remit = DataUtils.getStringNumber(remit);
            if(StringUtils.isBlank(remit)
                    ||!DataUtils.checkNum(remit)
                    ||Double.valueOf(remit)<0){ //去掉等于 0 的条件
                code="remit";
                result= "remit must be >= 0";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if(!StringUtils.isBlank(wagerCancel)
                    &&(!wagerCancel.equals("0")&&!wagerCancel.equals("1"))){
                code="wagerCancel";
                result= "wagerCancel is wrong";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if(StringUtils.isBlank(fromKey)){
                code="fromKey";
                result= "fromKey is wrong";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if(StringUtils.isBlank(key)){
                code="key";
                result= "key is null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            if(StringUtils.isBlank(siteId)){
                code="siteId";
                result= "siteId is null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
//            if(StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)){
//                code="siteId";
//                result= "siteId is wrong";
//                response.addErrInfo(code, result, null);
//                response.setSuccessCount(0);
//                return response;
//            }
//            if(StringUtils.isNotBlank(hashCode)){
//                Map<String, Integer> map = moneyCenterService.getSiteHashcodeMap();
//                if(map == null || map.size() == 0){
//                    code="web";
//                    result= "web config is wrong";
//                }
//                if(map.get(hashCode) == null){
//                    code="hashCode";
//                    result=  hashCode+"hashCode is wrong";
//                }
//                siteId = map.get(hashCode).toString();
//            }

            //key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（fromKey+username）C 无意义字符串长度6码
            String md5key = MoneyConfigUtils.getKeyMd5(key);
            String toMd5 = EncryptUtils.toMD5(fromKey+username);
            if(!md5key.equals(toMd5)){
                code="md5";
                result= "md5 is wrong";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
//            Map<String,DsMoneyConfig> moneyConfigMap = moneyCenterService.getMoneyConfigMap();
//            DsMoneyConfig moneyConfig = moneyConfigMap.get(fromKey);
//            if(moneyConfig == null){
//                code="fromKey";
//                result= "fromKey is wrong";
//            }
//			if(StringUtils.isNotBlank(moneyConfig.getIplist()) &&
//					!moneyConfig.getIplist().contains(ip)){
//				result.put("message", "ip is not allowed");
//				logger.info("此"+ip+"无权访问");
//				result.put("code", 110014);
//				return result;
//			}

//            if(StringUtils.isBlank(fromKeyType) || !DataUtils.checkNum(fromKeyType)){
//                code="fromKeyType";
//                result= "fromKeyType is wrong";
//            }else{
//                Map<String, Set<Long>> fromKeyTypeMap = moneyCenterService.getFromKeyTypeMap();
//                Set fromKeyTypeSet = fromKeyTypeMap.get(fromKey);
//                if(fromKeyTypeSet == null){
//                    code="fromKeyType";
//                    result= "fromKeyType is not found in db";
//                }
//                if(!fromKeyTypeSet.contains(Long.valueOf(fromKeyType))){
//                    code="fromKeyType";
//                    result= "fromKeyType is not found in db";
//                }
//            }

            if(StringUtils.isBlank(memo)){
                code="memo";
                result= "memo is null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            memo = moneyCenterService.setMemo(memo,gameType,gameId);

            resultData.put("data", data);
            data.put("username", username);
            /**
             * 为提高效率，批量转账和单个转账分开
             */
            String transNum = "single";
            moneyService.transMoney(username,Integer.valueOf(siteId),remitno,transType,remit,wagerCancel,resultData,data,fromKey,ip,fromKeyType,memo,transNum);
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        response.setResult("转账成功！");
        return response;
    }

    @Override
    public MemberMoneyLogResponse memberMoneyLog(MemberMoneyLogRequest request) throws MoneyServiceException {
        MemberMoneyLogResponse response =new MemberMoneyLogResponse();
        String code = null;
        String result = null;
        JSONObject resultDate = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            String username = request.getUsername();
            String remitno = request.getRemitno();

            String beginTime = request.getBeginTime();
            String endTime = request.getEndTime();
            String page = request.getPage();
            String pageSize = request.getPageSize();
            String fromKey = request.getFromKey();
            String key = request.getKey();
            String siteId = request.getSiteId();
            //String fromKeyType = request.getFromKeyType();
            //String agentLevel = request.getAgentLevel();
            //String agentName = request.getAgentName();

            //String fromKeyTypeIsTotal = request.getFromKeyTypeIsTotal();// 否是统计现金流
            //String userInfoIsDetail = request.getUserInfoIsDetail();// 是否查询用户的详细信息

            // String ip = getTCPClientIp();
            if (StringUtils.isNotBlank(beginTime)) {
                if (!DateUtils.isValidDate(beginTime)) {
                    code="beginTime";
                    result= "beginTime is wrong";
                    response.addErrInfo(code, result, null);
                    response.setSuccessCount(0);
                    return response;
                }
            }
            if (!StringUtils.isNotBlank(endTime)) {
                if (DateUtils.isValidDate(endTime)) {
                    code="endTime";
                    result= "endTime is wrong";
                    response.addErrInfo(code, result, null);
                    response.setSuccessCount(0);
                    return response;
                }
            }

            if (StringUtils.isNotBlank(page) && !DataUtils.checkNum(page)) {
                code="page";
                result= "page param is wrong";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (StringUtils.isNotBlank(pageSize)
                    && !DataUtils.checkNum(pageSize)) {
                code="pageSize";
                result= "pageSize param is wrong";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            if (StringUtils.isBlank(fromKey)) {
                code="fromKey";
                result= "fromKey is null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (StringUtils.isBlank(key)) {
                code="key";
                result= "key is null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (StringUtils.isBlank(siteId) ) {
                code="siteId";
                result= "siteId is all null";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
//            if (StringUtils.isNotBlank(siteId) && !DataUtils.checkNum(siteId)) {
//                code="siteId";
//                result= "siteId is wrong";
//                response.addErrInfo(code, result, null);
//                response.setSuccessCount(0);
//                return response;
//            }
//            if (StringUtils.isNotBlank(hashCode)) {
//                Map<String, Integer> map = moneyCenterService
//                        .getSiteHashcodeMap();
//                if (map == null || map.size() == 0) {
//                    code="site config";
//                    result= "site config is null";
//                }
//                if (map.get(hashCode) == null) {
//                    code="hashCode";
//                    result= hashCode + "is not found";
//                }
//                siteId = map.get(hashCode).toString();
//            }
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
            queryParam.setRemitno(remitno);
            moneyService.memberMoneyLog(resultDate, response, queryParam, pagation);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        response.setResult("查询成功！");
        return response;
    }
}
