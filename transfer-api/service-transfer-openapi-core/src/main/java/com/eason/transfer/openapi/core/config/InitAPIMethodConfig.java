//package com.eason.transfer.openapi.core.config;
//
//import com.eason.transfer.openapi.core.annotation.ApiMethodCategory;
//import com.eason.transfer.openapi.core.base.BaseAPI;
//import com.eason.transfer.openapi.core.exception.ApiException;
//import com.eason.transfer.openapi.core.model.ResponseModel;
//import com.eason.transfer.openapi.data.annotation.LoadConfig;
//import com.eason.transfer.openapi.data.annotation.MQProducer;
//import com.eason.transfer.openapi.data.annotation.TypeMgr;
//import com.eason.transfer.openapi.data.exception.ServiceException;
//import com.eason.transfer.openapi.data.model.MsgModel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.*;
//
//@Component
//@Slf4j
//public class InitAPIMethodConfig extends BaseAPI implements ApplicationContextAware {
//    private ApplicationContext applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext=applicationContext;
//    }
//
//    public <T /**extends BaseConfig**/, K /**extends IPullMgr**/>
//    List<ResponseModel> loadConfig(String type,String opt) throws ApiException {
//        try{
//            List<T> tList=null;
//            Map<String, Object> categoryMap=applicationContext.getBeansWithAnnotation(ApiMethodCategory.class);
//
//            ApiMethodCategory apiMethodCategory=pullAPIService.getClass().getAnnotation(ApiMethodCategory.class);
//            if(StringUtils.isEmpty(apiMethodCategory.cateCnName())){
//                throw new ApiException("当前@ApiMethodCategory服务名cateCnName不能为空");
//            }
//            if(StringUtils.isEmpty(apiMethodCategory.cateEnName())){
//                throw new ApiException("当前@ApiMethodCategory服务名cateEnName不能为空");
//            }
//            Method[] methods=pullAPIService.getClass().getDeclaredMethods();
//            for(Method method:methods) {
//                if (method.isAnnotationPresent(LoadConfig.class)) {
//                    tList=(List<T>)method.invoke(pullAPIService,null);
//                }
//            }
//            if(tList.isEmpty()){
//                throw new ServiceException("请配置当前@LoadConfig类型的配置表");
//            }
//
//            ExecutorService executorService=Executors.newFixedThreadPool(tList.size());
//            CountDownLatch cdl = new CountDownLatch(tList.size());
//            List<ResponseModel> list=new ArrayList<>();
//            for (T t:tList){
//                Future<ResponseModel> future=executorService.submit(new Callable<ResponseModel>() {
//                    @Override
//                    public ResponseModel call() throws Exception {
//                        try {
//                            if (!executorService.isShutdown()) {
//                                K k=null;
//                                Method targetMethod=null;
//                                Method[] methods=pullAPIService.getClass().getDeclaredMethods();
//                                for(Method method:methods) {
//                                    if (method.isAnnotationPresent(TypeMgr.class)) {
//                                        TypeMgr typeMgr=method.getAnnotation(TypeMgr.class);
//                                        k=(K)applicationContext.getBean(typeMgr.targetMgr());
//                                        targetMethod=method;
//                                    }
//                                }
//                                if(targetMethod==null){
//                                    throw new ServiceException("请配置当前mgr类型@TypeMgr的方法");
//                                }
//                                Object startId=k.getNextId(t);
//                                Integer size=(Integer) targetMethod.invoke(pullAPIService,t,k);
//                                if(size==0){
//                                    ResponseModel responseModel=ResponseModel.builder()
//                                            .code(SUCCESS)
//                                            .massge(String.format("%s当前代理=%s拉取到注单,数量=%s", code, t.getAgentId(), 0))
//                                            .obj(null)
//                                            .build();
//                                    return responseModel;
//                                }
//                                Object endId=k.getMaxId(t);
//                                MsgModel msg=MsgModel.builder()
//                                        .type(code)
//                                        .message("当前拉取size="+size+"条已完成，开始分发").build();
//                                t.getSiteMap().forEach((key,value)-> {
//                                    mqServiceProducer.notifySite(key,code,startId,endId,msg);
//                                });
//                                ResponseModel responseModel=ResponseModel.builder()
//                                        .code(SUCCESS)
//                                        .massge(String.format("%s当前代理=%s,拉取到注单,数量=%s", code, t.getAgentId(), size))
//                                        .obj(msg)
//                                        .build();
//                                return responseModel;
//                            }
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                            log.error("{}拉取数据失败,错误消息={},请检查配置={}", code,  e.getTargetException().getMessage(), t);
//                        } finally {
//                            cdl.countDown();
//                        }
//                        return errorModel;
//                    }
//                });
//                try{
//                    list.add(future.get());
//                }catch (ExecutionException e) {
//                    e.printStackTrace();
//                }catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            return list;
//        }catch(Exception e){
//            throw new ServiceException(e);
//        }
//    }
//
//    private Object getObject(Map<String, Object> producerMap,String url) throws ServiceException{
//        for(Map.Entry<String,Object> entry:producerMap.entrySet()){
//            String serverName=entry.getKey();
//            Object pullAPIService=entry.getValue();
//            MQProducer mqProducer=pullAPIService.getClass().getAnnotation(MQProducer.class);
//            String name=mqProducer.name();
//            String url2=mqProducer.url();
//            if(serverName.equalsIgnoreCase(name) && url.equals(url2)){
//                return pullAPIService;
//            }
//        }
//        throw new ServiceException("当前@MQProducer服务名name或者url错误");
//    }
//}
