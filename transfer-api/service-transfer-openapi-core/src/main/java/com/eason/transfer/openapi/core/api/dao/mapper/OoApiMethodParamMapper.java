package com.eason.transfer.openapi.core.api.dao.mapper;


import com.eason.transfer.openapi.core.api.dao.model.OoApiMethodParamModel;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * OoApiMethodParamDAO接口
 * @author eason
 */
@Repository
public interface OoApiMethodParamMapper{

	public int deleteAll();

    public OoApiMethodParamModel getObjectById(Integer id);

    public OoApiMethodParamModel getObjectByModel(OoApiMethodParamModel obj);

    public List<OoApiMethodParamModel> getListByModel(OoApiMethodParamModel obj);

    public int insertModel(OoApiMethodParamModel obj);

	public int updateModelById(OoApiMethodParamModel obj);

	public int deleteById(Integer id);
	
}
