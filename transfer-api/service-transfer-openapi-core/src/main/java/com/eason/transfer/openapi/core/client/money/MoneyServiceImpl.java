package com.eason.transfer.openapi.core.client.money;

import com.eason.transfer.openapi.core.api.exception.OpenApiBaseException;
import com.eason.transfer.openapi.core.client.money.model.MoneyBalanceGetRequest;
import com.eason.transfer.openapi.core.client.money.model.MoneyBalanceGetResponse;
import com.eason.transfer.openapi.core.client.money.model.MoneyINRequest;
import com.eason.transfer.openapi.core.client.money.model.MoneyOUTResponse;
import com.eason.transfer.openapi.core.client.user.UserServiceImpl;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfo;
import com.eason.transfer.openapi.core.client.user.dao.mapper.TUserInfoMapper;
import com.eason.transfer.openapi.core.client.user.model.UserInfoGetRequest;
import com.eason.transfer.openapi.core.client.user.model.UserInfoGetResponse;
import com.eason.transfer.openapi.core.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

@Service("moneyServiceImpl")
@Slf4j
public class MoneyServiceImpl {
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;

    public MoneyBalanceGetResponse getBalance(MoneyBalanceGetRequest request) throws OpenApiBaseException {
        try{
            MoneyBalanceGetResponse response=new MoneyBalanceGetResponse();
            String code = null;
            String result = null;
            if (StringUtils.isEmpty(request.getType())) {
                code ="type";
                result ="平台类型不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            TUserInfo tUserInfo=tUserInfoMapper.selectByPrimaryKey(Long.parseLong(request.getUserId()));
            if (tUserInfo==null){
                code="username";
                response.addErrInfo(code, "用户名不存在", null);
                response.setSuccessCount(0);
                return response;
            }
            response.setUserId(tUserInfo.getId());
            response.setUsername(tUserInfo.getUsername());
            //获取该用户的平台余额
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            Map<String, String> requestParam= new TreeMap<>();
            requestParam.put("username", tUserInfo.getUsername());
            requestParam.put("live",request.getType());
            requestParam.put("siteId",request.getAppKey());
            requestParam.put("cur","CNY");
            String keyB="df943b52c6182761bf75e7cbfc1ad85d";
            TimeZone timeZone = TimeZone.getTimeZone("GMT-4:00");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.setTimeZone(timeZone);
            String key="1234"+EncryptUtil.MD5(
                    tUserInfo.getUsername()+keyB+simpleDateFormat.format(new Date()))+"0";
            requestParam.put("key",key);

            String url="http://52.194.214.25:8095/transfer-api/queryBalance";
            log.info("钱包拉取请求={}",url);
            log.info("请求参数={}",requestParam);
            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
            map.setAll(requestParam);
            RestTemplate restTemplate=new RestTemplate();
            JSONObject resultObj=restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(map,headers),JSONObject.class).getBody();
            log.info("钱包拉取返回结果={}",resultObj.toString());
            if(!"10000".equals(resultObj.getString("status"))){
                code="money";
                response.addErrInfo(code, "用户钱包余额获取出错："+resultObj.getString("message"), null);
                response.setSuccessCount(0);
                return response;
            }
            String money=new DecimalFormat("0.00").format(resultObj.getDouble("balance"));
            response.setMoney(money);
            return response;
        }catch (Exception e){
            log.error("用户钱包余额获取出错", e);
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }
    }

    public MoneyOUTResponse transfer(MoneyINRequest request) throws OpenApiBaseException {
        try{
            MoneyOUTResponse response=new MoneyOUTResponse();
            String code = null;
            String result = null;
            if (StringUtils.isEmpty(request.getAction())) {
                code ="action";
                result ="转账类型不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (request.getAction().split("_").length!=2 || request.getTransMethod().split("_").length!=2) {
                code ="action";
                result ="转账类型格式不正确";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (request.getCredit()==null){
                code ="credit";
                result ="转账金额不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (Integer.parseInt(request.getCredit())<=1){
                code ="credit";
                result ="转账金额必须大于1";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            UserInfoGetRequest userInfoGetRequest=new UserInfoGetRequest();
            BeanUtils.copyProperties(request,userInfoGetRequest);
            UserInfoGetResponse userInfoGetResponse=userServiceImpl.getUserInfo(userInfoGetRequest);
            if (userInfoGetResponse.getErrorCount()>=1){
                code="userInfoGet";
                response.addErrInfo(code, userInfoGetResponse.getErrInfoList().get(0).getErrorDes(), null);
                response.setSuccessCount(0);
                return response;
            }
            if ("OUT".equalsIgnoreCase(request.getAction()) &&
                    Integer.parseInt(request.getCredit())<userInfoGetResponse.getMainMoney()){
                code ="credit";
                result ="转出余额不够";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            //转入转出平台余额
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            String type="OUT";
            if (request.getAction().endsWith("_99999") && request.getTransMethod().endsWith("_main")){
                request.setAction(request.getAction().split("_")[0]);
                request.setTransMethod(request.getTransMethod().split("_")[0]);
                type="IN";
            }
            if (request.getAction().startsWith("99999_") && request.getTransMethod().startsWith("main_")){
                request.setAction(request.getAction().split("_")[1]);
                request.setTransMethod(request.getTransMethod().split("_")[1]);
            }
            Map<String, String> requestParam= new TreeMap<>();
            requestParam.put("username", userInfoGetResponse.getUsername());
            requestParam.put("billno",request.getAppKey()+userInfoGetResponse.getUsername()+"_"+request.getTransMethod()+type+"_"+
                    RandomStringUtils.randomAlphanumeric(10));
            requestParam.put("live",request.getAction());
            requestParam.put("siteId",request.getAppKey());
            requestParam.put("cur","CNY");
            requestParam.put("type",type);
            requestParam.put("credit",request.getCredit());
            requestParam.put("transMethod",request.getTransMethod());
            String keyB="df943b52c6182761bf75e7cbfc1ad85d";
            TimeZone timeZone = TimeZone.getTimeZone("GMT-4:00");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.setTimeZone(timeZone);
            String key="1234"+EncryptUtil.MD5(
                    userInfoGetResponse.getUsername()+keyB+simpleDateFormat.format(new Date()))+"0";
            requestParam.put("key",key);

            String url="http://52.194.214.25:8095/transfer-api/transfer";
            log.info("转账接口请求={}",url);
            log.info("请求参数={}",requestParam);
            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
            map.setAll(requestParam);

            RestTemplate restTemplate=new RestTemplate();
            JSONObject resultObj=restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(map,headers),JSONObject.class).getBody();
            log.info("转账接口返回结果={}",resultObj.toString());
            if(!"10000".equals(resultObj.getString("status"))){
                code="money";
                response.addErrInfo(code, "转账接口获取出错："+resultObj.getString("message"), null);
                response.setSuccessCount(0);
                return response;
            }
            response.setMessage(resultObj.getString("message"));
            response.setStatus(resultObj.getString("status"));
            return response;
        }catch (Exception e){
            log.error("转账接口获取出错", e);
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }
    }

}
