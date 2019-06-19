package com.eason.report.pull.core.base;

import com.eason.report.pull.core.annotation.MQConsumer;
import com.eason.report.pull.core.annotation.MQProducer;
import com.eason.report.pull.core.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class BaseAPI{

    @Value("${target.siteId}")
    protected Integer siteId;

    public static final Integer GAMENUM=11; //彩票、视讯、电游、棋牌、体育

    public static final String SUCCESS="0000";
    public static final String ERROR="1111";

    public static final ResponseModel successModel=ResponseModel.builder().code(SUCCESS).massge("操作成功").build();
    public static final ResponseModel errorModel=ResponseModel.builder().code(ERROR).massge("操作失败 ").build();

    public static final String xxxPullAPIImpl="PullAPIImpl";
    public static final String xxxPushAPIImpl="PushAPIImpl";

}
