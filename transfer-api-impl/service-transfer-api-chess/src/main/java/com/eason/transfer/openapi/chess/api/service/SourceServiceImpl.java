package com.eason.transfer.openapi.chess.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.util.List;

@Service
@Slf4j
public class SourceServiceImpl {
    @Resource
    private EntityManager entityManager;

    @Transactional
    public <T,K> void insertSource(List<K> list,Class<T> tClass) throws Exception {
        try{
            String idName=null;
            Method[] methods=tClass.getMethods();
            for(Method m:methods){
                if(m.isAnnotationPresent(Id.class)){
                    idName=m.getName();
                }
            }
            if(StringUtils.isEmpty(idName)){
                return;
            }
            for(K k:list){
                try {
                    Method method=k.getClass().getDeclaredMethod(idName,null);
                    T po=entityManager.find(tClass,method.invoke(k,null));
                    if(po==null){
                        po=tClass.newInstance();
                        BeanUtils.copyProperties(k,po);
                        entityManager.persist(po);
                    }else{
                        BeanUtils.copyProperties(k,po);
                        entityManager.merge(po);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            throw new Exception(e);
        }
    }

}
