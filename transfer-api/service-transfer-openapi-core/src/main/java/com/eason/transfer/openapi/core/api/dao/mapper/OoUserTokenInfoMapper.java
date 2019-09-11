package com.eason.transfer.openapi.core.api.dao.mapper;

import com.eason.transfer.openapi.core.api.dao.model.UserTokenInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OoUserTokenInfoMapper {

	public UserTokenInfo getUserTokenInfoByToken(String token);

	public Integer insertUserToken(UserTokenInfo obj);

	public Integer updateToken(UserTokenInfo obj);

	public Date getLoginDateByUserId(String userId);
	
}
