package com.eason.report.pull.core.api;

import com.eason.report.pull.core.exception.ServiceException;
import com.eason.report.pull.core.mq.MQServiceProducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class PullAPIService extends MQServiceProducer {
    protected ExecutorService executorService =  Executors.newFixedThreadPool(GAMENUM);

    protected abstract void getPullBet() throws ServiceException;
    protected abstract void getPullBet(Long maxId,Integer length) throws ServiceException;
    protected abstract void getPullBet(String maxId,Integer length) throws ServiceException;

}
