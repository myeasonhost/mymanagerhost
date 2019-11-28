package com.eason.transfer.openapi.chess.api;

import com.alibaba.fastjson.JSONObject;
import com.eason.transfer.openapi.chess.aop.TransferStart;
import com.eason.transfer.openapi.chess.dao.ChessGameConfigDao;
import com.eason.transfer.openapi.chess.dao.ChessGameKindDao;
import com.eason.transfer.openapi.chess.dao.ChessUserDao;
import com.eason.transfer.openapi.chess.po.TChessGameConfigPo;
import com.eason.transfer.openapi.chess.po.TChessGameKindPo;
import com.eason.transfer.openapi.chess.po.TLcChessUserPo;
import com.eason.transfer.openapi.chess.utils.Encrypt;
import com.eason.transfer.openapi.core.sdk.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private ChessGameConfigDao chessGameConfigDao;
    @Autowired
    private ChessGameKindDao chessGameKindDao;
    @Autowired
    private ChessUserDao chessUserDao;
    @Autowired
    private RestTemplate restTemplate;


    @TransferStart
    @Override
    public LoginResponse login(LoginRequest request) throws Exception {
        LoginResponse response=new LoginResponse();
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
            TChessGameKindPo tChessGameKindPo=chessGameKindDao.findByKingId(request.getGameType());
            if(tChessGameKindPo==null){
                code ="gameType";
                result ="游戏类型不存在gameType="+request.getGameType();
            }
        }
        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }

        TChessGameConfigPo configPo=chessGameConfigDao.getTChessGameConfigPoByAndGameKind(request.getLoginType());
        Long timestamp=System.currentTimeMillis();
        String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
        String orderId=configPo.getAgentId()+date+request.getUsername();
        String params=String.format("s=0&account=%s&money=0&orderid=%s&ip=%s&lineCode=%s&KindID=%s",
                request.getUsername(),
                orderId,
                request.getIp(),
                request.getSiteId(),
                request.getGameType());
        String DESKey=configPo.getDesKey();
        String MD5Key=configPo.getMd5Key();
        String param= Encrypt.AESEncrypt(params,DESKey);
        String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
        String url=String.format(configPo.getTransferUrl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
                configPo.getAgentId(),
                timestamp,
                param,
                key);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        URI uri = builder.build(true).toUri();

        log.info("登录请求={}",uri.toString());
        String body=restTemplate.getForObject(uri,String.class);
        JSONObject resultObj=JSONObject.parseObject(body);
        log.info("登录返回结果={}",resultObj.toJSONString());
        if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
            throw new Exception(String.format("登录出错，错误信息=%s",resultObj.toJSONString()));
        }
        TLcChessUserPo userPo=new TLcChessUserPo();
        userPo.setAccount(request.getUsername());
        userPo.setChessAccount(getUserPrex(configPo,request.getSiteId())+request.getUsername());
        userPo.setLoginIp(request.getIp());
        userPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userPo.setStatus(Byte.parseByte("1"));
        chessUserDao.save(userPo);
        response.setResult("登录成功");
        response.setUrl(resultObj.getJSONObject("d").getString("url"));
        return response;
    }

    @TransferStart
    @Override
    public TransferInResponse transferIn(TransferInRequest request) {
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

        if(request.getMoney()!=null && request.getMoney().doubleValue()>0){
            code ="money";
            result ="上分金额必须大于0.0";
        }
        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }

//        TChessGameConfigPo configPo=chessGameConfigDao.getTChessGameConfigPoByAndGameKind(request.getLoginType());
//        Long timestamp=System.currentTimeMillis();
//        String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
//        String orderId=configPo.getAgentId()+date+request.getUsername();
//        String params=String.format("s=0&account=%s&money=0&orderid=%s&ip=%s&lineCode=%s&KindID=%s",
//                request.getUsername(),
//                orderId,
//                request.getIp(),
//                request.getSiteId(),
//                request.getGameType());
//        String DESKey=configPo.getDesKey();
//        String MD5Key=configPo.getMd5Key();
//        String param=Encrypt.AESEncrypt(params,DESKey);
//        String key=Encrypt.MD5(configPo.getAgentId()+timestamp+ MD5Key);
//        String url=String.format(configPo.getTransferUrl()+"?agent=%s&timestamp=%s&param=%s&key=%s",
//                configPo.getAgentId(),
//                timestamp,
//                param,
//                key);
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
//        URI uri = builder.build(true).toUri();
//
//        log.info("登录请求={}",uri.toString());
//        String body=restTemplate.getForObject(uri,String.class);
//        JSONObject resultObj=JSONObject.parseObject(body);
//        log.info("登录返回结果={}",resultObj.toJSONString());
//        if(resultObj.getJSONObject("d")!=null && !"0".equals(resultObj.getJSONObject("d").getString("code"))){ //请求成功
//            throw new Exception(String.format("登录出错，错误信息=%s",resultObj.toJSONString()));
//        }
//        response.setResult("登录成功");
//        response.setUrl(resultObj.getJSONObject("d").getString("url"));
        return response;
    }

    @TransferStart
    @Override
    public TransferOutResponse transferOut(TransferOutRequest request) {
        System.out.println(request);
        TransferOutResponse response=new TransferOutResponse();
        response.setResult("demo返回结果="+request.toString());
        return response;
    }

    @TransferStart
    @Override
    public OrderStatusResponse queryOrderStatus(OrderStatusRequest request) {
        System.out.println(request);
        OrderStatusResponse response=new OrderStatusResponse();
        response.setResult("demo返回结果="+request.toString());
        return response;
    }

    private String getUserPrex(TChessGameConfigPo configPo,String siteId){
        Map<String,String> map=new HashMap<>();
        String[] ary=configPo.getSiteId().split(","); //_1020,_1040,_1070,_1080
        for (String s:ary){ //_1020
            String[] i=s.split("_");
            map.put(i[1],i[0]);
        }
        return map.get(siteId);
    }

    @Override
    public PlayerStatusResponse queryPlayerStatus(PlayerStatusRequest request) {
        return null;
    }

    @Override
    public PullBetResponse getBetRecordList(PullBetRequest request) {
        return null;
    }

    @Override
    public WalletListResponse getWalletList(WalletListRequest request) {
        return null;
    }
}
