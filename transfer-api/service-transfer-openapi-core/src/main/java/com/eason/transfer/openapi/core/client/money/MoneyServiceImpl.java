package com.eason.transfer.openapi.core.client.money;

import com.eason.transfer.openapi.core.api.exception.OpenApiBaseException;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexSwiper;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexSwiperExample;
import com.eason.transfer.openapi.core.client.money.model.MoneyBalanceGetRequest;
import com.eason.transfer.openapi.core.client.money.model.MoneyBalanceGetResponse;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfo;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfoExample;
import com.eason.transfer.openapi.core.client.user.dao.mapper.TUserInfoMapper;
import com.eason.transfer.openapi.core.client.user.model.UserInfoGetResponse;
import com.eason.transfer.openapi.core.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("moneyServiceImpl")
@Slf4j
public class MoneyServiceImpl {
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
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
            requestParam.put("siteId","1040");
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

}
