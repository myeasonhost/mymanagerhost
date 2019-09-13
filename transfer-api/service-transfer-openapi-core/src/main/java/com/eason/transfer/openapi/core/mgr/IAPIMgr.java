package com.eason.transfer.openapi.core.mgr;

import com.eason.transfer.openapi.core.mongo.po.BasePo;

public interface IAPIMgr<T extends BasePo> {
//    public T extAttr(T t, K k);
    public void saveAndUpdate(T t);
//    public Object getMaxId(K k);
//    public Object getNextId(K k);
}
