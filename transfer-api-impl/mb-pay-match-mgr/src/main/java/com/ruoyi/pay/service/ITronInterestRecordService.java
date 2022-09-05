package com.ruoyi.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.pay.domain.TronInterestRecord;

import java.util.List;

/**
 * 利息Service接口
 *
 * @author eason
 * @date 2022-05-03
 */
public interface ITronInterestRecordService extends IService<TronInterestRecord> {

    /**
     * 查询列表
     */
    List<TronInterestRecord> queryList(TronInterestRecord tronInterestRecord);
}
