package com.eason.transfer.openapi.core.sdk.zb;


import com.eason.transfer.openapi.core.sdk.zb.model.GiftRequest;
import com.eason.transfer.openapi.core.sdk.zb.model.GiftResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @apiDefine 4user 用户API
 */
@FeignClient(contextId = "zb#ZbUserService",value = "service-transfer-api-zb")
public interface IZbUserService {

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/zbUser/addGift")
	GiftResponse addGift(@RequestBody GiftRequest request) throws Exception;

}
