package com.eason.transfer.openapi.core.api.impl;

import com.eason.transfer.openapi.core.api.OpenApiVisitRouter;
import com.eason.transfer.openapi.core.api.dao.mapper.OoApiAccessLogMapper;
import com.eason.transfer.openapi.core.api.dao.mapper.OoUserTokenInfoMapper;
import com.eason.transfer.openapi.core.api.dao.model.ApiMethodInfo;
import com.eason.transfer.openapi.core.api.dao.model.OoApiAccessLogModel;
import com.eason.transfer.openapi.core.api.dao.model.UserTokenInfo;
import com.eason.transfer.openapi.core.api.exception.OpenApiBaseException;
import com.eason.transfer.openapi.core.api.filter.OpenApiSystemParamFilterBean;
import com.eason.transfer.openapi.core.api.utils.MessageUtils;
import com.eason.transfer.openapi.core.api.utils.OpenApiCommonConst;
import com.eason.transfer.openapi.core.api.utils.OpenApiCommonConst.ERROR_MSG;
import com.eason.transfer.openapi.core.api.utils.OpenApiCommonUtil;
import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.core.common.response.ErrDetailInfo;
import com.eason.transfer.openapi.core.common.response.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;

@Service
public class OpenApiVisitRouterImpl implements OpenApiVisitRouter {


    private final static Log log = LogFactory.getLog(OpenApiVisitRouterImpl.class);
    /**
     * ?????????????????????
     */
    @Autowired
    private OpenApiSystemParamFilterBean openApiSystemParamFilterBean;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private OoApiAccessLogMapper ooApiAccessLogMapper;
    @Autowired
    private OoUserTokenInfoMapper ooUserTokenInfoMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * ??????ws?????????????????????????????????
     *
     * @param multivaluedMap
     */
    public String router(MultivaluedMap<String, String> multivaluedMap, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");

        // ????????????????????????
        Long startTime = System.currentTimeMillis();
        // ??????????????????
        int language = 1;
        try {
            if (multivaluedMap.get("language") != null) {
                List<String> languages = multivaluedMap.get("language");
                if (languages.get(0) != null) {
                    language = Integer.parseInt(languages.get(0));
                }
            }
        } catch (Exception e) {
            language = 1;
        }

        // ??????????????????paramMap???
        Map<String, String> paramMap = new HashMap<String, String>();
        Iterator<Entry<String, List<String>>> entryIterator = multivaluedMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Entry<String, List<String>> entry = entryIterator.next();
            String key = entry.getKey();
            paramMap.put(key, multivaluedMap.getFirst(key));
        }

        //format??????
        String format = OpenApiCommonConst.FORMAT_JSON;
        if (paramMap.containsKey(OpenApiCommonConst.STRING_FORMAT)) {
            format = paramMap.get(OpenApiCommonConst.STRING_FORMAT);
        }
        String method = multivaluedMap.getFirst(OpenApiCommonConst.STRING_METHOD);
        //??????https???????????????
        String httpsMethod = MessageUtils.getMessage(messageSource, OpenApiCommonConst.HTTPS_METHOD, null, language);
        //????????????HTTPS??????
        if (StringUtils.isNotBlank(method) && httpsMethod.indexOf(method + ",") != -1) {
            if (!httpRequest.isSecure()) {
                log.error("???????????????https??????????????????");
                String responseReslut="???????????????https???????????????";
                //??????????????????
//                Response response = new Response();
//                String result = REQUEST_HTTPS.ERROR_399;
//                String msg = MessageUtils.getMessage(messageSource, REQUEST_HTTPS.METHOD + result, null, language);
//                response.addErrInfo(result, msg, null);
//
//                //String responseReslut = JSONObject.fromObject(response).toString();
//                String responseReslut = OpenApiCommonUtil.getResponseInfo(response, format);
                return responseReslut;
            } else {
                log.info("??????https??????");
            }
        }


        // ??????ws
        Response response = invokeRouter(paramMap, null, httpRequest, language);

        //??????log??????
//        insertIntoLog(paramMap, httpRequest, response, startTime);

        //??????response??????
        String responseReslut = OpenApiCommonUtil.getResponseInfo(response, format);

