package com.eason.transfer.openapi.core.api.listener;

import com.eason.transfer.openapi.core.api.utils.OpenApiCommonConst;
import com.eason.transfer.openapi.core.api.dao.model.ApiMethodInfo;
import com.eason.transfer.openapi.core.api.dao.model.AppInfo;
import com.eason.transfer.openapi.core.api.service.LogOperatorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class RunnerListener implements CommandLineRunner {

    @Autowired
    private LogOperatorServiceImpl logOperatorServiceImpl;

    @Override
    public void run(String... args) {
        List<ApiMethodInfo> apiMethodInfoList = logOperatorServiceImpl.getApiMethodInfoList();
        for (ApiMethodInfo apiMethodInfo : apiMethodInfoList) {
            String key = apiMethodInfo.getMethod() + "_" + apiMethodInfo.getMethodVer();
            OpenApiCommonConst.allMethodInfoMap.put(key, apiMethodInfo);
        }
        // 加载app信息
        List<AppInfo> appInfoList = logOperatorServiceImpl.getAppinfoList();
        for (AppInfo appInfo : appInfoList) {
            OpenApiCommonConst.allAppInfoMap.put(appInfo.getAppKey(), appInfo);
        }

        log.info("APP加载完成=" + OpenApiCommonConst.allAppInfoMap);
    }

}
