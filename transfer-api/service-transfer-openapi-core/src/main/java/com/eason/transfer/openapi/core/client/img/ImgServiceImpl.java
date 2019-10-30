package com.eason.transfer.openapi.core.client.img;

import com.eason.transfer.openapi.core.api.exception.OpenApiBaseException;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexNotice;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexNoticeExample;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexSwiper;
import com.eason.transfer.openapi.core.client.img.dao.entity.TIndexSwiperExample;
import com.eason.transfer.openapi.core.client.img.dao.mapper.TIndexNoticeMapper;
import com.eason.transfer.openapi.core.client.img.dao.mapper.TIndexSwiperMapper;
import com.eason.transfer.openapi.core.client.img.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("imgServiceImpl")
@Slf4j
public class ImgServiceImpl {
    @Autowired
    private TIndexSwiperMapper indexSwiperMapper;
    @Autowired
    private TIndexNoticeMapper indexNoticeMapper;

    public ImgResponse getIndexSwiper(ImgRequest request) throws OpenApiBaseException {
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
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }
    }

    public NoticeResponse getIndexNotice(NoticeRequest request) throws OpenApiBaseException {
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
            OpenApiBaseException excp = new OpenApiBaseException(e);
            throw excp;
        }
    }

}
