package com.eason.transfer.openapi.core.action;

import com.eason.transfer.openapi.core.api.dao.mapper.OoApiMethodCategoryMapper;
import com.eason.transfer.openapi.core.api.dao.mapper.OoApiMethodCfgMapper;
import com.eason.transfer.openapi.core.api.dao.mapper.OoApiMethodMapper;
import com.eason.transfer.openapi.core.api.dao.mapper.OoApiMethodParamMapper;
import com.eason.transfer.openapi.core.api.dao.model.ApiSystemParam;
import com.eason.transfer.openapi.core.api.dao.model.OoApiMethodCategoryModel;
import com.eason.transfer.openapi.core.api.dao.model.OoApiMethodModel;
import com.eason.transfer.openapi.core.api.dao.model.OoApiMethodParamModel;
import com.eason.transfer.openapi.core.api.utils.OpenApiCommonConst;
import com.eason.transfer.openapi.core.api.utils.PostClient;
import com.eason.transfer.openapi.core.common.request.Request;
import com.eason.transfer.openapi.core.common.response.ErrDetailInfo;
import com.eason.transfer.openapi.core.common.response.Response;
import com.eason.transfer.openapi.core.utils.VerifyCodeUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiToolsAction {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OoApiMethodCategoryMapper ooApiMethodCategoryMapper;

    @Autowired
    private OoApiMethodMapper ooApiMethodMapper;

    @Autowired
    private OoApiMethodCfgMapper ooApiMethodCfgMapper;

    @Autowired
    private OoApiMethodParamMapper ooApiMethodParamMapper;

    @RequestMapping(value = "/api")
    public Response router(@Validated Request requestParams, BindingResult bindingResult, @RequestBody Map<String, Object> map) {
        List<ErrDetailInfo> errResultList = new ArrayList<>();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError err : errors) {
                ErrDetailInfo errDetailInfo = new ErrDetailInfo();
                errDetailInfo.setErrorDes(err.getDefaultMessage());
                errDetailInfo.setErrorCode(err.getCode());
                errDetailInfo.setPkInfo(err.getField());
                errResultList.add(errDetailInfo);
            }
        }
        if (!errResultList.isEmpty()) {
            return Response.builder().errorCount(errResultList.size()).errInfoList(errResultList).successCount(0).build();
        }
        System.out.println(requestParams);
        System.out.println(map);
//        System.out.println(req);

        return Response.builder().successCount(1).build();
    }

    @GetMapping(value = "/api/vcode")
    public void vcode(String code,HttpServletResponse response) {
        if(StringUtils.isEmpty(code)){
            return;
        }
        if(code.length()!=4){
            return;
        }
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //?????????????????????
//        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //??????????????????????????? 130??? 40???
        try{
            VerifyCodeUtil.outputImage(130, 40, response.getOutputStream(), code);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * ??????????????????????????????
     */
    @RequestMapping(value = "/apiTools")
    public ModelAndView apiTools() {
        return new ModelAndView("page/apiTools");
    }

    /**
     * ???????????????
     */
    @RequestMapping(value = "/apiToolsResult")
    public ModelAndView apiToolsResult() {
        return new ModelAndView("page/apiTestResult");
    }

    /**
     * ??????????????????????????????
     */
    @RequestMapping(value = "/queryMethodCategorys")
    public String queryMethodCategorys() throws Exception {
        try {
            OoApiMethodCategoryModel obj = new OoApiMethodCategoryModel();
            List<OoApiMethodCategoryModel> apiCategoryList = ooApiMethodCategoryMapper.getListByModel(obj);
            JSONArray jsonArray = JSONArray.fromObject(apiCategoryList);
            return jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * ??????????????????????????????
     */
    @RequestMapping(value = "/queryMethods")
    public String queryMethods(Integer methodType) throws Exception {
        try {
            //??????????????????ID???????????????????????????????????????
            OoApiMethodModel obj = new OoApiMethodModel();
            obj.setMethodType(methodType);
            List<OoApiMethodModel> apiMethodInfoList = ooApiMethodMapper.getListByModel(obj);
            JSONArray jsonArray = JSONArray.fromObject(apiMethodInfoList);
            return jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * ????????????ID??????????????????
     */
    @RequestMapping(value = "/queryMethodInParams")
    public String queryMethodInParams(Integer methodId) throws Exception {
        try {
            //????????????id??????????????????????????????
            OoApiMethodParamModel methodParamDto = new OoApiMethodParamModel();
            methodParamDto.setMethodId(methodId);
            methodParamDto.setParamClass(1);
            List<OoApiMethodParamModel> methodParamList = ooApiMethodParamMapper.getListByModel(methodParamDto);
            JSONArray jsonArray = JSONArray.fromObject(methodParamList);
            return jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * ??????????????????????????????
     */
    @RequestMapping(value = "/handleSubmitRequest")
    public ModelAndView handleSubmitRequest(@ModelAttribute ApiSystemParam systemParam,
                                            HttpServletRequest request) throws Exception {
        try {

            Map<String, String> appParamMap = new HashMap<String, String>();
            //????????????????????????????????????appKey???sessionKey???format???method
            if (!StringUtils.isEmpty(systemParam.getAppKey())) {
                appParamMap.put(OpenApiCommonConst.STRING_APPKEY, systemParam.getAppKey());
            }
            if (!StringUtils.isEmpty(systemParam.getSessionKey())) {
                appParamMap.put(OpenApiCommonConst.STRING_SESSIONKEY, systemParam.getSessionKey());
            }
            appParamMap.put(OpenApiCommonConst.STRING_FORMAT, systemParam.getFormat());
            appParamMap.put(OpenApiCommonConst.STRING_METHOD, systemParam.getMethod());
            appParamMap.put(OpenApiCommonConst.STRING_VER, systemParam.getVer());

            Map<String, String> paramMap = new HashMap<String, String>();
            //??????????????????????????????map???
            String paramNames[] = systemParam.getParamsNames().split(",");
            String paramTypes[] = systemParam.getParamsTypes().split(",");
            for (int i = 0; i < paramNames.length; i++) {
                //????????????????????????
                String tmpAppParams = request.getParameter(paramNames[i]);
                //????????????
                if (!StringUtils.isEmpty(tmpAppParams)) {
                    appParamMap.put(paramNames[i], tmpAppParams.trim());

                    if ("String".equals(paramTypes[i])) {
                        paramMap.put(paramNames[i], "\"" + tmpAppParams + "\"");
                    } else {
                        paramMap.put(paramNames[i], tmpAppParams);
                    }
                }
            }

            StringBuilder tmpPostParams = new StringBuilder();
            //?????????????????????????????????API??????
            String[] httpMethods = messageSource.getMessage("https_method", null, null).split(",");
            for (String item : httpMethods) {
                if (appParamMap.get(OpenApiCommonConst.STRING_METHOD).indexOf(item) != -1) {
                    systemParam.setPostUrl(systemParam.getPostUrl().replace("http", "https"));
                    systemParam.setPostUrl(systemParam.getPostUrl().replace("8080", "443"));
                }
            }
            // ??????request????????????request????????????
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            // ????????????map??????
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();


            String appResult = PostClient.sendRequest(systemParam.getPostUrl(), appParamMap, fileMap, systemParam.getAppSecret(), tmpPostParams);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(systemParam);
            modelAndView.addObject("appResult", appResult);
            modelAndView.addObject("postParams", tmpPostParams.toString());
            modelAndView.setViewName("page/apiTestResult");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

    }

}
