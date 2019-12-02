package com.eason.transfer.openapi.chess.api;

import com.alibaba.fastjson.JSONObject;
import com.eason.transfer.openapi.chess.aop.TransferStart;
import com.eason.transfer.openapi.chess.dao.entity.*;
import com.eason.transfer.openapi.chess.dao.mapper.TChessGameConfigPoMapper;
import com.eason.transfer.openapi.chess.dao.mapper.TChessGameKindPoMapper;
import com.eason.transfer.openapi.chess.dao.mapper.TChessGamePoMapper;
import com.eason.transfer.openapi.chess.dao.mapper.TChessWalletCorrectionRecordPoMapper;
import com.eason.transfer.openapi.chess.utils.Encrypt;
import com.eason.transfer.openapi.core.sdk.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@RestController
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private TChessGameKindPoMapper chessGameKindPoMapper;
    @Autowired
    private TChessGameConfigPoMapper chessGameConfigPoMapper;
    @Autowired
    private TChessWalletCorrectionRecordPoMapper chessWalletCorrectionRecordPoMapper;
    @Autowired
    private TChessGamePoMapper chessGamePoMapper;


    /**
     * （1）校验登录参数
     * （2）验证用户权限
     * （3）登录第三方接口
     * （4）登录成功，初始化本地用户
     * （5）返回结果
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/user/login")
    @TransferStart
    @Override
    public LoginResponse login(LoginRequest request) throws Exception {
        LoginResponse response=new LoginResponse();
        TChessGameConfigPo configPo;
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getUsername())) {
            code ="username";
            result ="用户名不能为空";
        } else if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        } else if (request.getGameType()==null) {
            code ="gameType";
            result ="游戏类型不能为空";
        }

        if(request.getGameType()!=null){
            TChessGameKindPo tChessGameKindPo=chessGameKindPoMapper.selectByPrimaryKey(request.getGameType());
            if(tChessGameKindPo==null){
                code ="gameType";
                result ="游戏类型不存在gameType="+request.getGameType();
            }
        }
        TChessGameConfigPoExample example=new TChessGameConfigPoExample();
        example.createCriteria()
                .andGameKindEqualTo(request.getLoginType());
        List<TChessGameConfigPo> list=chessGameConfigPoMapper.selectByExample(example);
        if (list==null || list.size()==0){
            code ="TChessGameKindPo";
            result ="游戏类型配置为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        try {
            configPo=list.get(0);
            Long timestamp=System.currentTimeMillis();
            String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
            String orderId=configPo.getAgentId()+date+getUserPrex(configPo,request.getSiteId())+request.getUsername();
            String params=String.format("s=0&account=%s&money=0&orderid=%s&ip=%s&lineCode=%s&KindID=%s",
                    getUserPrex(configPo,request.getSiteId())+request.getUsername(),
                    orderId,
                    request.getIp(),
                    request.getSiteId(),
                    request.getGameType());
            String DESKey=configPo.getDeskey();
            String MD5Key=configPo.getMd5key();
            String param= Encrypt.AESEncrypt(params,DESKey);
            String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
            String url=String.format(configPo.getTransferurl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                    configPo.getAgentId(),
                    timestamp,
                    param,
                    key);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            URI uri = builder.build(true).toUri();

            log.info("登录请求="+uri.toString());
            RestTemplate template = new RestTemplate();
            String body=template.getForObject(uri,String.class);
            JSONObject resultObj= JSONObject.parseObject(body);
            log.info("登录返回结果="+resultObj.toJSONString());
            if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
                throw new Exception(String.format("登录失败=%s",resultObj.toJSONString()));
            }
            response.setResult("登录成功");
            response.setUrl(resultObj.getJSONObject("d").getString("url"));
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 转入上分流程：
     * （1）校验转入参数
     * （2）验证用户权限
     * （3）钱包转入接口
     * （4）第三方上分接口，若失败，进入冲正流程
     * （5）返回结果
     * @param request
     * @return
     */
    @TransferStart
    @Override
    public TransferInResponse transferIn(TransferInRequest request) throws Exception {
        TransferInResponse response=new TransferInResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getUsername())) {
            code ="username";
            result ="用户名不能为空";
        } else if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        } else if (request.getMoney()==null) {
            code ="money";
            result ="上分金额不能为空";
        }

        if(request.getMoney()!=null && request.getMoney().doubleValue()<=0){
            code ="money";
            result ="上分金额必须大于0.0";
        }

        TChessGameConfigPoExample example=new TChessGameConfigPoExample();
        example.createCriteria()
                .andGameKindEqualTo(request.getLoginType());
        List<TChessGameConfigPo> list=chessGameConfigPoMapper.selectByExample(example);
        if (list==null || list.size()==0){
            code ="TChessGameKindPo";
            result ="游戏类型配置为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }

        TChessGameConfigPo configPo=list.get(0);
        Long timestamp=System.currentTimeMillis();
        String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
        String orderId=configPo.getAgentId()+date+request.getUsername();

        TChessWalletCorrectionRecordPo chessWalletCorrectionRecordPo=new TChessWalletCorrectionRecordPo();
        chessWalletCorrectionRecordPo.setRecordId(orderId);
        chessWalletCorrectionRecordPo.setSiteId(request.getSiteId());
        chessWalletCorrectionRecordPo.setAccount(request.getUsername());
        chessWalletCorrectionRecordPo.setGameKindA("主账户");
        chessWalletCorrectionRecordPo.setOptType("IN");
        chessWalletCorrectionRecordPo.setGameKindB(configPo.getGameKind());
        chessWalletCorrectionRecordPo.setOptAmount("+"+request.getMoney());
        chessWalletCorrectionRecordPo.setCreateTime(new Timestamp(timestamp));
        chessWalletCorrectionRecordPo.setStatus(Byte.parseByte("1"));
        try {
            //（3）调用钱包转出接口
//            chessService.updateAmount(request.getUsername(),2,new BigDecimal(request.getMoney()));
            String params=String.format("s=2&account=%s&money=%s&orderid=%s",
                    getUserPrex(configPo,request.getSiteId())+request.getUsername(),
                    request.getMoney(),
                    orderId);
            String DESKey=configPo.getDeskey();
            String MD5Key=configPo.getMd5key();
            String param=Encrypt.AESEncrypt(params,DESKey);
            String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
            String url=String.format(configPo.getTransferurl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                    configPo.getAgentId(),
                    timestamp,
                    param,
                    key);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            URI uri = builder.build(true).toUri();

            log.info("上分请求="+uri.toString());
            RestTemplate template = new RestTemplate();
            String body=template.getForObject(uri,String.class);
            JSONObject resultObj= JSONObject.parseObject(body);
            log.info("上分返回结果="+resultObj.toJSONString());
            if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
                throw new Exception(String.format("上分失败=%s",resultObj.toJSONString()));
            }
            BigDecimal money=resultObj.getJSONObject("d").getBigDecimal("money");
            response.setResult("上分成功，可用余额"+money);
            chessWalletCorrectionRecordPo.setBalance(money);
            chessWalletCorrectionRecordPo.setStatus(Byte.parseByte("2"));
            chessWalletCorrectionRecordPo.setStatusLog(configPo.getGameKind()+"转入成功");
            this.chessWalletCorrectionRecordPoMapper.insertSelective(chessWalletCorrectionRecordPo);
            return response;
        } catch (Exception e) {
            chessWalletCorrectionRecordPo.setStatus(Byte.parseByte("3"));
            chessWalletCorrectionRecordPo.setStatusLog(configPo.getGameKind()+"转入失败，失败原因："+e.getMessage());
            this.chessWalletCorrectionRecordPoMapper.insertSelective(chessWalletCorrectionRecordPo);
            throw new Exception(e.getMessage());
        }
    }

    @TransferStart
    @Override
    public TransferOutResponse transferOut(TransferOutRequest request) throws Exception{
        TransferOutResponse response=new TransferOutResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getUsername())) {
            code ="username";
            result ="用户名不能为空";
        } else if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        } else if (request.getMoney()==null) {
            code ="money";
            result ="下分金额不能为空";
        }

        if(request.getMoney()!=null && request.getMoney().doubleValue()<=0){
            code ="money";
            result ="下分金额必须大于0.0";
        }
        TChessGameConfigPoExample example=new TChessGameConfigPoExample();
        example.createCriteria()
                .andGameKindEqualTo(request.getLoginType());
        List<TChessGameConfigPo> list=chessGameConfigPoMapper.selectByExample(example);
        if (list==null || list.size()==0){
            code ="TChessGameKindPo";
            result ="游戏类型配置为空";
        }
        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        TChessGameConfigPo configPo=list.get(0);
        Long timestamp=System.currentTimeMillis();
        String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
        String orderId=configPo.getAgentId()+date+request.getUsername();

        TChessWalletCorrectionRecordPo chessWalletCorrectionRecordPo=new TChessWalletCorrectionRecordPo();
        chessWalletCorrectionRecordPo.setRecordId(orderId);
        chessWalletCorrectionRecordPo.setSiteId(request.getSiteId());
        chessWalletCorrectionRecordPo.setAccount(request.getUsername());
        chessWalletCorrectionRecordPo.setGameKindA("主账户");
        chessWalletCorrectionRecordPo.setOptType("OUT");
        chessWalletCorrectionRecordPo.setGameKindB(configPo.getGameKind());
        chessWalletCorrectionRecordPo.setOptAmount("-"+request.getMoney());
        chessWalletCorrectionRecordPo.setCreateTime(new Timestamp(timestamp));
        chessWalletCorrectionRecordPo.setStatus(Byte.parseByte("1"));

        try {
            String params=String.format("s=3&account=%s&money=%s&orderid=%s",
                    getUserPrex(configPo,request.getSiteId())+request.getUsername(),
                    request.getMoney(),
                    orderId);
            String DESKey=configPo.getDeskey();
            String MD5Key=configPo.getMd5key();
            String param=Encrypt.AESEncrypt(params,DESKey);
            String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
            String url=String.format(configPo.getTransferurl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                    configPo.getAgentId(),
                    timestamp,
                    param,
                    key);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            URI uri = builder.build(true).toUri();

            log.info("下分请求="+uri.toString());
            RestTemplate template = new RestTemplate();
            String body=template.getForObject(uri,String.class);
            JSONObject resultObj= JSONObject.parseObject(body);
            log.info("下分返回结果="+resultObj.toJSONString());
            if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
                throw new Exception(String.format("下分失败=%s",resultObj.toJSONString()));
            }
            BigDecimal money=resultObj.getJSONObject("d").getBigDecimal("money");
            response.setResult("下分成功，可用余额"+money);
            //（4）调用钱包转出接口，若失败，进入冲正流程
