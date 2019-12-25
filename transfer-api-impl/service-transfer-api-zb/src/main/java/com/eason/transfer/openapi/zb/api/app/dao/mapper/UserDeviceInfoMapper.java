package com.eason.transfer.openapi.zb.api.app.dao.mapper;

import com.eason.transfer.openapi.zb.api.app.dao.entity.UserDeviceInfoPo;

/**
 * UserDeviceInfoDAO接口
 *
 * @author eason
 */
public interface UserDeviceInfoMapper{
	public int insertUserDeviceInfo(UserDeviceInfoPo po);
	public UserDeviceInfoPo getUserDeviceByPo(UserDeviceInfoPo po);
	public int updateUserDeviceByClear(UserDeviceInfoPo po);
	public int updateUserDeviceByPo(UserDeviceInfoPo po);
}
