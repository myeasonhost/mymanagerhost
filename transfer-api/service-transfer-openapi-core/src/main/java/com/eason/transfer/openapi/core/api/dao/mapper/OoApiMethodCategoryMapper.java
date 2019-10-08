package com.eason.transfer.openapi.core.api.dao.mapper;

import com.eason.transfer.openapi.core.api.dao.model.OoApiMethodCategoryModel;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * OoApiMethodCategoryDAO接口
 *
 * @author eason
 */
@Repository
public interface OoApiMethodCategoryMapper {

    public OoApiMethodCategoryModel getObjectById(Integer id);

    public OoApiMethodCategoryModel getObjectByModel(OoApiMethodCategoryModel obj);

    public List<OoApiMethodCategoryModel> getListByModel(OoApiMethodCategoryModel obj);

    public int insertModel(OoApiMethodCategoryModel obj);

    public int updateModelById(OoApiMethodCategoryModel obj);

    public int deleteById(Integer id);

}
