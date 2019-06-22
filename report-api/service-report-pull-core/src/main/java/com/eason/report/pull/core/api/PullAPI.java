package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.mgr.IPullMgr;
import com.eason.report.pull.core.model.MsgModel;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mq.MQServiceProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@Slf4j
public class PullAPI extends BaseAPI implements ApplicationContextAware {
    private Map<String, Object> producerMap;
    private ApplicationContext applicationContext;
    @Autowired
    private MQServiceProducer mqServiceProducer;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        producerMap = applicationContext.getBeansWithAnnotation(MQProducer.class);
    }

    @RequestMapping(value = "/{type}/{opt}",method = RequestMethod.POST)
    public <T extends BaseConfig, K extends IPullMgr>
    List<ResponseModel> getPullBet(@PathVariable(name = "type") String type,
                                   @PathVariable(name = "opt") String opt) throws ServiceException{
        try{
            List<T> tList=null;
            String url=String.format("/%s/%s",type,opt);
            Object pullAPIService=getObject(producerMap,url);
            MQProducer mqProducer=pullAPIService.getClass().getAnnotation(MQProducer.class);
            String code=mqProducer.code();
            if(StringUtils.isEmpty(code)){
                throw new ServiceException("当前@MQProducer服务名code错误");
            }
            Method[] methods=pullAPIService.getClass().getDeclaredMethods();
            for(Method method:methods) {
                if (method.isAnnotationPresent(LoadConfig.class)) {
                    tList=(List<T>)method.invoke(pullAPIService,null);
                }
            }
            if(tList.isEmpty()){
                throw new ServiceException("请配置当前@LoadConfig类型的配置表");
            }

            ExecutorService executorService=Executors.newFixedThreadPool(tList.size());
            CountDownLatch cdl = new CountDownLatch(tList.size());
            List<ResponseModel> list=new ArrayList<>();
            for (T t:tList){
                Future<ResponseModel> future=executorService.submit(new Callable<ResponseModel>() {
                    @Override
                    public ResponseModel call() throws Exception {
                        try {
                            if (!executorService.isShutdown()) {
                                K k=null;
                                Method targetMethod=null;
                                Method[] methods=pullAPIService.getClass().getDeclaredMethods();
                                for(Method method:methods) {
                                    if (method.isAnnotationPresent(TypeMgr.class)) {
                                        TypeMgr typeMgr=method.getAnnotation(TypeMgr.class);
                                        k=(K)applicationContext.getBean(typeMgr.targetMgr());
                                        targetMethod=method;
                                    }
                                }
                                if(targetMethod==null){
                                    throw new ServiceException("请配置当前mgr类型@TypeMgr的方法");
                                }
                                Object startId=k.getNextId(t);
                                Integer size=(Integer) targetMethod.invoke(pullAPIService,t,k);
                                if(size==0){
                                    ResponseModel responseModel=ResponseModel.builder()
                                            .code(SUCCESS)
                                            .massge(String.format("%s当前代理=%s拉取到注单,数量=%s", code, t.getAgentId(), 0))
                                            .obj(null)
                                            .build();
                                    return responseModel;
                                }
                                Object endId=k.getMaxId(t);
                                MsgModel msg=MsgModel.builder()
                                        .type(code)
                                        .message("当前拉取size="+size+"条已完成，开始分发").build();
                                t.getSiteMap().forEach((key,value)-> {
                                    mqServiceProducer.notifySite(key,code,startId,endId,msg);
                                });
                                ResponseModel responseModel=ResponseModel.builder()
                                        .code(SUCCESS)
                                        .massge(String.format("%s当前代理=%s,拉取到注单,数量=%s", code, t.getAgentId(), size))
                                        .obj(msg)
                                        .build();
                                return responseModel;
                            }
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                            log.error("{}拉取数据失败,错误消息={},请检查配置={}", code,  e.getTargetException().getMessage(), t);
                        } finally {
                            cdl.countDown();
                        }
                        return errorModel;
                    }
                });
                try{
                    list.add(future.get());
                }catch (ExecutionException e) {
                    e.printStackTrace();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }catch(Exception e){
            throw new ServiceException(e);
        }
    }

    private Object getObject(Map<String, Object> producerMap,String url) throws ServiceException{
        for(Map.Entry<String,Object> entry:producerMap.entrySet()){
            String serverName=entry.getKey();
            Object pullAPIService=entry.getValue();
            MQProducer mqProducer=pullAPIService.getClass().getAnnotation(MQProducer.class);
            String name=mqProducer.name();
            String url2=mqProducer.url();
            if(serverName.equalsIgnoreCase(name) && url.equals(url2)){
                return pullAPIService;
            }
        }
        throw new ServiceException("当前@MQProducer服务名name或者url错误");
    }
}
