package com.eason.report.pull.core.mq;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.DateModel;
import com.eason.report.pull.core.model.Model;
import com.eason.report.pull.core.model.MsgModel;
import com.eason.report.pull.core.model.NumModel;
import com.eason.report.pull.core.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Slf4j
public class MQServiceConsumer extends BaseAPI implements ApplicationContextAware {

    @Autowired
    private MongoTemplate mongoTemplate;
    protected Map<String, Object> consumerMap;
    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        consumerMap = applicationContext.getBeansWithAnnotation(MQConsumer.class); // 获取所有带有 MQConsumer 注解的 Spring Bean
        for (Object serviceBean : consumerMap.values()) {
            MQConsumer result = serviceBean.getClass().getAnnotation(MQConsumer.class);
            System.out.println(result);
        }
    }

    @JmsListener(destination = "${target.siteId}")
    protected void receiverSite(MsgModel msg){
        log.info("站点siteId={},收到消息={}",siteId,msg);
        String type;
        try {
            type=msg.getType();
            Object pushAPIService=consumerMap.get(type+xxxPushAPIImpl);
            if (StringUtils.isEmpty(type) && pushAPIService!=null) {
                log.error("站点siteId={},pushAPIService={}消息接收任务不能执行，请检查类名规则",siteId,pushAPIService);
                return;
            }
            Model model=msg.getModel();
            if(model instanceof NumModel){
                NumModel numModel=(NumModel)model;
                Method[] methods=pushAPIService.getClass().getDeclaredMethods();
                for(Method method:methods){
                    if(method.isAnnotationPresent(SourceQuery.class)){
                        SourceQuery query=method.getAnnotation(SourceQuery.class);
                        try{
                            List list=mongoTemplate.find(query(where(query.targetId())
                                    .gte(numModel.getStartId())
                                    .lte(numModel.getEndId())
                                    .and("siteid").is(siteId)),query.targetMgo());
                            if(list.isEmpty()){
                                throw new ServiceException("接收数据为空");
                            }
                            method.invoke(pushAPIService,siteId,list);
                            for(Method ar:methods){
                                if(ar.isAnnotationPresent(AuditReport.class)){
                                    AuditReport auditReport=ar.getAnnotation(AuditReport.class);
                                    Object obj=applicationContext.getBean(auditReport.targetDao());
                                    String result=(String) obj
                                            .getClass()
                                            .getMethod(auditReport.procedureName(),Integer.class,String.class,Long.class,Long.class)
                                            .invoke(obj,siteId,type,numModel.getStartId(),numModel.getEndId());
                                    ar.invoke(pushAPIService,siteId,result);
                                }
                            }
                        }catch(Exception e){
                            List list=mongoTemplate.findAllAndRemove(query(where(query.targetId())
                                    .gte(numModel.getStartId())
                                    .lte(numModel.getEndId())
                                    .and("siteid").is(siteId)),query.targetMgo());
                            log.error("{}站点siteId={}，数据回滚num={}，请查看传参",type,siteId,list.size());
                            e.printStackTrace();
                        }

                    }

                }
                log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,numModel.getStartId(),numModel.getEndId());
            }else if(model instanceof DateModel){
                DateModel dateModel=(DateModel)model;
                Method[] methods=pushAPIService.getClass().getDeclaredMethods();
                for(Method method:methods){
                    if(method.isAnnotationPresent(SourceQuery.class)){
                        SourceQuery query=method.getAnnotation(SourceQuery.class);
                        try{
                            List list=mongoTemplate.find(query(where(query.targetId())
                                    .gte(DateUtil.covertTime(dateModel.getStartId()))
                                    .lte(DateUtil.covertTime(dateModel.getEndId()))
                                    .and("siteId").is(siteId)),query.targetMgo());
                            method.invoke(pushAPIService,siteId,list);
                            for(Method ar:methods){
                                if(ar.isAnnotationPresent(AuditReport.class)){
                                    AuditReport auditReport=ar.getAnnotation(AuditReport.class);
                                    Object obj=applicationContext.getBean(auditReport.targetDao());
                                    String result=(String) obj
                                            .getClass()
                                            .getMethod(auditReport.procedureName(),Integer.class,String.class,String.class,String.class)
                                            .invoke(obj,siteId,type,dateModel.getStartId(),dateModel.getEndId());
                                    ar.invoke(pushAPIService,siteId,result);
                                }
                            }
                        }catch(Exception e){
                            List list=mongoTemplate.findAllAndRemove(query(where(query.targetId())
                                    .gte(DateUtil.covertTime(dateModel.getStartId()))
                                    .lte(DateUtil.covertTime(dateModel.getEndId()))
                                    .and("siteId").is(siteId)),query.targetMgo());
                            log.error("{}站点siteId={}，数据回滚num={}，请查看传参",type,siteId,list.size());
                            e.printStackTrace();
                        }

                    }

                }
                log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,dateModel.getStartId(),dateModel.getEndId());
            }else{
                log.error("站点siteId={},model={}消息接收任务不能执行，请检查消息模型Model",siteId,model);
            }

        } catch (Exception e) {
            log.error("站点siteId={},系统异常={}", siteId,e.getMessage());
            e.printStackTrace();
        }
    }

}
