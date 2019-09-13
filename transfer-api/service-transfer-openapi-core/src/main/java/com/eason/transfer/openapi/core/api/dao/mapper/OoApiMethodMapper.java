package com.eason.transfer.openapi.core.api.dao.mapper;


import com.eason.transfer.openapi.core.api.dao.model.OoApiMethodModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * OoApiMethodDAO接口
 * @author eason
 */
public interface OoApiMethodMapper{
	
	public List<OoApiMethodModel> getApiMethodInfoList();

    public OoApiMethodModel getObjectById(Integer id);

    public OoApiMethodModel getObjectByModel(OoApiMethodModel obj);

    public List<OoApiMethodModel> getListByModel(OoApiMethodModel obj);

    public int insertModel(OoApiMethodModel obj);

    public int addModel(OoApiMethodModel obj);

	public int updateModelById(OoApiMethodModel obj);

	public int deleteById(Integer id);
    
}
