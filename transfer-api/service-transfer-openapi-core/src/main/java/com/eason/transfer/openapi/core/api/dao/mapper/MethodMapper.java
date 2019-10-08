package com.eason.transfer.openapi.core.api.dao.mapper;

import com.eason.transfer.openapi.core.api.dao.model.ApiMethod;
import com.eason.transfer.openapi.core.api.dao.model.ApiMethodType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MethodMapper
 * @Description:API方法管理DAO层接口
 * @author: eason
 */
@Repository
public interface MethodMapper {

    public List<ApiMethodType> getMethodTypeList();

    public void saveMethod(ApiMethod method);

    public List<ApiMethod> getMethodList();

    public void deleteMethodById(int id);

    public List<ApiMethod> getMethodListByCondition(Map<String, Object> map);

    public ApiMethod getMethodById(int id);

    public void updateMethod(ApiMethod method);

    public List<ApiMethod> methodPageSelect(Map<String, Integer> map) throws Exception;

    public int methodConditionTotalRecord(Map<String, Object> map);

    public int methodTotalRecord() throws Exception;

    public List<ApiMethod> methodPageSelectByCondition(Map<String, Object> map);
}
