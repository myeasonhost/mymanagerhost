package com.eason.transfer.openapi.user.api.app;

import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.core.sdk.user.IUserService;
import com.eason.transfer.openapi.core.sdk.user.exception.UserServiceException;
import com.eason.transfer.openapi.core.sdk.user.model.*;
import com.eason.transfer.openapi.user.api.app.dao.entity.UserCodePo;
import com.eason.transfer.openapi.user.api.app.dao.entity.UserInfoPo;
import com.eason.transfer.openapi.user.api.app.dao.entity.UserInfoPoExample;
import com.eason.transfer.openapi.user.api.app.dao.entity.VerifyCodeLogPo;
import com.eason.transfer.openapi.user.api.app.dao.mapper.UserCodePoMapper;
import com.eason.transfer.openapi.user.api.app.dao.mapper.UserInfoPoMapper;
import com.eason.transfer.openapi.user.api.app.dao.mapper.VerifyCodeLogMapper;
import com.eason.transfer.openapi.user.api.app.model.CodeConfigModel;
import com.eason.transfer.openapi.user.api.app.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserInfoPoMapper userMapper;
    @Autowired
    private VerifyCodeLogMapper verifyCodeLogMapper;
    @Autowired
    private UserCodePoMapper userCodeMapper;
    @Autowired
    private CodeMgrImpl codeMgrImpl;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Value("${user.file.img.local}")
    private String fileImgLocal;
