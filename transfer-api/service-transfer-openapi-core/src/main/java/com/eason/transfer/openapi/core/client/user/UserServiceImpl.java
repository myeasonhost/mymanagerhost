package com.eason.transfer.openapi.core.client.user;

import com.eason.transfer.openapi.core.api.exception.OpenApiBaseException;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfo;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfoExample;
import com.eason.transfer.openapi.core.client.user.dao.mapper.TUserInfoMapper;
import com.eason.transfer.openapi.core.client.user.model.*;
import com.eason.transfer.openapi.core.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl {
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
            if (request.getUserId()==null) {
                code ="userId";
                result ="用户ID不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            TUserInfo tUserInfo=tUserInfoMapper.selectByPrimaryKey(request.getUserId());
            if (tUserInfo==null){
                code="getUserInfo";
                response.addErrInfo(code, "用户不存在", null);
                response.setSuccessCount(0);
                return response;
            }
            BeanUtils.copyProperties(tUserInfo,response);
            response.setUserId(tUserInfo.getId());
            response.setToken(stringRedisTemplate.opsForValue().get(request.getAppKey()+"_"+tUserInfo.getUsername()));
            //获取该用户的钱包余额
            response.setMainMoney(10.0);
            return response;
        }catch (Exception e){
            log.error("用户登录失败", e);
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }
    }
}
