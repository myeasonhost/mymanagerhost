package com.eason.transfer.openapi.zb.api.app;

import com.eason.transfer.openapi.core.sdk.user.exception.UserServiceException;
import com.eason.transfer.openapi.core.sdk.user.model.UserCodeRequest;
import com.eason.transfer.openapi.zb.api.app.dao.entity.UserCodePo;
import com.eason.transfer.openapi.zb.api.app.dao.entity.VerifyCodeLogPo;
import com.eason.transfer.openapi.zb.api.app.dao.mapper.UserCodePoMapper;
import com.eason.transfer.openapi.zb.api.app.dao.mapper.VerifyCodeLogMapper;
import com.eason.transfer.openapi.zb.api.app.model.CodeConfigModel;
import com.eason.transfer.openapi.zb.api.app.service.ICodeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CodeMgrImpl implements ICodeService {
    private static Logger log = LoggerFactory.getLogger(CodeMgrImpl.class);

    @Autowired
    private UserCodePoMapper userCodeMapper;
    @Autowired
    private VerifyCodeLogMapper verifyCodeLogMapper;
    // 缓存
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public CodeConfigModel getCodeConfigConditional() throws UserServiceException {
        CodeConfigModel codeConfigModel=new CodeConfigModel();
        codeConfigModel.setOneDayNum("10");
        codeConfigModel.setLastTime("3");
        codeConfigModel.setInterval_time("1");
        codeConfigModel.setCode_valid_time("10");
        codeConfigModel.setVerFailTime("3");
        codeConfigModel.setVerFialForbidTime("10");
        return codeConfigModel;
    }

    /**
     * getVeriryCondition Description: 获取验证条件(验证码功能)
     */
    public HashMap<String, Object> getVerifCondition(){
        CodeConfigModel codeConfigModel=new CodeConfigModel();
        codeConfigModel.setOneDayNum("10");
        codeConfigModel.setLastTime("3");
        codeConfigModel.setInterval_time("1");
        codeConfigModel.setCode_valid_time("10");
        codeConfigModel.setVerFailTime("3");
        codeConfigModel.setVerFialForbidTime("10");
        // 拼接查询条件
        HashMap<String, Object> Hashmap = new HashMap<String, Object>();
        // 拼接查询条件
        Hashmap.put("oneDayNum",codeConfigModel.getOneDayNum());
        Hashmap.put("totalNum",codeConfigModel.getTotalNum());
        Hashmap.put("lastTime",codeConfigModel.getLastTime());
        Hashmap.put("intervalTime",codeConfigModel.getInterval_time());
        // 多长时间内失败3次则禁止再进行验证
        Hashmap.put("verFailTime",codeConfigModel.getVerFailTime());
        // 如果规定时间内验证失败超过3次，禁止验证多长时间
        Hashmap.put("verFialForbidTime",codeConfigModel.getVerFialForbidTime());
        return Hashmap;
    }

    /**
     * isVerfify Description: 如果用户在5分钟内验证失败了5次，则30分钟内无法再进行验证,判断用户是否可以验证
     */
    public boolean isVerfify(HashMap<String, Object> map) {

        boolean flag = true;
        HashMap<String, Object> codeMap = new HashMap<String, Object>();
        codeMap.put("phone", (String) map.get("phone"));
        codeMap.put("type", (Integer)(map.get("type")));
        codeMap.put("isSuccess", 0);    //验证码是否匹配 0为不匹配
        List<VerifyCodeLogPo> list = verifyCodeLogMapper.selectCountVerFail(codeMap);
        if (list != null && list.size() == 10) {
            long min = (list.get(0).getVerTime().getTime() - list
                    .get(list.size() - 1).getVerTime().getTime()) / 60000;
            long time;
            if (min <= Integer.parseInt((String) map.get("verFailTime"))) {
                time = (new Date().getTime() - list.get(0).getVerTime().getTime()) / 60000;
                if (time <= Integer.parseInt((String) map.get("verFialForbidTime"))) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * isConform Description: 重置密码前检验用户此次操作是否符合规则
     */
    public HashMap<String, Object> isConform(HashMap<String, Object> map) throws Exception {

        boolean flag = true;
        HashMap<String, Object> conformMap=new HashMap<String, Object>();
        try {
            // 如果用户此时处于无法进行验证的30分钟内，则无法发送验证码
            if (!isVerfify(map)) {
                flag = false;
                conformMap.put("flag", flag);
                conformMap.put("errorMsg", "验证码连续错误，请10分钟后再试");
                return conformMap;
            }
            // 验证单日次数
            if (StringUtils.isNumeric((String) map.get("oneDayNum"))) {
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                map.put("daytime", sim.format(new Date()));
                int count = userCodeMapper.getCountForDay(map);
                if (count >= Integer.parseInt((String) map.get("oneDayNum"))) {
                    flag = false;
                    conformMap.put("flag", flag);
                    conformMap.put("errorMsg", "超出单日发送验证码次数限制");
                    return conformMap;
                }
            }
            // 验证总次数
            if (StringUtils.isNumeric((String) map.get("totalNum"))) {
                int count = userCodeMapper.getTotalCount(map);
                if (count >= Integer.parseInt((String) map.get("totalNum"))) {
                    flag = false;
                    conformMap.put("flag", flag);
                    conformMap.put("errorMsg", "超出验证码发送次数");
                    return conformMap;
                }
            }

            // 验证是否30分钟之内发送三次
            if (StringUtils.isNumeric((String) map.get("lastTime"))) {

                // 获取用户最后3此发送验证码的时间
                List<Date> dateList = userCodeMapper.getTimeListTime(map);
                flag = isLastTime(dateList, map);
                if (!flag) {
                    conformMap.put("flag", flag);
                    conformMap.put("errorMsg", "30分钟之内只能发送3次验证码");
                    return conformMap;
                }

            }
            // 验证距离上次发送时间
            if (StringUtils.isNumeric((String) map.get("intervalTime"))) {
                List<Date> dateList = userCodeMapper.getTimeListTime(map);
                Long time = null;
                if (dateList != null && dateList.size() > 0) {
                    Date date = dateList.get(0);
                    time = (new Date().getTime() - date.getTime())
                            / Long.parseLong(((String) map.get("time")));
                    if (time < Long.parseLong((String) map.get("intervalTime"))) {
                        flag = false;
                        conformMap.put("flag", flag);
                        conformMap.put("errorMsg", "距离上次发送必须间隔60秒");
                        return conformMap;
                    }
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        conformMap.put("flag", flag);
        return conformMap;
    }

    /**
     * FunName: isLastTime Description: 判断半个小时以内是否发送过3次验证码
     * @return List<Date> 存储多个时间 HashMap<String, Object> 查询条件
     */
    public boolean isLastTime(List<Date> list, HashMap<String, Object> map) {

        boolean flag = false;
        if (list != null && list.size() >= 3) {
            for (Date date : list) {
                long min = (new Date().getTime() - date.getTime())
                        / Integer.parseInt((String) map.get("time"));
                if (min - Long.parseLong((String) map.get("lastTime")) > 0) {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * FunName: updateLastCodeStatus Description: 修改最后一次发送验证码的状态
     */
    public boolean updateLastCodeStatus(HashMap<String, Object> map) {

        boolean isPass = false;
        try {
            UserCodePo userCode = userCodeMapper.findLastCode(map);
            if (userCode == null) {
                isPass = true;
            } else {
                // 如果此最后一条验证码的状态是为使用（1）=
                // 验证码状态(1为未使用,2为已经验证失效,3为已经验证成功)
                if (userCode.getState().intValue() == 1) {
                    UserCodePo newModel = new UserCodePo();
                    newModel.setId(userCode.getId());
                    newModel.setState(2);
                    newModel.setUpdateTime(new Date());
                    // 修改此验证码的状态为2（失效）
                    isPass = userCodeMapper.updateModelById(newModel) > 0;
                } else {
                    isPass = false;
                }
            }
        } catch (Exception e) {
            log.error("修改用户验证码状态时发送异常", e);
        }
        return isPass;
    }

    /**
     * FunName: createCodeAndSend Description: 生成验证码并且发送且添加到数据库
     */
    public String createCodeAndSend(UserCodeRequest request, int codeValidTime)  {

        boolean flag = false;
        try {
            // 获取24小时数（获取多长时间内的验证码）
            int hour = 24;
            // 根据发送的类型和小时数查询所有的验证码
            UserCodePo model = new UserCodePo();
            model.setType(request.getCodeType().byteValue()); //1为注册,2为重置密码,3修改手机号
            model.setPhone(request.getPhone());
            // 查询最近一段时间内的验证码(按时间从近到远)
            String code = this.stringRedisTemplate.opsForValue().get(request.getPhone());
            if (code == null) {
                // 生成随机数
                code = String.valueOf(Math.round(Math.random() * 8999 + 1000));
                stringRedisTemplate.opsForValue().set( request.getPhone(), code, codeValidTime, TimeUnit.MINUTES);
            }
            //TODO 实现发生验证码方法
//            flag = PushUtil.sendTextCode(code, request.getPhone());
            flag=true;
            if (flag) {
                UserCodePo userCode = new UserCodePo();
                userCode.setType(request.getCodeType().byteValue()); // 验证码类型为重置密码
                userCode.setCode(code);
                userCode.setPhone(request.getPhone());
                userCode.setSendTime(new Date());
                // 验证码状态(1为未使用,2为已经验证失效,3为已经验证成功)
                userCode.setState(1);
                // 将验证码存入数据库
                if(userCodeMapper.insertUserCode(userCode)>0){
                    return code;
                }
            }
        } catch (Exception e) {
            log.error("创建并发送验证码异常:userPhone" + request.getPhone(), e);
        }

        return null;
    }


    /**
     * 验证用户是否进行过验证码验证
     * @param: type 1:注册 2：重置密码 phone 用户手机
     */
    public HashMap<String,Object> verifySendCode(String phone,Integer type, String code_valid_time) {
        boolean flag = false;
        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        // 拼接查询条件
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("type", type);
            map.put("phone", phone);
            // 验证码状态(1为未使用,2为已经验证失效,3为已经验证成功)
            map.put("state", 3);
            // 按时间查询出最后一条已经验证成功的验证数据
            UserCodePo model = userCodeMapper.findLastCode(map);
            if (model != null) {
                flag = true;
                long time = (new Date().getTime() - model.getUpdateTime().getTime()) / 60000;
                long verifyTime = Long.parseLong(code_valid_time);
                if (time > verifyTime) {
                    flag = false;
                    resultMap.put("flag", flag);
                    resultMap.put("errorMsg", "此验证码已经过期");
                }
            } else {
                flag = false;
                resultMap.put("flag", flag);
                resultMap.put("errorMsg", "没有进行过验证码验证");
            }
            resultMap.put("flag", flag);
        } catch (Exception e) {
            log.error("验证用户是否进行过验证码验证时异常", e);
            resultMap.put("flag", flag);
            resultMap.put("errorMsg", "检验是否进行过验证码验证时出错");
        }

        return resultMap;
    }

    /**
     * (1)从数据库获取验证码
     */
    public HashMap<String, Object> getUserCodeForDB(HashMap<String, Object> codeMap) {

        UserCodePo usersCode = userCodeMapper.findLastCode(codeMap);
        HashMap<String, Object> resultMap = null;
        boolean flag = false;
        boolean fg = false;
        if (usersCode != null) {
            resultMap = new HashMap<String, Object>();
            // 存储最后发送的验证码的时间
            codeMap.put("lastCodeTime", usersCode.getSendTime());
            // 判断是否拥有验证的权限
            fg = isVerfify(codeMap);
            if (!fg) {
                resultMap.put("flag", flag);
                resultMap.put("errorMsg", "连续输入错误，请10分钟后再试");
            } else {
                // 判断此验证码是否有效,为false代表有效,为true则验证码过期
                flag = isInSetTime(codeMap, usersCode.getSendTime());
                if (flag) {
                    resultMap.put("flag", false);
                    resultMap.put("errorMsg", "验证码过期");
                } else {
                    resultMap.put("flag", true);
                    resultMap.put("usersCode", usersCode);
                }
            }
        } else {
            // 检查是否有已经验证成功过的验证码，如果验证成功过，10分钟有效
            resultMap = isValid(codeMap);
            if (resultMap == null) {
                return null;
            }
            boolean lean = true;
            boolean rf=(boolean) resultMap.get("flag");
            if (rf) {
                lean = isVerfify(codeMap);
                if (!lean) {
                    if (resultMap.get("usersCode") != null) {
                        resultMap.put("usersCode", null);
                    }
                    resultMap.put("flag", lean);
                    resultMap.put("errorMsg", "连续输入错误，请10分钟后再试");
                }
            }
        }
        return resultMap;
    }

    /**
     * 判断距离最后一次发送验证码的时间是否在规定时间内
     */
    public boolean isInSetTime(HashMap<String, Object> codeMap, Date sendTime) {
        boolean flag = false;
        long time;
        if (sendTime != null) {
            time = (new Date().getTime() - sendTime.getTime()) / 60000;
            if (time > Integer.parseInt((String) codeMap.get("codeVaildTime"))) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 如果用户已经验证成功过，那么此验证码10分钟内有效
     */
    public HashMap<String, Object> isValid(HashMap<String, Object> codeMap) {
        boolean isPass = false;
        long time;
        HashMap<String, Object> resultMap = null;
        // 验证码状态(1为未使用,2为已经验证失效,3为已经验证成功)
        codeMap.put("state", 3);
        UserCodePo usersCode = userCodeMapper.findLastCode(codeMap);
        if (usersCode != null) {
            resultMap = new HashMap<String, Object>();
            time = (new Date().getTime() - usersCode.getSendTime().getTime()) / 60000;
            if (time <= Integer.parseInt((String) codeMap.get("codeVaildTime"))) {
                isPass = true;
            } else {
                isPass = false;
            }
            resultMap.put("flag", isPass);
            resultMap.put("usersCode", usersCode);
        }
        if (null != resultMap) {
            return resultMap;
        } else {
            return null;
        }
    }
}
