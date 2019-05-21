package com.eason.report.pull.ds.config;

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
public class JDAppInfoConfig implements Serializable {
    private String user;
    private Integer level;
    private String pullUrl;
    private Integer length;
    private Map<String,String> siteId;
}