//            chessService.updateAmount(request.getUsername(),1,new BigDecimal(request.getMoney()));
            chessWalletCorrectionRecordPo.setBalance(money);
            chessWalletCorrectionRecordPo.setStatus(Byte.parseByte("2"));
            chessWalletCorrectionRecordPo.setStatusLog(configPo.getGameKind()+"转出成功");
            this.chessWalletCorrectionRecordPoMapper.insertSelective(chessWalletCorrectionRecordPo);
            return response;
        } catch (Exception e) {
            chessWalletCorrectionRecordPo.setStatus(Byte.parseByte("3"));
            chessWalletCorrectionRecordPo.setStatusLog(configPo.getGameKind()+"转出失败，失败原因："+e.getMessage());
            this.chessWalletCorrectionRecordPoMapper.insertSelective(chessWalletCorrectionRecordPo);
            throw new Exception(e.getMessage());
        }
    }

    @TransferStart
    @Override
    public QueryBalanceResponse queryBalance(QueryBalanceRequest request) throws Exception {
        QueryBalanceResponse response=new QueryBalanceResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getUsername())) {
            code ="username";
            result ="用户名不能为空";
        } else if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        }
        TChessGameConfigPoExample example=new TChessGameConfigPoExample();
        example.createCriteria()
                .andGameKindEqualTo(request.getLoginType());
        List<TChessGameConfigPo> list=chessGameConfigPoMapper.selectByExample(example);
        if (list==null || list.size()==0){
            code ="TChessGameKindPo";
            result ="游戏类型配置为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        try {
            TChessGameConfigPo configPo=list.get(0);
            Long timestamp=System.currentTimeMillis();
            String params=String.format("s=1&account=%s",
                    getUserPrex(configPo,request.getSiteId())+request.getUsername());
            String DESKey=configPo.getDeskey();
            String MD5Key=configPo.getMd5key();
            String param=Encrypt.AESEncrypt(params,DESKey);
            String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
            String url=String.format(configPo.getTransferurl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                    configPo.getAgentId(),
                    timestamp,
                    param,
                    key);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            URI uri = builder.build(true).toUri();

            log.info("查询余额请求="+uri.toString());
            RestTemplate template = new RestTemplate();
            String body=template.getForObject(uri,String.class);
            JSONObject resultObj= JSONObject.parseObject(body);
            log.info("查询余额返回结果="+resultObj.toJSONString());
            if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
                throw new Exception(String.format("查询余额失败=%s",resultObj.toJSONString()));
            }
            response.setMoney(resultObj.getJSONObject("d").getDouble("money"));
            response.setResult("查询余额成功");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @TransferStart
    @Override
    public OrderStatusResponse queryOrderStatus(OrderStatusRequest request) throws Exception{
        OrderStatusResponse response=new OrderStatusResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getUsername())) {
            code ="username";
            result ="用户名不能为空";
        } else if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        } else if (StringUtils.isBlank(request.getOrderId())) {
            code ="orderId";
            result ="订单ID不能为空";
        }
        TChessGameConfigPoExample example=new TChessGameConfigPoExample();
        example.createCriteria()
                .andGameKindEqualTo(request.getLoginType());
        List<TChessGameConfigPo> list=chessGameConfigPoMapper.selectByExample(example);
        if (list==null || list.size()==0){
            code ="TChessGameKindPo";
            result ="游戏类型配置为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        try {
            TChessGameConfigPo configPo=list.get(0);
            Long timestamp=System.currentTimeMillis();
            String orderId=request.getOrderId();
            String params=String.format("s=4&account=%s&orderid=%s",
                    getUserPrex(configPo,request.getSiteId())+request.getUsername(),
                    orderId);
            String DESKey=configPo.getDeskey();
            String MD5Key=configPo.getMd5key();
            String param=Encrypt.AESEncrypt(params,DESKey);
            String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
            String url=String.format(configPo.getTransferurl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                    configPo.getAgentId(),
                    timestamp,
                    param,
                    key);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            URI uri = builder.build(true).toUri();

            log.info("查询订单请求="+uri.toString());
            RestTemplate template = new RestTemplate();
            String body=template.getForObject(uri,String.class);
            JSONObject resultObj= JSONObject.parseObject(body);
            log.info("查询订单返回结果="+resultObj.toJSONString());
            if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
                throw new Exception(String.format("查询订单失败=%s",resultObj.toJSONString()));
            }
            response.setStatus(resultObj.getJSONObject("d").getInteger("status"));
            response.setMoney(resultObj.getJSONObject("d").getDouble("money"));
            response.setResult("查询订单成功");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @TransferStart
    @Override
    public PlayerStatusResponse queryPlayerStatus(PlayerStatusRequest request) throws Exception{
        PlayerStatusResponse response=new PlayerStatusResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getUsername())) {
            code ="username";
            result ="用户名不能为空";
        } else if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        }

        TChessGameConfigPoExample example=new TChessGameConfigPoExample();
        example.createCriteria()
                .andGameKindEqualTo(request.getLoginType());
        List<TChessGameConfigPo> list=chessGameConfigPoMapper.selectByExample(example);
        if (list==null || list.size()==0){
            code ="TChessGameKindPo";
            result ="游戏类型配置为空";
        }
        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }
        try {
            TChessGameConfigPo configPo=list.get(0);
            Long timestamp=System.currentTimeMillis();
            String params=String.format("s=5&account=%s",
                    getUserPrex(configPo,request.getSiteId())+request.getUsername());
            String DESKey=configPo.getDeskey();
            String MD5Key=configPo.getMd5key();
            String param=Encrypt.AESEncrypt(params,DESKey);
            String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
            String url=String.format(configPo.getTransferurl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                    configPo.getAgentId(),
                    timestamp,
                    param,
                    key);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            URI uri = builder.build(true).toUri();

            log.info("查询玩家状态请求="+uri.toString());
            RestTemplate template = new RestTemplate();
            String body=template.getForObject(uri,String.class);
            JSONObject resultObj= JSONObject.parseObject(body);
            log.info("查询玩家状态返回结果="+resultObj.toJSONString());
            if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
                throw new Exception(String.format("查询玩家状态失败=%s",resultObj.toJSONString()));
            }
            response.setStatus(resultObj.getJSONObject("d").getInteger("status"));
            response.setResult("玩家状态返回成功");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PullBetResponse getBetRecordList(PullBetRequest request) throws Exception{
        PullBetResponse response=new PullBetResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        } else if (StringUtils.isBlank(request.getStartTime())){
            code ="startTime";
            result ="开始时间不能为空";
        } else if (StringUtils.isBlank(request.getEndTime())){
            code ="endTime";
            result ="结束时间不能为空";
        } else if (request.getPage()==null){
            code ="page";
            result ="分页参数不能为空";
        } else if (request.getPageSize()==null){
            code ="pageSize";
            result ="分页参数不能为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }

        Date startDate= null;
        Date endDate= null;
        try {
            startDate = DateUtils.parseDate(request.getStartTime(),new String[]{"yyyy-MM-dd HH:mm:ss"});
            endDate = DateUtils.parseDate(request.getEndTime(),new String[]{"yyyy-MM-dd HH:mm:ss"});
        } catch (ParseException e) {
            response.addErrInfo("time", "日期只支持（yyyy-MM-dd HH:mm:ss）格式", null);
            response.setSuccessCount(0);
            return response;
        }
        long betweenDays = (endDate.getTime() - startDate.getTime()) / (1000L*3600L*24L);
        if(betweenDays>7){
            response.addErrInfo("time", "日期间隔最大不能超过7天", null);
            response.setSuccessCount(0);
            return response;
        }
        if(betweenDays<0){
            response.addErrInfo("time", "结束时间大于开始时间", null);
            response.setSuccessCount(0);
            return response;
        }
        TChessGamePoExample example=new TChessGamePoExample();
        TChessGamePoExample.Criteria criteria=example.createCriteria();
        criteria.andSiteIdEqualTo(request.getSiteId());
        criteria.andGameendtimeBetween(startDate,endDate);
        if (!StringUtils.isBlank(request.getUsername())) {
            criteria.andAccountEqualTo(request.getUsername());
        }
        long total=this.chessGamePoMapper.countByExample(example);

        example.setStartRow(request.getPage()*request.getPageSize());
        example.setPageSize(request.getPageSize());
        List<BetRecordVo> listVo=this.chessGamePoMapper.selectBetRecord(example);
        response.setList(listVo);
        response.setTotal(total);
        return response;
    }

    @Override
    public WalletListResponse getWalletList(WalletListRequest request) throws Exception{
        WalletListResponse response=new WalletListResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getLoginType())) {
            code ="loginType";
            result ="登录类型不能为空";
        } else if (StringUtils.isBlank(request.getStartTime())){
            code ="startTime";
            result ="开始时间不能为空";
        } else if (StringUtils.isBlank(request.getEndTime())){
            code ="endTime";
            result ="结束时间不能为空";
        } else if (request.getPage()==null){
            code ="page";
            result ="分页参数不能为空";
        } else if (request.getPageSize()==null){
            code ="pageSize";
            result ="分页参数不能为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }

        Date startDate= null;
        Date endDate= null;
        try {
            startDate = DateUtils.parseDate(request.getStartTime(),new String[]{"yyyy-MM-dd HH:mm:ss"});
            endDate = DateUtils.parseDate(request.getEndTime(),new String[]{"yyyy-MM-dd HH:mm:ss"});
        } catch (ParseException e) {
            response.addErrInfo("time", "日期只支持（yyyy-MM-dd HH:mm:ss）格式", null);
            response.setSuccessCount(0);
            return response;
        }
        long betweenDays = (endDate.getTime() - startDate.getTime()) / (1000L*3600L*24L);
        if(betweenDays>7){
            response.addErrInfo("time", "日期间隔最大不能超过7天", null);
            response.setSuccessCount(0);
            return response;
        }
        if(betweenDays<0){
            response.addErrInfo("time", "结束时间大于开始时间", null);
            response.setSuccessCount(0);
            return response;
        }
        TChessWalletCorrectionRecordPoExample example=new TChessWalletCorrectionRecordPoExample();
        TChessWalletCorrectionRecordPoExample.Criteria criteria=example.createCriteria();
        criteria.andSiteIdEqualTo(request.getSiteId());
        criteria.andCreateTimeBetween(startDate,endDate);
        if (!StringUtils.isBlank(request.getUsername())) {
            criteria.andAccountEqualTo(request.getUsername());
        }
        long total=this.chessWalletCorrectionRecordPoMapper.countByExample(example);

        example.setStartRow(request.getPage()*request.getPageSize());
        example.setPageSize(request.getPageSize());
        List<TChessWalletCorrectionRecordPo> listPo=this.chessWalletCorrectionRecordPoMapper.selectByExample(example);
        List<WalletListRecordVo> listVo=new ArrayList<>();
        for (TChessWalletCorrectionRecordPo po:listPo){
            WalletListRecordVo vo=new WalletListRecordVo();
            BeanUtils.copyProperties(po,vo);
            vo.setCreateTime(DateFormatUtils.format(po.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            listVo.add(vo);
        }
        response.setList(listVo);
        response.setTotal(total);
        return response;
    }

    private String getUserPrex(TChessGameConfigPo configPo, String siteId){
        Map<String,String> map=new HashMap<>();
        String[] ary=configPo.getSiteId().split(","); //_1020,_1040,_1070,_1080
        for (String s:ary){ //_1020
            String[] i=s.split("_");
            map.put(i[1],i[0]);
        }
        return map.get(siteId);
    }
}
