package com.ruoyi.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.pay.domain.OrgAccountInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商户信息Service接口
 *
 * @author doctor
 * @date 2022-07-20
 */
public interface IOrgAccountInfoService extends IService<OrgAccountInfo> {

    /**
     * 查询列表
     */
    List<OrgAccountInfo> queryList(OrgAccountInfo orgAccountInfo);

    /**
     * 登录白名单和谷歌验证码验证
     */
    R whiteIpAndGoogleCodeLogin(LoginBody loginBody, HttpServletRequest request);
}
