package com.eason.transfer.openapi.core.api.dao.mapper;

import com.eason.transfer.openapi.core.api.dao.model.OoApiAccessLogModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * OoApiAccessLogDAO接口
 * @author eason
 */
@Repository
public interface OoApiAccessLogMapper{


	public int addPartition(OoApiAccessLogModel obj);
	
	public List<OoApiAccessLogModel> getApiLogPage(Map<String, Object> map);

	public int getCountByCiteria(Map<String, Object> map);

	public int deletePartition(OoApiAccessLogModel obj);

    public OoApiAccessLogModel getObjectById(Integer id);

    public OoApiAccessLogModel getObjectByModel(OoApiAccessLogModel obj);

    public List<OoApiAccessLogModel> getListByModel(OoApiAccessLogModel obj);

    public Integer insertModel(OoApiAccessLogModel obj);

	public int updateModelById(OoApiAccessLogModel obj);

	public int deleteById(Integer id);
    
}
