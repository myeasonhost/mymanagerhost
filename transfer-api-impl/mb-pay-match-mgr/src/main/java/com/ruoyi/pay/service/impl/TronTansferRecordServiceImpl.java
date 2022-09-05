package com.ruoyi.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.pay.domain.TronTansferRecord;
import com.ruoyi.pay.mapper.TronTansferRecordMapper;
import com.ruoyi.pay.service.ITronTansferRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 转账记录Service业务层处理
 *
 * @author eason
 * @date 2022-05-05
 */
@Service
public class TronTansferRecordServiceImpl extends ServiceImpl<TronTansferRecordMapper, TronTansferRecord> implements ITronTansferRecordService {

    @Override
    public List<TronTansferRecord> queryList(TronTansferRecord tronTansferRecord) {
        LambdaQueryWrapper<TronTansferRecord> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(tronTansferRecord.getAgencyId())){
            lqw.eq(TronTansferRecord::getAgencyId ,tronTansferRecord.getAgencyId());
        }
        if (StringUtils.isNotBlank(tronTansferRecord.getFromAddress())){
            lqw.eq(TronTansferRecord::getFromAddress ,tronTansferRecord.getFromAddress());
        }
        if (StringUtils.isNotBlank(tronTansferRecord.getSalemanId())){
            lqw.eq(TronTansferRecord::getSalemanId ,tronTansferRecord.getSalemanId());
        }
        if (StringUtils.isNotBlank(tronTansferRecord.getToAddress())){
            lqw.eq(TronTansferRecord::getToAddress ,tronTansferRecord.getToAddress());
        }
        if (StringUtils.isNotBlank(tronTansferRecord.getType())){
            lqw.eq(TronTansferRecord::getType ,tronTansferRecord.getType());
        }
        if (StringUtils.isNotBlank(tronTansferRecord.getStatus())){
            lqw.eq(TronTansferRecord::getStatus ,tronTansferRecord.getStatus());
        }
        lqw.orderByDesc(TronTansferRecord::getCreateTime);

        return this.list(lqw);
    }
}
