package com.eason.report.pull.core.mgr;

public interface IPullMgr<T,K> {
    public T extAttr(T t, K k);
    public void saveAndUpdate(T po, K k);
    public Object getMaxId(K k);
    public Object getNextId(K k);
}
