package com.eason.api.zb.web;

import com.eason.api.base.vo.response.ResponseVo;
import com.eason.api.zb.exception.ServiceException;
import com.eason.api.zb.service.FMgrService;
import com.eason.api.zb.service.FZhuboService;
import com.eason.api.zb.vo.zhubo.MgrStartPlayRequestVo;
import com.eason.api.zb.vo.zhubo.StartPlayRequestVo;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/mgr")
public class MgrControler {
    private static Logger log = LoggerFactory.getLogger(MgrControler.class);

    @Autowired
    private FMgrService fmgrService;

    @RequestMapping(value = "/{zbId}/createRoom", method = RequestMethod.POST)
    public ResponseVo createRoom(@PathVariable Integer zbId, @RequestBody MgrStartPlayRequestVo mgrstartPlayRequestVo, HttpServletRequest request) {
        try {
            String api_token=request.getHeader("api_token");
            if (StringUtils.isEmpty(api_token)){
                api_token = request.getParameter("token");
            }
            if (StringUtils.isEmpty(api_token)){
                throw new ServiceException("您未登陆");
            }
            ResponseVo responseVo = new ResponseVo(0, "操作成功");
            responseVo.setData(fmgrService.createRoom(zbId,api_token,mgrstartPlayRequestVo));
            return responseVo;
        } catch (ServiceException e) {
            ResponseVo responseVo = new ResponseVo(401, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (HystrixRuntimeException e) {
            ResponseVo responseVo = new ResponseVo(500, "服务器忙，请重试！");
            responseVo.setData(new HashMap<>());
            return responseVo;
        } catch (Exception e) {
            ResponseVo responseVo = new ResponseVo(500, e.getMessage());
            responseVo.setData(new HashMap<>());
            return responseVo;
        }
    }

}
