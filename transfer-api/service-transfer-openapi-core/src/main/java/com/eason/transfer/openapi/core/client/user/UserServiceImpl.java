package com.eason.transfer.openapi.core.client.user;

import com.eason.transfer.openapi.core.api.exception.OpenApiBaseException;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfo;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfoExample;
import com.eason.transfer.openapi.core.client.user.dao.mapper.TUserInfoMapper;
import com.eason.transfer.openapi.core.client.user.model.*;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl {
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public UserInfoResponse register(UserInfoRequest request) throws OpenApiBaseException {
        try{
            UserInfoResponse response=new UserInfoResponse();
            String code = null;
            String result = null;
            if (StringUtils.isBlank(request.getUsername())) {
                code ="username";
                result ="用户名不能为空";
            } else if (StringUtils.isBlank(request.getPassword())) {
                code ="password";
                result ="密码不能为空";
            } else if (StringUtils.isBlank(request.getSurePassword())) {
                code ="surePassword";
                result ="确认密码不能为空";
            } else if (StringUtils.isBlank(request.getRealName())) {
                code ="realName";
                result ="真实用户名不能为空";
            } else if (StringUtils.isBlank(request.getPhoneNum())) {
                code ="phoneNum";
                result ="手机号码不能为空";
            }

            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            if (!(request.getUsername().length()>=4 && request.getUsername().length()<=10)) {
                code ="username";
                result ="用户名请输入4-10位字母或数字";
            } else if (!(request.getPassword().length()>=6 && request.getUsername().length()<=10)) {
                code ="password";
                result ="密码请输入6-10位字母或数字";
            } else if (!request.getPassword().equals(request.getSurePassword())) {
                code ="surePassword";
                result ="确认密码与密码不匹配";
            }

            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            TUserInfoExample example=new TUserInfoExample();
            example.createCriteria()
                    .andUsernameEqualTo(request.getUsername());
            List<TUserInfo> list=tUserInfoMapper.selectByExample(example);
            if (list.size()>0){
                code="username";
                response.addErrInfo(code, "用户已经注册", null);
                response.setSuccessCount(0);
                return response;
            }

            TUserInfo tUserInfo=new TUserInfo();
            BeanUtils.copyProperties(request,tUserInfo);
            tUserInfo.setPassword(EncryptUtil.MD5(tUserInfo.getPassword()));
            tUserInfo.setStatus(Byte.valueOf("0")); //0为启用，1为停用
            tUserInfo.setCreateTime(new Date());
            int flag=tUserInfoMapper.insert(tUserInfo);
            if (flag==0){
                throw new OpenApiBaseException("用户注册失败");
            }
            response.setResult("用户注册成功");
            return response;
        }catch (Exception e){
            log.error("用户注册失败", e);
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }

    }

    @Transactional
    public UserLoginResponse login(UserLoginRequest request) throws OpenApiBaseException {
        try{
            UserLoginResponse response=new UserLoginResponse();
            String code = null;
            String result = null;
            if (StringUtils.isBlank(request.getUsername())) {
                code ="username";
                result ="用户名不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (StringUtils.isBlank(request.getPassword())) {
                code ="password";
                result ="密码不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            TUserInfoExample example=new TUserInfoExample();
            example.createCriteria()
                    .andUsernameEqualTo(request.getUsername())
                    .andPasswordEqualTo(EncryptUtil.MD5(request.getPassword()));
            List<TUserInfo> list=tUserInfoMapper.selectByExample(example);
            if (list.size()!=1){
                code="login";
                response.addErrInfo(code, "用户或者密码错误", null);
                response.setSuccessCount(0);
                return response;
            }
            TUserInfo userInfo=list.get(0);
            if (userInfo.getStatus()!=0){
                code="user_status_stop";
                response.addErrInfo(code, "用户账号已被锁定，请联系客服", null);
                response.setSuccessCount(0);
                return response;
            }
            String token=EncryptUtil.MD5(userInfo.getId()+userInfo.getUsername()+userInfo.getPassword()+System.currentTimeMillis());
            stringRedisTemplate.opsForValue().set(request.getAppKey()+"_"+userInfo.getUsername(),
                    token,1, TimeUnit.HOURS);
            response.setUserId(userInfo.getId());
            response.setUsername(userInfo.getUsername());
            response.setToken(token);
            response.setResult("用户登录成功");
            return response;
        }catch (Exception e){
            log.error("用户登录失败", e);
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }
    }

    @Transactional
    public UserInfoGetResponse getUserInfo(UserInfoGetRequest request) throws OpenApiBaseException {
        try{
            UserInfoGetResponse response=new UserInfoGetResponse();
            String code = null;
            String result = null;
            if (StringUtils.isEmpty(request.getUsername())) {
                code ="username";
                result ="用户名不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if (StringUtils.isEmpty(request.getToken())) {
                code ="token";
                result ="用户token不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }else{
                String token=stringRedisTemplate.opsForValue().get(request.getAppKey()+"_"+request.getUsername());
                if(StringUtils.isEmpty(token)){
                    code ="token";
                    result ="用户token已经失效，请重新登录";
                    response.addErrInfo(code, result, null);
                    response.setSuccessCount(0);
                    return response;
                }else if(!token.equals(request.getToken())){
                    code ="token";
                    result ="用户token错误，请重新登录";
                    response.addErrInfo(code, result, null);
                    response.setSuccessCount(0);
                    return response;
                }
            }
            TUserInfoExample example=new TUserInfoExample();
            example.createCriteria()
                    .andUsernameEqualTo(request.getUsername());
            List<TUserInfo> list=tUserInfoMapper.selectByExample(example);
            if (list.size()!=1){
                code="username";
                response.addErrInfo(code, "用户名不存在", null);
                response.setSuccessCount(0);
                return response;
            }
            TUserInfo tUserInfo=list.get(0);
            BeanUtils.copyProperties(tUserInfo,response);
            response.setUserId(tUserInfo.getId());
            response.setToken(stringRedisTemplate.opsForValue().get(request.getAppKey()+"_"+tUserInfo.getUsername()));
            //获取该用户的钱包余额
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            Map<String, String> requestParam= new TreeMap<>();
            requestParam.put("username", tUserInfo.getUsername());
            requestParam.put("fromKey","ds_money_key");
            requestParam.put("siteId","1040");
            requestParam.put("key","12345"+EncryptUtil.MD5("ds_money_key"+tUserInfo.getUsername())+"123456");

            String url="http://10.10.4.74:8094/ds-money-api/getMoney";
            log.info("钱包拉取请求={}",url);
            log.info("请求参数={}",requestParam);
            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
            map.setAll(requestParam);

            JSONObject resultObj=restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(map,headers),JSONObject.class).getBody();
            log.info("钱包拉取返回结果={}",resultObj.toString());
            if(!"100000".equals(resultObj.getString("code"))){
                code="money";
                response.addErrInfo(code, "用户钱包余额获取出错："+resultObj.getString("message"), null);
                response.setSuccessCount(0);
                return response;
            }
            response.setMainMoney(resultObj.getJSONObject("data").getDouble("money"));
            return response;
        }catch (Exception e){
            log.error("用户登录失败", e);
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }
    }
}
