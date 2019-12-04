package com.eason.transfer.openapi.user.api.app.dao.mapper;


import com.eason.transfer.openapi.user.api.app.dao.entity.UserCodePo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * UserCodeDAO接口
 *
 * @author eason
 */
public interface UserCodePoMapper {

    /**
     * Description:根据用户手机等参数查询此手机当日发送验证码的次数
     */
    public int getCountForDay(HashMap<String, Object> map);

    /**
     * Description:根据用户手机和类型查询此手机发送验证码的总次数
     */
    public int getTotalCount(HashMap<String, Object> map);

    /**
     * Description:根据用户手机和类型查询最后三次发送验证码的时间
     */
    public List<Date> getTimeListTime(HashMap<String, Object> map);

    /**
     * Description:根据一个mac和类型获取其发送验证码的次数
     */
    public default int getCountForMac(UserCodePo ooUserCodeModel) {
        return 0;
    }

    /**
     * Description:根据手机号获取此号码最后一次验证码
     */
    public UserCodePo findLastCode(HashMap<String, Object> codeMap);
    //------------------请在此添加自定义方法（结束）------------------

    /**
     * Description:根据主键id来获取对象UserCodePo
     */
    public UserCodePo getObjectById(Integer id);

    /**
     * Description:根据Model来获取对象UserCodePo
     */
    public UserCodePo getObjectByModel(UserCodePo obj);

    /**
     * Description:根据Model来获取对象UserCodePo
     */
    public List<UserCodePo> getListByModel(UserCodePo obj);

    /**
     * Description:新增对象UserCodePo
     */
    public int insertUserCode(UserCodePo obj);

    /**
     * Description:根据ID更新对象UserCodePo
     * 只更新不为空的字段
     */
    public int updateModelById(UserCodePo obj);

    /**
     * Description:删除对象
     */
    public int deleteById(Integer id);

    /**
     * FunName:getNewPhoneUpCode
     * Description:获取最新手机号修改验证码信息
     */
    public UserCodePo getNewPhoneUpCode(String phone);

    /**
     * Description:修改手机号当天验证码获取次数
     */
    public int getPhoneChangeCount(String phone);


}
