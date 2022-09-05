package com.ruoyi.pay.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.lang3.StringUtils;
import com.ruoyi.pay.mapper.MbPayOrderMapper;
import com.ruoyi.pay.domain.MbPayOrder;
import com.ruoyi.pay.service.IMbPayOrderService;

import java.util.List;
import java.util.Map;

/**
 * 充值订单Service业务层处理
 *
 * @author doctor
 * @date 2022-09-05
 */
@Service
public class MbPayOrderServiceImpl extends ServiceImpl<MbPayOrderMapper, MbPayOrder> implements IMbPayOrderService {

    @Override
    public List<MbPayOrder> queryList(MbPayOrder mbPayOrder) {
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(mbPayOrder.getSiteId())){
            lqw.eq(MbPayOrder::getSiteId ,mbPayOrder.getSiteId());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getOrderId())){
            lqw.eq(MbPayOrder::getOrderId ,mbPayOrder.getOrderId());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getUserId())){
            lqw.eq(MbPayOrder::getUserId ,mbPayOrder.getUserId());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getMatchId())){
            lqw.eq(MbPayOrder::getMatchId ,mbPayOrder.getMatchId());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getProductName())){
            lqw.like(MbPayOrder::getProductName ,mbPayOrder.getProductName());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getAmount())){
            lqw.eq(MbPayOrder::getAmount ,mbPayOrder.getAmount());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getCurrency())){
            lqw.eq(MbPayOrder::getCurrency ,mbPayOrder.getCurrency());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getCoinAmount())){
            lqw.eq(MbPayOrder::getCoinAmount ,mbPayOrder.getCoinAmount());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getCoinCode())){
            lqw.eq(MbPayOrder::getCoinCode ,mbPayOrder.getCoinCode());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getStatus())){
            lqw.eq(MbPayOrder::getStatus ,mbPayOrder.getStatus());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getNotifySucceed())){
            lqw.eq(MbPayOrder::getNotifySucceed ,mbPayOrder.getNotifySucceed());
        }
        if (mbPayOrder.getNotifyTimes() != null){
            lqw.eq(MbPayOrder::getNotifyTimes ,mbPayOrder.getNotifyTimes());
        }
        if (mbPayOrder.getLastNotifyTime() != null){
            lqw.eq(MbPayOrder::getLastNotifyTime ,mbPayOrder.getLastNotifyTime());
        }
        if (mbPayOrder.getNextNotifyTime() != null){
            lqw.eq(MbPayOrder::getNextNotifyTime ,mbPayOrder.getNextNotifyTime());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getTimeout())){
            lqw.eq(MbPayOrder::getTimeout ,mbPayOrder.getTimeout());
        }
        if (mbPayOrder.getPayTime() != null){
            lqw.eq(MbPayOrder::getPayTime ,mbPayOrder.getPayTime());
        }
        if (mbPayOrder.getTransferTime() != null){
            lqw.eq(MbPayOrder::getTransferTime ,mbPayOrder.getTransferTime());
        }
        if (mbPayOrder.getApplyTime() != null){
            lqw.eq(MbPayOrder::getApplyTime ,mbPayOrder.getApplyTime());
        }
        if (mbPayOrder.getTimeoutTime() != null){
            lqw.eq(MbPayOrder::getTimeoutTime ,mbPayOrder.getTimeoutTime());
        }
        if (mbPayOrder.getCancelTime() != null){
            lqw.eq(MbPayOrder::getCancelTime ,mbPayOrder.getCancelTime());
        }
        if (mbPayOrder.getFinishTime() != null){
            lqw.eq(MbPayOrder::getFinishTime ,mbPayOrder.getFinishTime());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getNotifyUrl())){
            lqw.eq(MbPayOrder::getNotifyUrl ,mbPayOrder.getNotifyUrl());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getRedirectUrl())){
            lqw.eq(MbPayOrder::getRedirectUrl ,mbPayOrder.getRedirectUrl());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getCashierUrl())){
            lqw.eq(MbPayOrder::getCashierUrl ,mbPayOrder.getCashierUrl());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getQrcodeUrl())){
            lqw.eq(MbPayOrder::getQrcodeUrl ,mbPayOrder.getQrcodeUrl());
        }
        if (StringUtils.isNotBlank(mbPayOrder.getPayimageUrl())){
            lqw.eq(MbPayOrder::getPayimageUrl ,mbPayOrder.getPayimageUrl());
        }
        return this.list(lqw);
    }
}
