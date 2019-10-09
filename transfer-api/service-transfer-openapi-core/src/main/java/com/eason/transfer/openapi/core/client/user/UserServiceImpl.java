package com.eason.transfer.openapi.core.client.user;

import com.eason.transfer.openapi.core.api.exception.OpenApiBaseException;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfo;
import com.eason.transfer.openapi.core.client.user.dao.entity.TUserInfoExample;
import com.eason.transfer.openapi.core.client.user.dao.mapper.TUserInfoMapper;
import com.eason.transfer.openapi.core.client.user.model.UserInfoRequest;
import com.eason.transfer.openapi.core.client.user.model.UserInfoResponse;
import com.eason.transfer.openapi.core.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl {
    @Autowired
    private TUserInfoMapper tUserInfoMapper;

    @Transactional
    public UserInfoResponse register(UserInfoRequest request) throws OpenApiBaseException {
        try{
            String code="user.info.register";
            UserInfoResponse response=new UserInfoResponse();
            String result = null;
            if (StringUtils.isBlank(request.getUsername())) {
                result ="用户名不能为空";
            } else if (StringUtils.isBlank(request.getPassword())) {
                result ="密码不能为空";
            } else if (StringUtils.isBlank(request.getSurePassword())) {
                result ="确认密码不能为空";
            } else if (StringUtils.isBlank(request.getRealName())) {
                result ="真实用户名不能为空";
            }

            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            if (!(request.getUsername().length()>=4 && request.getUsername().length()<=10)) {
                result ="用户名请输入4-10位字母或数字";
            } else if (!(request.getPassword().length()>=6 && request.getUsername().length()<=10)) {
                result ="密码请输入6-10位字母或数字";
            } else if (!request.getPassword().equals(request.getSurePassword())) {
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
                response.addErrInfo(code, "用户已经注册", null);
                response.setSuccessCount(0);
                return response;
            }

            TUserInfo tUserInfo=new TUserInfo();
            BeanUtils.copyProperties(request,tUserInfo);
            tUserInfo.setPassword(EncryptUtil.MD5(tUserInfo.getPassword()));
            tUserInfo.setStatus(Byte.valueOf("0")); //0为启用，1为停用
            tUserInfo.setLoginTime(new Date());
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

}
