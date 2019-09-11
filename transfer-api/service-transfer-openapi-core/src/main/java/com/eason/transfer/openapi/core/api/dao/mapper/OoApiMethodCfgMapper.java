package com.eason.transfer.openapi.core.api.dao.mapper;

import com.eason.transfer.openapi.core.api.dao.model.OoApiMethodCfgModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OoApiMethodCfgDAO接口
 * @author eason
 */
@Repository
public interface OoApiMethodCfgMapper{

    public OoApiMethodCfgModel getObjectById(Integer id);

    public OoApiMethodCfgModel getObjectByModel(OoApiMethodCfgModel obj);

    public List<OoApiMethodCfgModel> getListByModel(OoApiMethodCfgModel obj);

    public int insertModel(OoApiMethodCfgModel obj);

    public int addModel(OoApiMethodCfgModel obj);

	public int updateModelById(OoApiMethodCfgModel obj);

	public int deleteById(Integer id);
    
}
