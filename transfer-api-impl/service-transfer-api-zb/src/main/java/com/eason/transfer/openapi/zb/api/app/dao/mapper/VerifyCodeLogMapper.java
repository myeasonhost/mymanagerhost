package com.eason.transfer.openapi.zb.api.app.dao.mapper;

import com.eason.transfer.openapi.zb.api.app.dao.entity.VerifyCodeLogPo;

import java.util.HashMap;
import java.util.List;



/**
 * OoUserVerifyCodeDAO接口
 *
 * @author admin
 */
public interface VerifyCodeLogMapper{

	/**
	 * FunName:selectCountVerFail
	 * Description: 根据参数查询验证成功或失败的次数 
	 */
	public List<VerifyCodeLogPo> selectCountVerFail(HashMap<String, Object> codeMap);

    /**
	 * Description:根据主键id来获取对象VerifyCodeLogPo
	 */
    public VerifyCodeLogPo getObjectById(Integer id);

    /**
	 * Description:根据Model来获取对象VerifyCodeLogPo
	 */
    public VerifyCodeLogPo getObjectByModel(VerifyCodeLogPo obj);

    /**
	 * Description:根据Model来获取对象VerifyCodeLogPo
	 */
    public List<VerifyCodeLogPo> getListByModel(VerifyCodeLogPo obj);
    
	/**
	 * Description:新增对象VerifyCodeLogPo
	 */
    public int insertModel(VerifyCodeLogPo obj);
    
	/**
	 * Description:根据ID更新对象VerifyCodeLogPo
	 *        只更新不为空的字段
	 */
	public int updateModelById(VerifyCodeLogPo obj);
    
	/**
	 * Description:删除对象
	 */
	public int deleteById(Integer id);
    
}
