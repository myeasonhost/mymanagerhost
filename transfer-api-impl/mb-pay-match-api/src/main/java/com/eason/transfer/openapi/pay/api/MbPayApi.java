package com.eason.transfer.openapi.pay.api;

import cn.hutool.core.date.DateBetween;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.pay.domain.MbPayOrder;
import com.eason.transfer.openapi.pay.mapper.MbPayOrderMapper;
import com.eason.transfer.openapi.pay.message.MessageProducer;
import com.eason.transfer.openapi.pay.model.*;
import com.eason.transfer.openapi.pay.service.IMbPayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MbPayApi extends ServiceImpl<MbPayOrderMapper, MbPayOrder> implements IMbPayOrderService {

    @Autowired
    private MessageProducer messageProducer;

    /**
     * （1）代付下单接口-status=0 订单创建
     */
    public OpenPayWebResponse openPayWeb(OpenPayWebRequest request) {
        OpenPayWebResponse response = new OpenPayWebResponse();
        String code = "pay";
        String result = null;
        if (StringUtils.isBlank(request.getUserId())) {
            code = "userId";
            result = "用户ID不能为空";
        }
        if (StringUtils.isBlank(request.getOrderId())) {
            code = "orderId";
            result = "商户订单ID不能为空";
        }
        if (StringUtils.isBlank(request.getProductName())) {
            code = "productName";
            result = "产品名不能为空";
        }
        if (StringUtils.isNotBlank(result)) {
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(MbPayOrder::getSiteId, request.getAppKey());
        lqw.eq(MbPayOrder::getOrderId, request.getOrderId());
        MbPayOrder mbPayOrder = this.getOne(lqw);
        if (mbPayOrder == null) {
            mbPayOrder = new MbPayOrder();

            mbPayOrder.setSiteId(request.getAppKey());
            mbPayOrder.setUserId(request.getUserId());
            mbPayOrder.setOrderId(request.getOrderId());
            mbPayOrder.setProductName(request.getProductName());
            mbPayOrder.setStatus("0");
            mbPayOrder.setTimeout("15");
            mbPayOrder.setCreateTime(new Date(System.currentTimeMillis()));
            this.saveOrUpdate(mbPayOrder);
        }
        BeanUtils.copyProperties(mbPayOrder, response);
        response.setResult("支付下单");
        return response;
    }


    /**
     * （2）代付状态查询接口
     */
    public OpenPayWebResponse queryStatus(QueryStatusRequest request) {
        OpenPayWebResponse response = new OpenPayWebResponse();
        String code = "pay";
        String result = null;
        if (StringUtils.isBlank(request.getOrderId())) {
            code = "orderId";
            result = "商户订单ID不能为空";
        }
        if (StringUtils.isNotBlank(result)) {
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(MbPayOrder::getSiteId, request.getAppKey());
        lqw.eq(MbPayOrder::getOrderId, request.getOrderId());
        MbPayOrder mbPayOrder = this.getOne(lqw);
        if (mbPayOrder == null) {
            response.addErrInfo(code, "商户的订单号不存在", null);
            response.setSuccessCount(0);
            return response;
        }
        BeanUtils.copyProperties(mbPayOrder, response);
        return response;
    }

    /**
     * （3）获取充值信息接口
     */
    public ChargeInfoListResponse getRechargeInfoList(ChargeInfoListRequest request) {
        ChargeInfoListResponse response = new ChargeInfoListResponse();
        String code = "pay";
        String result = null;
        if (StringUtils.isBlank(request.getOrderId())) {
            code = "orderId";
            result = "商户订单ID不能为空";
        }
        if (StringUtils.isNotBlank(result)) {
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(MbPayOrder::getSiteId, request.getAppKey());
        lqw.eq(MbPayOrder::getOrderId, request.getOrderId());
        MbPayOrder mbPayOrder = this.getOne(lqw);
        if (mbPayOrder == null) {
            response.addErrInfo(code, "商户的订单号不存在", null);
            response.setSuccessCount(0);
            return response;
        }
        BeanUtils.copyProperties(mbPayOrder, response);
        //TODO 获取银行卡充值提现记录
        List<ChargeBankModel> list = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            ChargeBankModel bankModel = new ChargeBankModel();
            bankModel.setChargeMatchId(i);
            bankModel.setBankId(i);
            bankModel.setBankType("深圳银行" + i);
            bankModel.setBankName("深圳福田分行" + i);
            bankModel.setBankCard("6225888888888888" + i);
            bankModel.setAmount(String.valueOf(100 + i * 100));
            bankModel.setUserName("银行卡0" + i);
            list.add(bankModel);
        }
        response.setList(list);
        return response;
    }

    /**
     * （4）代付支付接口-status=1 支付中
     */
    public ChargePayResponse payRecharge(ChargePayRequest request) {
        ChargePayResponse response = new ChargePayResponse();
        String code = "pay";
        String result = null;
        if (StringUtils.isBlank(request.getOrderId())) {
            code = "orderId";
            result = "商户订单ID不能为空";
        }
        if (StringUtils.isBlank(request.getChargeMatchId())) {
            code = "chargeMatchId";
            result = "订单充值ID不能为空";
        }
        if (StringUtils.isNotBlank(result)) {
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(MbPayOrder::getSiteId, request.getAppKey());
        lqw.eq(MbPayOrder::getOrderId, request.getOrderId());
        MbPayOrder mbPayOrder = this.getOne(lqw);
        if (mbPayOrder == null) {
            response.addErrInfo(code, "商户的订单号不存在", null);
            response.setSuccessCount(0);
            return response;
        }
        //TODO 获取银行卡充值提现记录
        ChargeBankModel bankModel = new ChargeBankModel();
        int i = 1;
        bankModel.setChargeMatchId(i);
        bankModel.setBankId(i);
        bankModel.setBankType("深圳银行" + i);
        bankModel.setBankName("深圳福田分行" + i);
        bankModel.setBankCard("6225888888888888" + i);
        bankModel.setAmount(String.valueOf(100 + i * 100));
        bankModel.setUserName("银行卡0" + 1);
        BeanUtils.copyProperties(bankModel, response);
        //更新订单状态
        mbPayOrder.setStatus("1"); //0=下单成功，1=支付中,2=玩家确认支付，3=玩家支付取消，4=支付超时，5=支付审核，6=支付成功
        Date date = DateTime.now();
        mbPayOrder.setPayTime(date);
        mbPayOrder.setTimeoutTime(DateUtil.offsetMinute(date,15));//15分钟超时
        this.saveOrUpdate(mbPayOrder);

        BeanUtils.copyProperties(mbPayOrder, response);
        response.setResult("支付中");
        //发送超时消息通知
        messageProducer.payTimeOutput(mbPayOrder, 60 * 15); //15分钟超时
        return response;
    }


    /**
     * （5）代付转账确认接口-status=2 玩家确认支付
     */
    public ChargeFinishResponse finishRecharge(ChargeFinishRequest request) {

        ChargeFinishResponse response = new ChargeFinishResponse();
        String code = "pay";
        String result = null;
        if (StringUtils.isBlank(request.getOrderId())) {
            code = "orderId";
            result = "商户订单ID不能为空";
        }
        if (StringUtils.isBlank(request.getChargeMatchId())) {
            code = "chargeMatchId";
            result = "订单充值ID不能为空";
        }
        if (StringUtils.isNotBlank(result)) {
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(MbPayOrder::getSiteId, request.getAppKey());
        lqw.eq(MbPayOrder::getOrderId, request.getOrderId());
        MbPayOrder mbPayOrder = this.getOne(lqw);
        if (mbPayOrder == null) {
            response.addErrInfo(code, "商户的订单号不存在", null);
            response.setSuccessCount(0);
            return response;
        }
        //更新订单状态
        mbPayOrder.setStatus("2"); //0=下单成功，1=支付中,2=玩家确认支付，3=玩家支付取消，4=支付超时，5=支付审核，6=支付成功
        mbPayOrder.setTransferTime(new Date(System.currentTimeMillis()));
        mbPayOrder.setAmount("200");
        mbPayOrder.setCurrency("1");
        mbPayOrder.setCoinAmount("200*1");
        mbPayOrder.setCoinCode("RMB");
        this.saveOrUpdate(mbPayOrder);

        BeanUtils.copyProperties(mbPayOrder, response);
        response.setResult("玩家确认支付，请上传凭证");
        return response;
    }

    /**
     * （6）代付转账取消接口-status=3 支付取消
     */
    public ChargeCancelResponse cancelRecharge(ChargeCancelRequest request) {
        ChargeCancelResponse response = new ChargeCancelResponse();
        String code = "pay";
        String result = null;
        if (StringUtils.isBlank(request.getOrderId())) {
            code = "orderId";
            result = "商户订单ID不能为空";
        }
        if (StringUtils.isNotBlank(result)) {
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(MbPayOrder::getSiteId, request.getAppKey());
        lqw.eq(MbPayOrder::getOrderId, request.getOrderId());
        MbPayOrder mbPayOrder = this.getOne(lqw);
        if (mbPayOrder == null) {
            response.addErrInfo(code, "商户的订单号不存在", null);
            response.setSuccessCount(0);
            return response;
        }
        BeanUtils.copyProperties(mbPayOrder, response);
        //更新订单状态
        mbPayOrder.setStatus("3"); //0=下单成功，1=支付中,2=玩家确认支付，3=玩家支付取消，4=支付超时，5=支付审核，6=支付成功
        mbPayOrder.setCancelTime(new Date(System.currentTimeMillis()));
        this.saveOrUpdate(mbPayOrder);
        response.setResult("玩家取消支付");
        return response;
    }

    /**
     * （7）上传支付凭证接口-status=5 支付审核
     */
    public UploadPayImageResponse uploadPayImage(UploadPayImageRequest request) throws IOException {
        UploadPayImageResponse response = new UploadPayImageResponse();
        String code = "pay";
        String result = null;
        if (StringUtils.isBlank(request.getOrderId())) {
            code = "orderId";
            result = "商户订单ID不能为空";
        }
        if (request.getPayImage().getContent().length == 0) {
            code = "payImage";
            result = "请上传支付凭证";
        }
        if (StringUtils.isNotBlank(result)) {
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        LambdaQueryWrapper<MbPayOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(MbPayOrder::getSiteId, request.getAppKey());
        lqw.eq(MbPayOrder::getOrderId, request.getOrderId());
        MbPayOrder mbPayOrder = this.getOne(lqw);
        if (mbPayOrder == null) {
            response.addErrInfo(code, "商户的订单号不存在", null);
            response.setSuccessCount(0);
            return response;
        }
        //文件上传到目标路径
        FileItem fileItem = request.getPayImage();
        String dir = this.getClass().getResource("/images").getPath();
        File file = new File(dir, "/" + fileItem.getFileName() + "." + fileItem.getMimeType());
        FileCopyUtils.copy(fileItem.getContent(), file);
        log.info("文件上传成功：path={}", file.getPath());
        mbPayOrder.setPayimageUrl(file.getPath());
        mbPayOrder.setRemark(request.getRemark());
        BeanUtils.copyProperties(mbPayOrder, response);
        //更新订单状态
        mbPayOrder.setStatus("5"); //0=下单成功，1=支付中,2=玩家确认支付，3=玩家支付取消，4=支付超时，5=支付审核，6=支付成功
        mbPayOrder.setApplyTime(new Date(System.currentTimeMillis()));
        this.saveOrUpdate(mbPayOrder);
        response.setResult("支付凭证上传成功，请等待审核");
        return response;
    }
}
