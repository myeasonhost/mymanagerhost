package com.eason.transfer.openapi.chess.api;

import com.eason.transfer.openapi.chess.aop.TransferStart;
import com.eason.transfer.openapi.chess.dao.entity.TChessGameConfigPo;
import com.eason.transfer.openapi.chess.dao.entity.TChessGameConfigPoExample;
import com.eason.transfer.openapi.chess.dao.entity.TChessUserPo;
import com.eason.transfer.openapi.chess.dao.entity.TChessUserPoExample;
import com.eason.transfer.openapi.chess.dao.mapper.TChessGameConfigPoMapper;
import com.eason.transfer.openapi.chess.dao.mapper.TChessUserPoMapper;
import com.eason.transfer.openapi.core.common.request.Request;
import com.eason.transfer.openapi.core.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author eason
 */
@Aspect
@Component
@Slf4j
public class EkernalServiceImpl{

    @Autowired
    private TChessUserPoMapper chessUserPoMapper;
    @Autowired
    private TChessGameConfigPoMapper chessGameConfigPoMapper;

    @Around("@annotation(transferStart)")
    public Object transferStart(ProceedingJoinPoint pjd, TransferStart transferStart) throws Throwable {

        Object result;
        Request request;
        String loginType = null;
        String siteId = null;
        String username = null;

        Object[] args = pjd.getArgs();
        Class<?>[] paramsCls = new Class<?>[args.length];
        for (int i = 0; i < args.length; ++i) {
            paramsCls[i] = args[i].getClass();
        }
        try {
            if(!(args[0] instanceof Request)){
                throw new RuntimeException("参数类型第一位为Request类型");
            }else{
                request=(Request)args[0];
            }
            Method method1=args[0].getClass().getDeclaredMethod("getLoginType");
            if (method1!=null){
                loginType=(String) method1.invoke(args[0]);
            }
            Method method2=args[0].getClass().getDeclaredMethod("getSiteId");
            if (method2!=null){
                siteId=(String) method2.invoke(args[0]);
            }
            Method method3=args[0].getClass().getDeclaredMethod("getUsername");
            if (method3!=null){
                username=(String) method3.invoke(args[0]);
            }

            if (StringUtils.isEmpty(loginType)){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","loginType不能为空",null);
                return response;
            }
            TChessGameConfigPoExample example=new TChessGameConfigPoExample();
            example.createCriteria()
                    .andGameKindEqualTo(loginType);
            List<TChessGameConfigPo> list=chessGameConfigPoMapper.selectByExample(example);
            if (list==null || list.size()==0){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","loginType配置不存在="+loginType,null);
                return response;
            }
            TChessGameConfigPo configPo=list.get(0);

            if (configPo!=null && configPo.getStatus().byteValue()==3){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","该代理已经被废弃，请联系商务",null);
                return response;
            }
            if (configPo!=null && configPo.getStatus().byteValue()==2){
                Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                Response response=(Response) method.getReturnType().newInstance();
                response.addErrInfo("transferStart","正在维护",null);
                return response;
            }
            if (configPo!=null && configPo.getStatus().byteValue()==1){
                Map<String,String> map=new HashMap<>();
                String[] ary=configPo.getSiteId().split(","); 
                for (String s:ary){ //_1020
                    String[] i=s.split("_");
                    map.put(i[1],configPo.getAgentId()+"_"+i[0]);
                }
                if(StringUtils.isEmpty(siteId)){
                    Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                    Response response=(Response) method.getReturnType().newInstance();
                    response.addErrInfo("transferStart","siteId不能为空",null);
                    return response;
                }
                if(!StringUtils.isEmpty(siteId) && !map.containsKey(siteId)){
                    Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                    Response response=(Response) method.getReturnType().newInstance();
                    response.addErrInfo("transferStart","siteId配置不存在="+siteId,null);
                    return response;
                }
            }
            //username=eason 查询单个用户，username=null 查询所有用户
            if(!StringUtils.isEmpty(username)){
//                int flag=userDao.findRecordByAccount(username);
//                if(flag==0){
//                    Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
//                    Response response=(Response) method.getReturnType().newInstance();
//                    response.addErrInfo("transferStart","主平台账户不存在该用户",null);
//                    return response;
//                }
                TChessUserPo userPo=null;
                TChessUserPoExample example2=new TChessUserPoExample();
                example2.createCriteria()
                        .andSiteIdEqualTo(siteId)
                        .andAccountEqualTo(username);
                List<TChessUserPo> list1=chessUserPoMapper.selectByExample(example2);
                if(list1!=null && list1.size()==1){
                    userPo=list1.get(0);
                    if(userPo.getStatus()==2){
                        Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
                        Response response=(Response) method.getReturnType().newInstance();
                        response.addErrInfo("transferStart","当前用户禁止登陆，请联系客服",null);
                        return response;
                    }
                }
                result = pjd.proceed(new Object[]{args[0]});
                if(((Response)result).getSuccessCount()==1){
                    if(userPo==null){
                        userPo=new TChessUserPo();
                        userPo.setAccount(username);
                        userPo.setChessAccount(getUserPrex(configPo,siteId)+username);
                        userPo.setLoginIp(request.getIp());
                        userPo.setSiteId(siteId);
                        userPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        userPo.setStatus(Byte.parseByte("1"));
                        chessUserPoMapper.insert(userPo);
                    }else{
                        userPo.setLoginIp(request.getIp());
                        userPo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        chessUserPoMapper.updateByPrimaryKey(userPo);
                    }
                }
            }else{
                result = pjd.proceed(new Object[]{args[0]});
            }
        } catch (Throwable e) {
            Method method = pjd.getTarget().getClass().getMethod(pjd.getSignature().getName(), paramsCls);
            Response response=(Response) method.getReturnType().newInstance();
            response.addErrInfo("transferStart",e.getMessage().split("=")[0],null);
            e.printStackTrace();
            return response;
        }
        return result;
    }

    private String getUserPrex(TChessGameConfigPo configPo,String siteId){
        Map<String,String> map=new HashMap<>();
        String[] ary=configPo.getSiteId().split(","); 
        for (String s:ary){ //_1020
            String[] i=s.split("_");
            map.put(i[1],i[0]);
        }
        return map.get(siteId);
    }

}
