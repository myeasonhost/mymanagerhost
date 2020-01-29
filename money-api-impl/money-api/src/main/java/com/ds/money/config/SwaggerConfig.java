//package com.ds.money.config;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @Author: jerry
// * @Description:
// * @Date: Create in 2018-06-15 13:06
// */
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Value("${swagger.enable}")
//    private boolean enableSwagger;
//    @Bean
//    public Docket myDocket() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2);
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("钱包API接口文档")
//                .description("This is to show api description")
//                .license("Apache 2.0")
//                .contact(new Contact("", "", ""))
//                .version("1.0")
//                .build();
//        docket.apiInfo(apiInfo);
//        docket.enable(enableSwagger);
//        //设置只生成被Api这个注解注解过的Ctrl类中有ApiOperation注解的api接口的文档
//       // docket.select().apis(RequestHandlerSelectors.basePackage("com.ds.money.controller")).build();
//        docket.select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).
//                apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
//        return docket;
//    }
//
//}
