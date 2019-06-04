package com.eason.report.pull.core.api;

import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.mq.MQServiceConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class PushAPIService extends MQServiceConsumer {
    protected ExecutorService executorService =  Executors.newFixedThreadPool(GAMENUM);

    public abstract void getPushBet(Integer siteId,String prex,String startId,String endId) throws ServiceException;
    public abstract void getPushBet(Integer siteId,String prex,Long startId,Long endId) throws ServiceException;

}
