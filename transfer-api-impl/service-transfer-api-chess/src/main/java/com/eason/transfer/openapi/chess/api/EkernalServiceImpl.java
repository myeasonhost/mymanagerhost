package com.eason.transfer.openapi.chess.api;

import com.eason.transfer.openapi.chess.aop.LoadConfig;
import com.eason.transfer.openapi.chess.aop.TransferStart;
import com.eason.transfer.openapi.chess.api.service.IPullService;
import com.eason.transfer.openapi.chess.dao.entity.TChessGameConfigPo;
import com.eason.transfer.openapi.chess.dao.entity.TChessGameConfigPoExample;
import com.eason.transfer.openapi.chess.dao.entity.TChessUserPo;
import com.eason.transfer.openapi.chess.dao.entity.TChessUserPoExample;
import com.eason.transfer.openapi.chess.dao.mapper.TChessGameConfigPoMapper;
import com.eason.transfer.openapi.chess.dao.mapper.TChessUserPoMapper;
import com.eason.transfer.openapi.core.common.request.Request;
import com.eason.transfer.openapi.core.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author eason
 */
@Aspect
@Component
@Slf4j
public class EkernalServiceImpl implements ApplicationContextAware {

    @Autowired
    private TChessUserPoMapper chessUserPoMapper;
    @Autowired
    private TChessGameConfigPoMapper chessGameConfigPoMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

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

    @Around("@annotation(loadConfig)")
    public <K extends IPullService> Object LoadConfig(ProceedingJoinPoint pjd, LoadConfig loadConfig) throws Throwable {
        TChessGameConfigPoExample example = new TChessGameConfigPoExample();
        example.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<TChessGameConfigPo> list = chessGameConfigPoMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            throw new Exception("无法启动拉取配置，请检查配置状态");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(list.size());
        CountDownLatch cdl = new CountDownLatch(list.size());
        List<String> resultList=new ArrayList<>();
        for (TChessGameConfigPo t : list) {
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    try {
                        if (!executorService.isShutdown()) {
                            K k = (K) applicationContext.getBean(loadConfig.targetMgr());
                            Long now=System.currentTimeMillis();
                            Long startId;
                            String key=String.format("chess_pull_%s_%s",t.getGameKind(),t.getAgentId());
                            String initDate= stringRedisTemplate.opsForValue().get(key);
                            Date endDateCache=initDate==null?null: DateUtils.parseDate(initDate,new String[]{"yyyy-MM-dd HH:mm:ss"});
                            if(endDateCache!=null){
                                startId=DateUtils.addSeconds(endDateCache,1).getTime();
                            }else{
                                startId=k.getNextId(t);
                            }
                            Long endId=DateUtils.addMinutes(new Date(startId),t.getLength().intValue()).getTime();
                            List<?> listPo=k.pullBet(startId,endId,t);
                            log.info(String.format("%s当前代理=%s拉取到注单(%s->%s)数量=%s",t.getGameKind(),t.getAgentId(),startId,t.getLength(),listPo.size()));
                            String startDate= DateFormatUtils.format(new Date(startId),"yyyy-MM-dd HH:mm:ss");
                            String endDate=DateFormatUtils.format(new Date(endId),"yyyy-MM-dd HH:mm:ss");
                            if(listPo.size()==0){
                                log.info(String.format("%s当前代理=%s拉取成功,但注单数量为0,时间段%s——%s",t.getGameKind(),t.getAgentId(),startDate,endDate));
                                String info="注单为空，不入报表统计";
                                log.info(info);
                                if(endId.compareTo(now)==1){
                                    log.info("注单明细不用存储，水位到达水平线，删除水位endDateCache="+initDate);
                                    stringRedisTemplate.delete(key);
                                }else{
                                    log.info("注单明细不用存储，水位未到达水平线，添加水位endDateCache="+endDate);
                                    stringRedisTemplate.opsForValue().set(key,endDate);
                                }
                                return info;
                            }
                            k.saveAndUpdate(listPo,t);
                            if(endId.compareTo(now)==1){
                                log.info("注单明细存储成功，水位到达水平线，删除水位endDateCache="+initDate);
                                stringRedisTemplate.delete(key);
                            }else{
                                log.info("注单明细存储成功，水位未到达水平线，添加水位endDateCache="+endDate);
                                stringRedisTemplate.opsForValue().set(key,endDate);
                            }
                            log.info(String.format("%s当前代理=%s开始报表统计(%s->%s)数量=%s", t.getGameKind(), t.getAgentId(),startId,t.getLength(),listPo.size()));
                            String result=(String) pjd.proceed(new Object[]{new String[]{t.getAgentId(),t.getGameKind(),startDate,endDate}});
                            return result;
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                        log.error(String.format("%s当前代理=%s拉取数据失败,错误消息=%s,请检查配置", t.getGameKind(),  t.getAgentId(), e.getMessage()));
                    } finally {
                        cdl.countDown();
                    }
                    return null;
                }
            });
            try{
                resultList.add(future.get());
            }catch (ExecutionException e) {
                e.printStackTrace();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultList.toString();
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
