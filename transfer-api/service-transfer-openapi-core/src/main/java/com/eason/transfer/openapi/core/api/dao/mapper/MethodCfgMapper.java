package com.eason.transfer.openapi.core.api.dao.mapper;

import com.eason.transfer.openapi.core.api.dao.model.ApiMethod;
import com.eason.transfer.openapi.core.api.dao.model.ApiMethodCfg;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @ClassName: MethodCfgMapper
* @Description:API接口配置管理DAO层接口
* @author: eason
*/
@Repository
public interface MethodCfgMapper {
	
	public void saveMethodCfg(ApiMethodCfg methodCfg);
	
	public List<ApiMethodCfg> getMethodCfgList();

	public List<ApiMethodCfg> getMethodCfgListByCondition(Map<String, Object> map);

	public void deleteMethodCfgById(int id);
	
	public ApiMethodCfg getMethodCfgById(int id);
	
	public void updateMethodCfg(ApiMethodCfg methodCfg);
	
	public List<ApiMethod> getMethodList();

	public List<ApiMethodCfg> methodcfgPageSelect(Map<String, Integer> map) throws Exception;

	public int methodcfgConditionTotalRecord(Map<String, Object> map);

	public int methodcfgTotalRecord() throws Exception;
	public List<ApiMethodCfg> methodcfgPageSelectByCondition(Map<String, Object> map);
}
