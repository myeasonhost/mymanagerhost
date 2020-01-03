package com.eason.transfer.openapi.core.sdk.zb.user;


import com.eason.transfer.openapi.core.sdk.zb.model.GiftRequest;
import com.eason.transfer.openapi.core.sdk.zb.model.GiftResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @apiDefine 4user 用户API
 */
@FeignClient(contextId = "app#IUserService",value = "service-transfer-api-zb")
public interface ZbUserService {

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/zbUser/addGift")
	GiftResponse addGift(@RequestBody GiftRequest request) throws Exception;

}
