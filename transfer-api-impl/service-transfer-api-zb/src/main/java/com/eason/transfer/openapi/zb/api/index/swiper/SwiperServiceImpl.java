package com.eason.transfer.openapi.zb.api.index.swiper;


import com.eason.transfer.openapi.core.sdk.index.swiper.ISwiperService;
import com.eason.transfer.openapi.core.sdk.index.swiper.model.*;
import com.eason.transfer.openapi.zb.api.index.swiper.dao.entity.TIndexNotice;
import com.eason.transfer.openapi.zb.api.index.swiper.dao.entity.TIndexNoticeExample;
import com.eason.transfer.openapi.zb.api.index.swiper.dao.entity.TIndexSwiper;
import com.eason.transfer.openapi.zb.api.index.swiper.dao.entity.TIndexSwiperExample;
import com.eason.transfer.openapi.zb.api.index.swiper.dao.mapper.TIndexNoticeMapper;
import com.eason.transfer.openapi.zb.api.index.swiper.dao.mapper.TIndexSwiperMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class SwiperServiceImpl implements ISwiperService {
    @Autowired
    private TIndexSwiperMapper indexSwiperMapper;
    @Autowired
    private TIndexNoticeMapper indexNoticeMapper;

    public ImgResponse getImgSwiper(ImgRequest request) throws Exception {
        try{
            ImgResponse response=new ImgResponse();
            String code = null;
            String result = null;
            if (StringUtils.isBlank(request.getType())) {
                code ="type";
                result ="类型不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            TIndexSwiperExample example=new TIndexSwiperExample();
            example.createCriteria()
                    .andTypeEqualTo(request.getType());
            example.setOrderByClause("order_by asc");
            List<TIndexSwiper> list=indexSwiperMapper.selectByExample(example);
            if (list.size()==0){
                code="type";
                response.addErrInfo(code, "type可能错误，没有图片集", null);
                response.setSuccessCount(0);
                return response;
            }
            List<ImgVo> imgVoList=new ArrayList<>();
            for (TIndexSwiper tIndexSwiper:list){
                ImgVo imgVo=new ImgVo();
                BeanUtils.copyProperties(tIndexSwiper,imgVo);
                imgVoList.add(imgVo);
            }
            response.setList(imgVoList);
            return response;
        }catch (Exception e){
            log.error("获取图片失败", e);
            throw new Exception(e);
        }
    }

    public NoticeResponse getNoticeSwiper(NoticeRequest request) throws Exception {
        try{
            NoticeResponse response=new NoticeResponse();
            String code = null;
            String result = null;
            if (StringUtils.isBlank(request.getType())) {
                code ="type";
                result ="类型不能为空";
                response.addErrInfo(code, result, null);
                response.setSuccessCount(0);
                return response;
            }

            TIndexNoticeExample example=new TIndexNoticeExample();
            example.createCriteria()
                    .andTypeEqualTo(request.getType());
            example.setOrderByClause("update_by asc");
            List<TIndexNotice> list=indexNoticeMapper.selectByExample(example);
            if (list.size()==0){
                code="type";
                response.addErrInfo(code, "type可能错误，没有消息集", null);
                response.setSuccessCount(0);
                return response;
            }
            List<NoticeVo> noticeVoList=new ArrayList<>();
            for (TIndexNotice tIndexNotice:list){
                NoticeVo vo=new NoticeVo();
                BeanUtils.copyProperties(tIndexNotice,vo);
                noticeVoList.add(vo);
            }
            response.setList(noticeVoList);
            return response;
        }catch (Exception e){
            log.error("获取消息信息失败", e);
            throw new Exception(e);
        }
    }

}