        return responseReslut;


    }


    public String routerWithFile(MultipartBody body, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");

        // ????????????????????????
        Long startTime = System.currentTimeMillis();

        //??????Map
        Map<String, String> paramMap = new HashMap<String, String>();
        //??????list
        Map<String, FileItem> fileItemMap = new HashMap<String, FileItem>();

        //???????????????????????????
        OpenApiCommonUtil.parseParamAttachment(body, paramMap, fileItemMap);

        // ??????????????????
        int language = 1;
        try {
            language = Integer.parseInt(paramMap.get("language"));
        } catch (Exception e) {
            language = 1;
        }
        String method = paramMap.get(OpenApiCommonConst.STRING_METHOD);

        //format??????
        String format = OpenApiCommonConst.FORMAT_JSON;
        if (paramMap.containsKey(OpenApiCommonConst.STRING_FORMAT)) {
            format = paramMap.get(OpenApiCommonConst.STRING_FORMAT);
        }
        //??????https???????????????
        String httpsMethod = MessageUtils.getMessage(messageSource, OpenApiCommonConst.HTTPS_METHOD, null, language);
        //????????????HTTPS??????
        if (StringUtils.isNotBlank(method) && httpsMethod.indexOf(method + ",") != -1) {
            if (!httpRequest.isSecure()) {
                log.error("???????????????https??????????????????");
                String responseReslut="???????????????https???????????????";
                //??????????????????
//                Response response = new Response();
//                String result = REQUEST_HTTPS.ERROR_399;
//                String msg = MessageUtils.getMessage(messageSource, REQUEST_HTTPS.METHOD + result, null, language);
//                response.addErrInfo(result, msg, null);
//
//                //String responseReslut = JSONObject.fromObject(response).toString();
//                String responseReslut = OpenApiCommonUtil.getResponseInfo(response, format);
                return responseReslut;
            } else {
                log.info("??????https??????");
            }
        }


        // ??????ws
        Response response = invokeRouter(paramMap, fileItemMap, httpRequest, language);
        //??????log??????
//        insertIntoLog(paramMap, httpRequest, response, startTime);

        //??????response??????
        String responseReslut = OpenApiCommonUtil.getResponseInfo(response, format);

        return responseReslut;

    }


    private Response invokeRouter(Map<String, String> paramMap, Map<String, FileItem> fileItemMap, HttpServletRequest request, int language) {
        // ?????????????????????
        Response response = openApiSystemParamFilterBean.checkSystemParam(paramMap, request, 0, language);
        // ??????sessionKey???????????????sign????????????
        if (response != null && response.getErrInfoList() != null && response.getErrInfoList().size() > 0) {
            for (ErrDetailInfo item : response.getErrInfoList()) {
                if (StringUtils.isNotBlank(item.getErrorDes()) && item.getErrorDes().equals("????????????sessionKey???????????????")) {
                    item.setErrorDes("????????????????????????????????????????????????");
                }
                if (StringUtils.isNotBlank(item.getErrorDes()) && item.getErrorDes().indexOf("????????????sign") != -1) {
                    item.setErrorDes("?????????????????????");
                }
            }
        }
        if (response != null) {
            return response;
        }

        // ??????ws
        response = invokeService(paramMap, fileItemMap, request, language);

        return response;

    }


    private void insertIntoLog(Map<String, String> paramMap, HttpServletRequest request, Response response, Long startTime) {
        try {
            Long endTime = System.currentTimeMillis();
            Integer cost = (int) (endTime - startTime);
            if (paramMap == null) {
                return;
            }
            /** IP */
            String ip = OpenApiCommonUtil.getRemoteIp(request);
            /** ???????????? */
            String deviceManufacturer = paramMap.get("deviceManufacturer");
            /** ???????????? */
            String deviceModel = paramMap.get("deviceModel");
            /** ??????????????? */
            String systemVer = paramMap.get("systemVer");
            /** app????????? */
            String appVer = paramMap.get("appVer");

            String resultCode = null;
            Integer resultType = 1;

            if (response.getErrInfoList() != null && response.getErrInfoList().size() > 0) {
                //???????????????,??????????????????
                resultType = 3;
                for (ErrDetailInfo errDetailInfo : response.getErrInfoList()) {
                    if (StringUtils.isNotBlank(errDetailInfo.getErrorCode())) {
                        if (errDetailInfo.getErrorCode().startsWith(ERROR_MSG.SYSTEM_ERROR) ||
                                errDetailInfo.getErrorCode().startsWith(ERROR_MSG.HEDWIG_SERVICE_ERROR)) {
                            resultType = 2;
                            resultCode = errDetailInfo.getErrorCode();
                            break;
                        } else {
                            resultCode = errDetailInfo.getErrorCode();
                        }
                    }
                }
            }

            Integer userId = null;
            if (StringUtils.isNotBlank(paramMap.get("sessionKey"))) {
                UserTokenInfo token = ooUserTokenInfoMapper.getUserTokenInfoByToken(paramMap.get("sessionKey"));
                if (token != null) {
                    userId = Integer.parseInt(token.getUserId());
                } else {
                    //??????????????????token
                    if (stringRedisTemplate.boundValueOps(paramMap.get("sessionKey")).get() != null) {
                        try {
//						userId = (Integer)memCachedClient.get(paramMap.get("sessionKey"));
                            userId = Integer.parseInt(stringRedisTemplate.boundValueOps(paramMap.get("sessionKey")).get());
                        } catch (Exception e) {
                            log.error(stringRedisTemplate.boundValueOps(paramMap.get("sessionKey")).get().getClass().getName());
                            log.error("??????????????????,Key=[" + paramMap.get("sessionKey") + "], value =[" + stringRedisTemplate.boundValueOps(paramMap.get("sessionKey")).get() + "]", e);
                            stringRedisTemplate.delete(paramMap.get("sessionKey"));
                        }
                    }
                }
            }
            OoApiAccessLogModel accessLog = new OoApiAccessLogModel();
            accessLog.setAppVer(appVer);
            accessLog.setDeviceManufacturer(deviceManufacturer);
            accessLog.setDeviceModel(deviceModel);
            accessLog.setInvokeMethod(paramMap.get("method"));
            String param = paramMap.toString();
            if (StringUtils.isNotBlank(param) && param.length() > 3000) {
                param = param.substring(0, 3000);
            }

            accessLog.setInvokeParam(param);
            accessLog.setIp(ip);
            accessLog.setMethodVer(paramMap.get("ver"));
            accessLog.setResultCode(resultCode);
            accessLog.setResultType(resultType);
            accessLog.setSystemVer(systemVer);
            accessLog.setUserId(userId);
            accessLog.setVisitDate(new Date());
            accessLog.setVisitTimeCost(cost);
            accessLog.setResultMsg(response.toString());
            if (StringUtils.isNotBlank(response.getException())) {
                if (response.getException().length() > 4000) {
                    accessLog.setException(response.getException().substring(0, 4000));
                } else {
                    accessLog.setException(response.getException());
                }
            }
            ooApiAccessLogMapper.insertModel(accessLog);
        } catch (Exception e) {
            log.error("??????????????????", e);
        }

    }

    /**
     * ??????ws??????
     *
     * @param paramMap
     * @param request
     * @return
     */
    private Response invokeService(Map<String, String> paramMap, Map<String, FileItem> fileItemMap, HttpServletRequest request, int language) {

        //?????????????????????
        String ver = paramMap.get(OpenApiCommonConst.STRING_VER);
        //????????????????????????
        String merthod = paramMap.get(OpenApiCommonConst.STRING_METHOD);

        // ?????????????????????
        Response response = null;

        //URL ??????????????????key
        String key = merthod + "_" + ver;
        ApiMethodInfo apiMethodInfo = OpenApiCommonConst.allMethodInfoMap.get(key);

        String redirectValue = OpenApiCommonUtil.stringTrim(apiMethodInfo.getMethodCfg());

        if (StringUtils.isEmpty(redirectValue)) {
            return OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.EDIRECT_ERROR, null, null, messageSource, language);
        }

        try {
            String[] hedwigBeanValues = redirectValue.split(";");

            String prex = hedwigBeanValues[0];
            if ("local".equals(prex)) {
                if (hedwigBeanValues.length != 4 || StringUtils.isEmpty(hedwigBeanValues[1])
                        || StringUtils.isEmpty(hedwigBeanValues[2]) || StringUtils.isEmpty(hedwigBeanValues[3])) {
                    return OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.EDIRECT_ERROR, null, null, messageSource, language);
                }
                //??????service bean
                ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                Object serviceObj = context.getBean(OpenApiCommonUtil.stringTrim(hedwigBeanValues[1]));

                //??????request??????
                Class<?> hedwigRequest = Class.forName(OpenApiCommonUtil.stringTrim(hedwigBeanValues[3]));
                Object objRequest = hedwigRequest.newInstance();
                OpenApiCommonUtil.setBeanPropertyValue(objRequest, paramMap, fileItemMap);

                String methodName = OpenApiCommonUtil.stringTrim(hedwigBeanValues[2]);

                try {
                    response = (Response) serviceObj.getClass().getMethod(methodName, new Class[]{objRequest.getClass()}).invoke(serviceObj, new Object[]{objRequest});
                } catch (InvocationTargetException baseException) {
                    baseException.printStackTrace();
                    if(baseException.getTargetException() instanceof OpenApiBaseException){
                        OpenApiBaseException e = (OpenApiBaseException) baseException.getTargetException();
                        if (response == null) {
                            response = new Response();
                        }
                        response.addErrInfo(e.getErrorCode(), e.getErrorMsg(), e.getPkinfo());
                    }
                    response = OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.METHOD_ERROR, new String[]{baseException.getTargetException().getMessage()}, null, messageSource, language);
                    return response;
                } catch (Exception exception) {
                    log.error(exception);
                    response = OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.METHOD_ERROR, null, null, messageSource, language);
                    response.setException(exception);
                    return response;
                }

                if (response == null) {
                    log.error("????????????????????????");
                    String[] params = new String[]{"???????????????"};
                    return OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.HEDWIG_SERVICE_ERROR, params, redirectValue, messageSource, language);
                }
                //??????response??????
                return response;
            } else if ("remote".equals(prex)) {
                if (hedwigBeanValues.length != 4 || StringUtils.isEmpty(hedwigBeanValues[1])
                        || StringUtils.isEmpty(hedwigBeanValues[2]) || StringUtils.isEmpty(hedwigBeanValues[3])) {
                    return OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.EDIRECT_ERROR, null, null, messageSource, language);
                }

                //??????request??????
                Class<?> hedwigRequest = Class.forName(OpenApiCommonUtil.stringTrim(hedwigBeanValues[3]));
                Object objRequest = hedwigRequest.newInstance();
                OpenApiCommonUtil.setBeanPropertyValue(objRequest, paramMap, fileItemMap);

                //??????service bean
                ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                Object serviceObj = context.getBean(Class.forName(OpenApiCommonUtil.stringTrim(hedwigBeanValues[1])));

                String methodName = OpenApiCommonUtil.stringTrim(hedwigBeanValues[2]);
                try {
                    response = (Response) serviceObj.getClass().getMethod(methodName, new Class[]{objRequest.getClass()}).invoke(serviceObj, new Object[]{objRequest});
                } catch (InvocationTargetException baseException) {
                    baseException.printStackTrace();
                    if(baseException.getTargetException() instanceof OpenApiBaseException){
                        OpenApiBaseException e = (OpenApiBaseException) baseException.getTargetException();
                        if (response == null) {
                            response = new Response();
                        }
                        response.addErrInfo(e.getErrorCode(), e.getErrorMsg(), e.getPkinfo());
                    }
                    response = OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.HEDWIG_SERVICE_ERROR, new String[]{baseException.getTargetException().getMessage()}, null, messageSource, language);
                    return response;
                } catch (Exception exception) {
                    log.error(exception);
                    response = OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.METHOD_ERROR, new String[]{exception.getMessage()}, null, messageSource, language);
                    return response;
                }
                if (response == null) {
                    log.error("????????????????????????");
                    String[] params = new String[]{"???????????????"};
                    return OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.HEDWIG_SERVICE_ERROR, params, redirectValue, messageSource, language);
                }
                return response;
            }
        } catch (NoSuchBeanDefinitionException noSuchBeanException) {
            String[] params = new String[]{"?????????????????????"};
            noSuchBeanException.printStackTrace();
            return OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.HEDWIG_SERVICE_ERROR, params, null, messageSource, language);
        } catch (Exception exception) {
            exception.printStackTrace();
            return OpenApiCommonUtil.setResponseObjByError(ERROR_MSG.SYSTEM_ERROR, null, null, messageSource, language);
        }
        return response;
    }


    public OpenApiSystemParamFilterBean getOpenApiSystemParamFilterBean() {
        return openApiSystemParamFilterBean;
    }


    public void setOpenApiSystemParamFilterBean(
            OpenApiSystemParamFilterBean openApiSystemParamFilterBean) {
        this.openApiSystemParamFilterBean = openApiSystemParamFilterBean;
    }

}
