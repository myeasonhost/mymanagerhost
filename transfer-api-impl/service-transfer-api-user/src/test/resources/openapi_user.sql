INSERT  INTO `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) VALUES
(25,1,'user.info.getDetail','用户详情','用户详情',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);


INSERT  INTO `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) VALUES
(25,25,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.user.IUserService;getDetail;com.eason.transfer.openapi.core.sdk.user.model.UserDetailRequest',0,NULL,NULL,NULL,NULL);

INSERT  INTO `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) VALUES
(79,23,'nickname','java.lang.String',1,NULL,NULL,'昵称',1,NULL,'2019-12-03 19:33:15');

INSERT  INTO `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) VALUES
(80,23,'sex','java.lang.String',1,NULL,NULL,'性别',1,NULL,'2019-12-03 19:33:15');

INSERT  INTO `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) VALUES
(81,23,'birthday','java.lang.String',1,NULL,NULL,'出生日期',1,NULL,'2019-12-03 19:33:15');