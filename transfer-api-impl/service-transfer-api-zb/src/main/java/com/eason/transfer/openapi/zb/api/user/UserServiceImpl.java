package com.eason.transfer.openapi.zb.api.user;

import com.eason.transfer.openapi.core.sdk.zb.model.GiftRequest;
import com.eason.transfer.openapi.core.sdk.zb.model.GiftResponse;
import com.eason.transfer.openapi.core.sdk.zb.user.ZbUserService;
import com.eason.transfer.openapi.zb.api.entity.GiftPo;
import com.eason.transfer.openapi.zb.api.mapper.GiftPoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class UserServiceImpl implements ZbUserService {
        @Autowired
        private GiftPoMapper giftPoMapper;

        @Override
        public GiftResponse addGift(GiftRequest request) throws Exception {
            GiftResponse giftResponse=new GiftResponse();
            GiftPo giftPo=new GiftPo();
            giftPo.setGiftName(request.getGiftName());
            giftPo.setGiftLmg(request.getGiftImg());
            giftPo.setGiftPrice(request.getGiftPrice());
            giftPo.setSpecialstyle(request.getSpecialStyle());
            giftPoMapper.insert(giftPo);
            giftResponse.setResult("发送礼物成功");
            return giftResponse;
        }
}
