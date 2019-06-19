package com.eason.report.pull.core.api;

import com.eason.report.pull.core.annotation.LoadConfig;
import com.eason.report.pull.core.annotation.TypeMgr;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.Model;
import com.eason.report.pull.core.model.ResponseModel;
import com.eason.report.pull.core.mq.MQServiceProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@Slf4j
public class PullAPI extends MQServiceProducer {
    private ExecutorService executorService =  Executors.newFixedThreadPool(BaseAPI.GAMENUM);

    @RequestMapping(value = "/{type}/getPullBet",method = RequestMethod.POST)
    public List<ResponseModel> getPullBet(@PathVariable(value = "type") String type) throws ServiceException{
        try{

            List<BaseConfig> configPoList=null;
            Object pullAPIService=producerMap.get(type.toUpperCase()+xxxPullAPIImpl);
            if(pullAPIService==null){
                throw new ServiceException("不支持当前类型type="+type);
            }
            Method[] methods=pullAPIService.getClass().getDeclaredMethods();
            for(Method method:methods) {
                if (method.isAnnotationPresent(LoadConfig.class)) {
                    configPoList=(List<BaseConfig>)method.invoke(pullAPIService,null);
                }
            }
            if(configPoList.isEmpty()){
                throw new ServiceException("请配置当前@LoadConfig类型的配置表");
            }
            CountDownLatch cdl = new CountDownLatch(configPoList.size());
            List<ResponseModel> list=new ArrayList<>();
            configPoList.forEach(configPo -> {
                Future<ResponseModel> future=executorService.submit(new Callable<ResponseModel>() {
                    @Override
                    public ResponseModel call() throws Exception {
                        try {
                            if (!executorService.isShutdown()) {
                                IPullMgr iPullMgr=null;
                                Method targetMethod=null;
                                Method[] methods=pullAPIService.getClass().getDeclaredMethods();
                                for(Method method:methods) {
                                    if (method.isAnnotationPresent(TypeMgr.class)) {
                                        TypeMgr typeMgr=method.getAnnotation(TypeMgr.class);
                                        iPullMgr=(IPullMgr)applicationContext.getBean(typeMgr.targetMgr());
                                        targetMethod=method;
                                    }
                                }
                                if(targetMethod==null){
                                    throw new ServiceException("请配置当前mgr类型@TypeMgr的方法");
                                }
                                Object startId=iPullMgr.getNextId(configPo);
                                Integer size=(Integer) targetMethod.invoke(pullAPIService,configPo,iPullMgr);
                                if(size==0){
                                    ResponseModel responseModel=ResponseModel.builder()
                                            .code(SUCCESS)
                                            .massge(String.format("%s当前代理=%s拉取到注单,数量=%s", type, configPo.getAgent(), 0))
                                            .obj(null)
                                            .build();
                                    return responseModel;
                                }
                                Object endId=iPullMgr.getMaxId(configPo);
                                Model model= Model.builder()
                                        .type(type.toUpperCase())
                                        .startId(startId)
                                        .endId(endId).build();
                                configPo.getSiteMap().forEach((key,value)-> {
                                    notifySite(key, size,model);
                                });
                                ResponseModel responseModel=ResponseModel.builder()
                                        .code(SUCCESS)
                                        .massge(String.format("%s当前代理=%s,拉取到注单,数量=%s", type, configPo.getAgent(), size))
                                        .obj(model.toString())
                                        .build();
                                return responseModel;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("{}拉取数据失败,错误消息={},请检查配置={}", type, e.getMessage(), configPo);
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
            });
            return list;
        }catch(Exception e){
            throw new ServiceException(e);
        }
    }
}
