package com.eason.transfer.openapi.zb.api.user;

import com.eason.transfer.openapi.core.sdk.zb.IZbUserService;
import com.eason.transfer.openapi.core.sdk.zb.model.GiftRequest;
import com.eason.transfer.openapi.core.sdk.zb.model.GiftResponse;
import com.eason.transfer.openapi.zb.api.entity.GiftPo;
import com.eason.transfer.openapi.zb.api.mapper.GiftPoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class UserServiceImpl implements IZbUserService {
        @Autowired
        private GiftPoMapper giftPoMapper;

        @Override
        public GiftResponse addGift(GiftRequest request) {
            GiftResponse giftResponse=new GiftResponse();
            try{
                GiftPo gift=giftPoMapper.selectByGiftName(request.getGiftName());
                if(gift!=null){
                    giftResponse.setResult("礼物以存在");
                    return giftResponse;
                }
                GiftPo giftPo=new GiftPo();
                giftPo.setGiftName(request.getGiftName());
                //TODO 图片上传至服务器
                giftPo.setGiftLmg(request.getGiftImg());
                giftPo.setGiftPrice(request.getGiftPrice());
                giftPo.setSpecialstyle(request.getSpecialStyle());
                giftPoMapper.insert(giftPo);
                giftResponse.setResult("发送礼物成功");
            }catch (Exception e){
                giftResponse.setResult("发送礼物失败");
            }
            return giftResponse;
        }
}
