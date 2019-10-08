package com.eason.transfer.openapi.core.api.dao.mapper;


import com.eason.transfer.openapi.core.api.dao.model.AppInfo;
import com.eason.transfer.openapi.core.api.dao.model.OoAppInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OoAppInfoDAO接口
 *
 * @author eason
 */
@Repository
public interface OoAppInfoMapper {

    public List<AppInfo> getAppinfoList();

    public OoAppInfoModel getObjectById(Integer id);

    public OoAppInfoModel getObjectByModel(OoAppInfoModel obj);

    public List<OoAppInfoModel> getListByModel(OoAppInfoModel obj);

    public int insertModel(OoAppInfoModel obj);

    public int updateModelById(OoAppInfoModel obj);

    public int deleteById(Integer id);

}
