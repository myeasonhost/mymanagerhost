package com.eason.report.pull.core.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Map;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MGAppInfoConfig implements Serializable {
    private Long agentId;
    private String username;
    private String password;
    private String pullUrl;
    private String prex;
    private Integer length;
    private String  MG_WEBSITE_URL;
    private Map<Integer,String> siteId;  //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
}
