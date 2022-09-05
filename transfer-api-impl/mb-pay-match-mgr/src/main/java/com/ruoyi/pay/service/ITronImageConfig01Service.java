package com.ruoyi.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.pay.domain.TronImageConfig01;

import java.util.List;

/**
 * 图片配置01Service接口
 *
 * @author eason
 * @date 2022-05-17
 */
public interface ITronImageConfig01Service extends IService<TronImageConfig01> {

    /**
     * 查询列表
     */
    List<TronImageConfig01> queryList(TronImageConfig01 tronImageConfig01);
}
