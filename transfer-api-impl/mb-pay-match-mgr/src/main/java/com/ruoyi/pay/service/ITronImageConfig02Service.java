package com.ruoyi.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.pay.domain.TronImageConfig02;

import java.util.List;

/**
 * 图片配置02Service接口
 *
 * @author eason
 * @date 2022-05-17
 */
public interface ITronImageConfig02Service extends IService<TronImageConfig02> {

    /**
     * 查询列表
     */
    List<TronImageConfig02> queryList(TronImageConfig02 tronImageConfig02);
}
