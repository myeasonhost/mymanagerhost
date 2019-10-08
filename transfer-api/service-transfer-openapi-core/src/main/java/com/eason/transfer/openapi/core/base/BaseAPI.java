package com.eason.transfer.openapi.core.base;

import com.eason.transfer.openapi.core.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class BaseAPI {

    public static final String SUCCESS = "0000";
    public static final String ERROR = "1111";

    public static final ResponseModel successModel = ResponseModel.builder().code(SUCCESS).massge("操作成功").build();
    public static final ResponseModel errorModel = ResponseModel.builder().code(ERROR).massge("操作失败 ").build();

}
