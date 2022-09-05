package com.ruoyi.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.pay.domain.TronBillRecord;

import java.util.List;

/**
 * 结算记录Service接口
 *
 * @author eason
 * @date 2022-05-06
 */
public interface ITronBillRecordService extends IService<TronBillRecord> {

    /**
     * 查询列表
     */
    List<TronBillRecord> queryList(TronBillRecord tronBillRecord);

    /**
     * 查询统计
     */
    Integer queryCount(TronBillRecord tronBillRecord);

}
