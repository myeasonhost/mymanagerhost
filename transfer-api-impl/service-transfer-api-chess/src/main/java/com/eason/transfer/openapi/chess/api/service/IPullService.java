package com.eason.transfer.openapi.chess.api.service;

import java.util.List;

public interface IPullService<T,K> {
    public T extAttr(T t, K k);
    public void saveAndUpdate(List<T> listPo, K k);
    public List<T> pullBet(Long startId, Long endId, K k) throws Exception;
    public Long getMaxId(K k);
    public Long getNextId(K k);
}