//    @Value("${user.file.img.remote}")
    private String fileImgRemote;

    @Override
    public RegisterResponse register(RegisterRequest request) throws UserServiceException {
        RegisterResponse response = new RegisterResponse();
        CodeConfigModel codeConfigModel=codeMgrImpl.getCodeConfigConditional();
        String code = null;
        String result = null;
        try {
            // 1. 参数验证
            if(StringUtils.isBlank(request.getPhone())) {
                code ="phone";
                result ="手机号不能为空";
            } else if (StringUtils.isBlank(request.getPassword())){
                code ="password";
                result ="密码不能为空";
            } else if (StringUtils.isBlank(request.getValidateCode())){
                code ="code";
                result ="验证码不能为空";
            }
            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            // 2.验证注册码  1:注册 2：重置密码
            HashMap<String, Object> resultCodeMap =codeMgrImpl.verifySendCode(request.getPhone(),1,codeConfigModel.getCode_valid_time());
            boolean b=(boolean)resultCodeMap.get("flag");
            if(!b){
                String msg = (String)resultCodeMap.get("errorMsg");
                throw new UserServiceException(msg);
            }

            // 3.验证用户是否注册
            UserInfoPoExample example=new UserInfoPoExample();
            example.createCriteria().andPhoneNumEqualTo(request.getPhone());
            List<UserInfoPo> userInfoPoList = this.userMapper.selectByExample(example);
            if(userInfoPoList != null && userInfoPoList.size()>0){
                code ="userPo";
                result ="用户已经注册";
            }
            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            // 4.业务验证 messae method overload
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            // 验证码类型 1为注册 2为重置
            hashMap.put("type", 1); //注册
            // 用户手机号 1为未使用,2为已经验证失效,3为已经验证成功
            hashMap.put("phone", request.getPhone());
            hashMap.put("state", 1);
            // 验证码有效时间
            hashMap.put("codeVaildTime", codeConfigModel.getCode_valid_time());
            // 多长时间内失败3次则禁止再进行验证
            hashMap.put("verFailTime", codeConfigModel.getVerFailTime());
            // 如果规定时间内验证失败超过3次，禁止验证多长时间
            hashMap.put("verFialForbidTime", codeConfigModel.getVerFialForbidTime());
            HashMap<String, Object>  resultMap = codeMgrImpl.getUserCodeForDB(hashMap);
            if (resultMap != null) {
                Boolean flag = (Boolean) resultMap.get("flag");
                if (flag) {
                    UserCodePo usersCode = (UserCodePo) resultMap.get("usersCode");
                    VerifyCodeLogPo verCode = new VerifyCodeLogPo();
                    verCode.setPhone(request.getPhone());
                    verCode.setErrorCode(request.getValidateCode());
                    verCode.setTrueCode(usersCode.getCode());
                    verCode.setVerTime(new Date());
                    verCode.setType(1);
                    if (usersCode.getCode() != null && usersCode.getCode().equals(request.getValidateCode())) {
                        //(1)更新user表的注册状态
                        UserInfoPo userInfoPo=new UserInfoPo();
                        userInfoPo.setUsername(request.getPhone());
                        userInfoPo.setPhoneNum(request.getPhone());
                        userInfoPo.setPassword(request.getPassword());
                        userInfoPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        userInfoPo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        userInfoPo.setCreateBy("APP");
                        userInfoPo.setStatus((byte)0);//0为启用，1为停用

                        userMapper.insert(userInfoPo);
                        //(2)验证码匹配 0为不匹配 1为匹配
                        verCode.setVerResult(1);
                        //(3)将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        //(4)将验证码标记为已经使用
                        UserCodePo cd = new UserCodePo();
                        cd.setId(usersCode.getId());
                        cd.setUserId(userInfoPo.getId().intValue());
                        cd.setState(3);  //1为未使用,2为已经验证失效,3为已经验证成功
                        cd.setUpdateTime(new Date());
                        userCodeMapper.updateModelById(cd);
                        //(5)验证完毕，清除缓存
                        this.stringRedisTemplate.delete(request.getPhone());
                        response.setResult("用户手机注册成功");
                        response.setUserId(userInfoPo.getId().intValue()); //返回userid
                        //(6)发送推送
//                        pushServiceImpl.pushUserService(userInfoPo.getUserId(), PushTemplatePo.STATUS.REGIST_SUCCESS);
                        return response;
                    } else {
                        // 验证码不匹配 0为不匹配 1为匹配
                        verCode.setVerResult(0);
                        // 将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        response.addErrInfo("verifyCode", "验证码不匹配", null);
                        response.setSuccessCount(0);
                        return response;
                    }
                } else {
                    response.addErrInfo("verifyUserInfo", (String) resultMap.get("errorMsg"), null);
                    response.setSuccessCount(0);
                    return response;
                }
            } else {
                // 无法获取有效的验证码
                response.addErrInfo("verifyCode", "无法获取有效的验证码", null);
                response.setSuccessCount(0);
                return response;
            }

        } catch (Exception e) {
            log.error("对用户的验证码进行校验时出错,userPhone:" + request.getPhone(), e);
            throw new UserServiceException(e.getMessage());
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequestVo) throws UserServiceException {
        LoginResponse response = new LoginResponse();
        String code = null;
        String result = null;
        try {
            // ①参数验证
            if (StringUtils.isBlank(loginRequestVo.getUsername())) {
                code ="userName";
                result ="用户名不能为空";
            }
            if (StringUtils.isBlank(loginRequestVo.getPassword())) {
                code ="password";
                result ="密码不能为空";
            }

            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            // ②验证用户
            UserInfoPoExample example=new UserInfoPoExample();
            example.createCriteria().andUsernameEqualTo(loginRequestVo.getUsername())
                    .andPasswordEqualTo(loginRequestVo.getPassword());
            List<UserInfoPo> userInfoPoList = this.userMapper.selectByExample(example);
            if (userInfoPoList==null || userInfoPoList.size()==0) {
                response.addErrInfo("userPo", "用户名或密码错误", null);
                response.setSuccessCount(0);
                return response;
            }
            UserInfoPo userPo=userInfoPoList.get(0);
            if (!userPo.getPassword().equals(loginRequestVo.getPassword())) {
                response.addErrInfo("userPo", "用户密码错误", null);
                response.setSuccessCount(0);
                return response;
            }
            String token = TokenUtil.getToken();
            response.setToken(token);
            stringRedisTemplate.opsForValue().set("token:"+token,userPo.getId()+"",6, TimeUnit.HOURS);

            //3更新用户登陆时间
            userPo.setUpdateTime(new Date());
            this.userMapper.updateByPrimaryKey(userPo);

            response.setUserId(userPo.getId().intValue());
            response.setNickname(userPo.getNickName());
            response.setUsername(userPo.getUsername());
            response.setAvatar(userPo.getAvatar());
            return response;
        } catch (Exception e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    @Override
    public RegisterResponse reset(@RequestBody RegisterRequest request) throws UserServiceException {
        RegisterResponse response = new RegisterResponse();
        CodeConfigModel codeConfigModel=codeMgrImpl.getCodeConfigConditional();
        String code = null;
        String result = null;
        try {
            // 1. 参数验证
            if(StringUtils.isBlank(request.getPhone())) {
                code ="phone";
                result ="手机号不能为空";
            } else if (StringUtils.isBlank(request.getPassword())){
                code ="password";
                result ="重置密码不能为空";
            } else if (StringUtils.isBlank(request.getValidateCode())){
                code ="code";
                result ="验证码不能为空";
            }
            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            // 业务验证 messae method overload
            HashMap<String, Object> hashMap = codeMgrImpl.getVerifCondition();
            // 验证码类型
            hashMap.put("type", 2); //2=重置
            // 用户手机号
            hashMap.put("phone", request.getPhone());
            hashMap.put("state", 1);
            // 验证码有效时间
            hashMap.put("codeVaildTime", codeConfigModel.getCode_valid_time());
            // 多长时间内失败3次则禁止再进行验证
            hashMap.put("verFailTime", codeConfigModel.getVerFailTime());
            // 如果规定时间内验证失败超过3次，禁止验证多长时间
            hashMap.put("verFialForbidTime", codeConfigModel.getVerFialForbidTime());
            HashMap<String, Object>  resultMap = codeMgrImpl.getUserCodeForDB(hashMap);
            if (resultMap != null) {
                Boolean flag = (Boolean) resultMap.get("flag");
                UserInfoPoExample example=new UserInfoPoExample();
                example.createCriteria().andPhoneNumEqualTo(request.getPhone());
                List<UserInfoPo> userInfoPoList = this.userMapper.selectByExample(example);
                if(userInfoPoList==null || userInfoPoList.size()==0){
                    // 此用户不存在
                    response.addErrInfo("userPo", "该用户不存在", null);
                    response.setSuccessCount(0);
                    return response;
                }
                UserInfoPo userInfo = userInfoPoList.get(0);
                if (flag && userInfo!=null) {
                    UserCodePo usersCode = (UserCodePo) resultMap.get("usersCode");
                    VerifyCodeLogPo verCode = new VerifyCodeLogPo();
                    verCode.setPhone(request.getPhone());
                    verCode.setErrorCode(request.getValidateCode());
                    verCode.setTrueCode(usersCode.getCode());
                    verCode.setVerTime(new Date());
                    verCode.setType(2);
                    if (usersCode.getCode() != null && usersCode.getCode().equals(request.getValidateCode())) {
                        //(1)更新user表的密码
                        userInfo.setPassword(request.getPassword());
                        userInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        this.userMapper.updateByPrimaryKey(userInfo);
                        //(2)验证码 匹配=1 不匹配=0
                        verCode.setVerResult(1);
                        //(3)将验证码标记为已经使用 1为未使用,2为已经验证失效,3为已经验证成功
                        UserCodePo cd = new UserCodePo();
                        cd.setId(usersCode.getId());
                        cd.setUserId(userInfo.getId().intValue());
                        cd.setState(3);
                        cd.setUpdateTime(new Date());
                        userCodeMapper.updateModelById(cd);
                        //(4)将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        //(5)验证完毕，清除缓存
                        this.stringRedisTemplate.delete(request.getPhone());
                        response.setResult("用户重置密码成功！");
                        response.setUserId(userInfo.getId().intValue()); //返回userid
                        return response;
                    } else {
                        // 验证码不匹配 0为不匹配 1为匹配
                        verCode.setVerResult(0);
                        // 将验证记录插入数据库
                        verifyCodeLogMapper.insertModel(verCode);
                        response.addErrInfo("verifyCode", "验证码不匹配", null);
                        response.setSuccessCount(0);
                        return response;
                    }
                } else {
                    response.addErrInfo("verifyUserInfo", (String) resultMap.get("errorMsg"), null);
                    response.setSuccessCount(0);
                    return response;
                }
            } else {
                // 无法获取有效的验证码
                response.addErrInfo("verifyCode", "无法获取有效的验证码", null);
                response.setSuccessCount(0);
                return response;
            }

        } catch (Exception e) {
            log.error("重置密码时出错,userPhone:" + request.getPhone(), e);
            throw new UserServiceException(e.getMessage());
        }
    }

    @Override
    public UserCodeResponse getValidateCode(UserCodeRequest request) throws UserServiceException {
        UserCodeResponse response = new UserCodeResponse();
        String code = null;
        String result = null;
        try {
            if (StringUtils.isBlank(request.getPhone())) {
                code ="phone";
                result ="手机号不能为空";
            } else if (request.getCodeType() == null) {
                code ="codeType";
                result ="验证类型不能为空";
            } else if (!request.getCodeType().equals(1) && !request.getCodeType().equals(2)) {
                code ="codeType";
                result ="验证类型只能为1注册或者2重置";
            }
            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            // 2. 业务验证
            // 根据手机号查询此用户是否存在
            UserInfoPoExample example=new UserInfoPoExample();
            example.createCriteria().andPhoneNumEqualTo(request.getPhone());
            List<UserInfoPo> userInfoPoList = this.userMapper.selectByExample(example);
            UserInfoPo userPo = null;
            if(userInfoPoList!=null && userInfoPoList.size()==1){
                userPo=userInfoPoList.get(0);
            }
            // 如果是注册
            if (request.getCodeType().equals(1)) {
                if (userPo != null) {
                    // 该用户已经注册
                    response.addErrInfo("userPo", "该用户已经注册", null);
                    response.setSuccessCount(0);
                    return response;
                } else {
                    // 获取验证的条件
                    HashMap<String, Object>  hashmap= codeMgrImpl.getVerifCondition();
                    // 判断是否具有发送验证码的权限
                    HashMap<String, Object> conformMap = codeMgrImpl.isConform(hashmap);
                    boolean flag = (boolean) conformMap.get("flag");
                    // 符合发送验证码的要求，发送验证码
                    if (flag) {
                        // 判断此用户最后一次发送的验证码的状态
                        codeMgrImpl.updateLastCodeStatus(hashmap);
                        // 发送验证码
                        String code1=codeMgrImpl.createCodeAndSend(request,Integer.parseInt(codeMgrImpl.getCodeConfigConditional().getCode_valid_time()));
                        response.setCode(code1);
                        if (code1==null) {
                            response.addErrInfo("verifyCode", "发送验证码失败", null);
                            response.setSuccessCount(0);
                            return response;
                        }
                    } else {
                        response.addErrInfo("verifyCode", (String) conformMap.get("errorMsg"), null);
                        response.setSuccessCount(0);
                        return response;
                    }
                }
                response.setResult("注册验证码发送成功");

                // 重置密码
            } else if (request.getCodeType().equals(2)) {
                if (userPo != null) {
                    // 获取验证的条件
                    HashMap<String, Object> hashmap = codeMgrImpl.getVerifCondition();
                    // 判断是否具有发送验证码的权限
                    HashMap<String, Object> conformMap = codeMgrImpl.isConform(hashmap);
                    boolean flag = (boolean) conformMap.get("flag");
                    // 符合发送验证码的要求，发送验证码
                    if (flag) {
                        // 判断此用户最后一次发送的验证码的状态
                        codeMgrImpl.updateLastCodeStatus(hashmap);
                        // 发送验证码
                        String code1=codeMgrImpl.createCodeAndSend(request,Integer.parseInt(codeMgrImpl.getCodeConfigConditional().getCode_valid_time()));
                        response.setCode(code1);
                        if (code1==null) {
                            response.addErrInfo("verifyCode", "发送验证码失败", null);
                            response.setSuccessCount(0);
                            return response;
                        }
                    } else {
                        // 不符合标准，无法发送验证码
                        response.addErrInfo("verifyCode", (String) conformMap.get("errorMsg"), null);
                        response.setSuccessCount(0);
                        return response;
                    }
                } else {
                    // 手机号不存在
                    response.addErrInfo("phone", "手机号不存在，该用户未注册", null);
                    response.setSuccessCount(0);
                    return response;
                }
                response.setResult("重置验证码发送成功");
            }

            return response;
        } catch (Exception e) {
            log.error("重置密码或者注册用户前检验用户时出错,userPhone:" + request.getPhone(),e);
            throw new UserServiceException(e.getMessage());
        }
    }

    @Override
    public UserInfoResponse edit(UserInfoRequest requestVo) throws UserServiceException {
        UserInfoResponse response = new UserInfoResponse();
        String code = null;
        String result = null;
        try{
            //插入参数进行更新
            UserInfoPo userInfoPo = this.userMapper.selectByPrimaryKey(Long.parseLong(requestVo.getUserId()));
            if (userInfoPo == null){
                code ="userId";
                result ="用户ID不存在";
            }
            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            if(userInfoPo!=null){
                if(!StringUtils.isBlank(requestVo.getNickname())){
                    userInfoPo.setNickName(requestVo.getNickname().trim());
                }
                if(requestVo.getSex()!=null){
                    userInfoPo.setSex(requestVo.getSex());
                }
                if(requestVo.getBirthday()!=null){
                    userInfoPo.setBirthday(requestVo.getBirthday());
                }
                if(!StringUtils.isBlank(requestVo.getSignature())){
                    userInfoPo.setSignature(requestVo.getSignature());
                }
                this.userMapper.updateByPrimaryKey(userInfoPo);
            }
            response.setUserId(Integer.parseInt(requestVo.getUserId()));
            response.setResult("更新成功！");
        } catch(Exception e){
            log.error("用户信息更新异常:" + e.getMessage());
            throw new UserServiceException(e.getMessage());
        }
        return response;
    }

    @Override
    public UserAvatarResponse uploadAvatar(UserAvatarRequest request) throws UserServiceException {
        UserAvatarResponse response=new UserAvatarResponse();
        String code = null;
        String result = null;
        try {
            //插入参数进行更新
            UserInfoPo userInfoPo = this.userMapper.selectByPrimaryKey(Long.parseLong(request.getUserId()));
            if (userInfoPo == null){
                code ="userId";
                result ="用户ID不存在";
            }
            if (StringUtils.isNotEmpty(result)){
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }
            FileItem fileImg=request.getFileImg();
            FileCopyUtils.copy(fileImg.getContent(), new File(fileImgLocal + fileImg.getFileName()));
            String pic = fileImgRemote + fileImg.getFileName();
            userInfoPo.setAvatar(pic);
            this.userMapper.updateByPrimaryKey(userInfoPo);
            response.setImgUrl(fileImgRemote + fileImg.getFileName());
            response.setResult("图像上传成功");
            return response;
        } catch (IOException e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    @Override
    public UserDetailResponse getDetail(UserDetailRequest request) throws UserServiceException {
        UserDetailResponse responseVo=new UserDetailResponse();
        String code = null;
        String result = null;
        try {
            //插入参数进行更新
            UserInfoPo userInfoPo = this.userMapper.selectByPrimaryKey(Long.parseLong(request.getUserId()));
            if (userInfoPo == null){
                code ="userId";
                result ="用户ID不存在";
            }
            if (StringUtils.isNotEmpty(result)){
                responseVo.addErrInfo(code, result, null);
                responseVo.setSuccessCount(0);
                return responseVo;
            }
            responseVo.setUserId(Integer.parseInt(request.getUserId()));
            responseVo.setUsername(userInfoPo.getUsername());
            responseVo.setNickname(userInfoPo.getNickName());

            responseVo.setPhone(userInfoPo.getPhoneNum());
            responseVo.setBirthday(userInfoPo.getBirthday());
            responseVo.setSignature(userInfoPo.getSignature());
            responseVo.setAvatar(userInfoPo.getAvatar());
            responseVo.setSex(userInfoPo.getSex());
            responseVo.setStatus(userInfoPo.getStatus());
            responseVo.setCreatedAt(userInfoPo.getCreateTime());

            return responseVo;
        } catch (Exception e) {
            throw new UserServiceException(e.getMessage());
        }
    }
}
