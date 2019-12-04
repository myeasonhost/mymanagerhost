package com.eason.transfer.openapi.chess.api;

import com.eason.transfer.openapi.chess.dao.entity.TReportAuditTotalPo;
import com.eason.transfer.openapi.chess.dao.entity.TReportAuditTotalPoExample;
import com.eason.transfer.openapi.chess.dao.entity.TReportDayUserPo;
import com.eason.transfer.openapi.chess.dao.entity.TReportDayUserPoExample;
import com.eason.transfer.openapi.chess.dao.mapper.TReportAuditTotalPoMapper;
import com.eason.transfer.openapi.chess.dao.mapper.TReportDayUserPoMapper;
import com.eason.transfer.openapi.core.sdk.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author eason
 */
@RestController
@Slf4j
public class ReportServiceImpl implements IReportService {

    @Autowired
    private TReportAuditTotalPoMapper reportAuditTotalPoMapper;
    @Autowired
    private TReportDayUserPoMapper reportDayUserPoMapper;
    @Autowired
    private ChessServiceImpl chessServiceImpl;

    @Scheduled(fixedRate = 60000)   // 每分钟执行一次
    public void startPull(){
        chessServiceImpl.getPullBet();
    }

    @CrossOrigin(origins = "*")
    @Override
    public ReportUserGameTypeResponse getUserGameTypeReport(ReportUserGameTypeRequest request) throws Exception {
        ReportUserGameTypeResponse response=new ReportUserGameTypeResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getStartTime())){
            code ="startTime";
            result ="开始时间不能为空";
        } else if (StringUtils.isBlank(request.getEndTime())){
            code ="endTime";
            result ="结束时间不能为空";
        } else if (request.getPage()==null){
            code ="page";
            result ="分页参数不能为空";
        } else if (request.getPageSize()==null){
            code ="pageSize";
            result ="分页参数不能为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }

        Date startDate= null;
        Date endDate= null;
        try {
            startDate = DateUtils.parseDate(request.getStartTime(),new String[]{"yyyy-MM-dd"});
            endDate = DateUtils.parseDate(request.getEndTime(),new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {
            response.addErrInfo("time", "日期只支持（yyyy-MM-dd）格式", null);
            response.setSuccessCount(0);
            return response;
        }
        long betweenDays = (endDate.getTime() - startDate.getTime()) / (1000L*3600L*24L);
        if(betweenDays>7){
            response.addErrInfo("time", "日期间隔最大不能超过7天", null);
            response.setSuccessCount(0);
            return response;
        }
        if(betweenDays<0){
            response.addErrInfo("time", "结束时间大于开始时间", null);
            response.setSuccessCount(0);
            return response;
        }
        TReportAuditTotalPoExample example=new TReportAuditTotalPoExample();
        TReportAuditTotalPoExample.Criteria criteria=example.createCriteria();
        criteria.andSiteIdEqualTo(request.getSiteId());
        criteria.andBetTimeBetween(startDate,endDate);

        if (!StringUtils.isBlank(request.getUsername())) {
            criteria.andUsernameEqualTo(request.getUsername());
        }
        if (!StringUtils.isBlank(request.getLoginType())) {
            criteria.andGameKindEqualTo(request.getLoginType());
        }
        long total=this.reportAuditTotalPoMapper.countByExample(example);

        example.setStartRow(request.getPage()*request.getPageSize());
        example.setPageSize(request.getPageSize());
        List<TReportAuditTotalPo> listPo=this.reportAuditTotalPoMapper.selectByExample(example);
        List<ReportUserGameTypeVo> listVo=new ArrayList<>();
        for (TReportAuditTotalPo po:listPo){
            ReportUserGameTypeVo vo=new ReportUserGameTypeVo();
            BeanUtils.copyProperties(po,vo);
            vo.setBetTime(DateFormatUtils.format(po.getCreateTime(),"yyyy-MM-dd"));
            listVo.add(vo);
        }
        response.setTotal(total);
        response.setList(listVo);
        return response;
    }

    @CrossOrigin(origins = "*")
    @Override
    public ReportUserGameKindResponse getUserGameKindReport(ReportUserGameKindRequest request) throws Exception {
        ReportUserGameKindResponse response=new ReportUserGameKindResponse();
        String code = null;
        String result = null;
        if (StringUtils.isBlank(request.getSiteId())) {
            code ="siteId";
            result ="站点ID不能为空";
        } else if (StringUtils.isBlank(request.getStartTime())){
            code ="startTime";
            result ="开始时间不能为空";
        } else if (StringUtils.isBlank(request.getEndTime())){
            code ="endTime";
            result ="结束时间不能为空";
        } else if (request.getPage()==null){
            code ="page";
            result ="分页参数不能为空";
        } else if (request.getPageSize()==null){
            code ="pageSize";
            result ="分页参数不能为空";
        }

        if (StringUtils.isNotEmpty(result)){
            response.addErrInfo(code, result, null);
            response.setSuccessCount(0);
            return response;
        }

        Date startDate= null;
        Date endDate= null;
        try {
            startDate = DateUtils.parseDate(request.getStartTime(),new String[]{"yyyy-MM-dd"});
            endDate = DateUtils.parseDate(request.getEndTime(),new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {
            response.addErrInfo("time", "日期只支持（yyyy-MM-dd）格式", null);
            response.setSuccessCount(0);
            return response;
        }
        long betweenDays = (endDate.getTime() - startDate.getTime()) / (1000L*3600L*24L);
        if(betweenDays>7){
            response.addErrInfo("time", "日期间隔最大不能超过7天", null);
            response.setSuccessCount(0);
            return response;
        }
        if(betweenDays<0){
            response.addErrInfo("time", "结束时间大于开始时间", null);
            response.setSuccessCount(0);
            return response;
        }
        TReportDayUserPoExample example=new TReportDayUserPoExample();
        TReportDayUserPoExample.Criteria criteria=example.createCriteria();
        criteria.andSiteIdEqualTo(request.getSiteId());
        criteria.andBetTimeBetween(startDate,endDate);

        if (!StringUtils.isBlank(request.getUsername())) {
            criteria.andUsernameEqualTo(request.getUsername());
        }
        if (!StringUtils.isBlank(request.getLoginType())) {
            criteria.andGameKindEqualTo(request.getLoginType());
        }
        long total=this.reportDayUserPoMapper.countByExample(example);

        example.setStartRow(request.getPage()*request.getPageSize());
        example.setPageSize(request.getPageSize());
        List<TReportDayUserPo> listPo=this.reportDayUserPoMapper.selectByExample(example);
        List<ReportUserGameKindVo> listVo=new ArrayList<>();
        for (TReportDayUserPo po:listPo){
            ReportUserGameKindVo vo=new ReportUserGameKindVo();
            BeanUtils.copyProperties(po,vo);
            vo.setBetTime(DateFormatUtils.format(po.getCreateTime(),"yyyy-MM-dd"));
            listVo.add(vo);
        }
        response.setTotal(total);
        response.setList(listVo);
        return response;
    }

}
