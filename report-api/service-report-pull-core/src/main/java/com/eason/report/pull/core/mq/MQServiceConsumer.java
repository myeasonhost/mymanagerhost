package com.eason.report.pull.core.mq;

import com.eason.report.pull.core.annotation.AuditReport;
import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.SourceQuery;
import com.eason.report.pull.core.base.BaseAPI;
import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.model.Model;
import com.eason.report.pull.core.model.MsgModel;
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
    private Map<String, Object> consumerMap;
    private ApplicationContext applicationContext;

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
            Method[] methods=pushAPIService.getClass().getDeclaredMethods();
            for(Method method:methods){
                if(method.isAnnotationPresent(SourceQuery.class)){
                    SourceQuery query=method.getAnnotation(SourceQuery.class);
                    try{
                        List list=mongoTemplate.find(query(where(query.targetId())
                                .gte(model.getStartId())
                                .lte(model.getEndId())
                                .and("siteid").is(siteId)),query.targetMgo());
                        if(list.isEmpty()){
                            throw new ServiceException("接收数据为空");
                        }
                        method.invoke(pushAPIService,siteId,list);
                        for(Method ar:methods){
                            if(ar.isAnnotationPresent(AuditReport.class)){
                                AuditReport auditReport=ar.getAnnotation(AuditReport.class);
                                Object obj=applicationContext.getBean(auditReport.targetDao());
                                Method[] methods1=obj.getClass().getMethods();
                                for(Method method1:methods1){
                                    if(auditReport.procedureName().equals(method1.getName())){
                                        String result=(String) method1.invoke(obj,siteId,type,model.getStartId(),model.getEndId());
                                        ar.invoke(pushAPIService,siteId,result);
                                    }
                                }
                            }
                        }
                    }catch(Exception e){
                        List list=mongoTemplate.findAllAndRemove(query(where(query.targetId())
                                .gte(model.getStartId())
                                .lte(model.getEndId())
                                .and("siteid").is(siteId)),query.targetMgo());
                        log.error("{}站点siteId={}，数据回滚num={}，请查看传参",type,siteId,list.size());
                        e.printStackTrace();
                    }

                }

            }
            log.info("站点siteId={}，游戏type={},当前startId={}——endId={}，开始分发",siteId,type,model.getStartId(),model.getEndId());

        } catch (Exception e) {
            log.error("站点siteId={},系统异常={}", siteId,e.getMessage());
            e.printStackTrace();
        }
    }

}
