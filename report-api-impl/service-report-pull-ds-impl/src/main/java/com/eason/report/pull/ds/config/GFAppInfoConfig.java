package com.eason.report.pull.ds.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GFAppInfoConfig implements Serializable {
    private String user;
    private Integer siteId;
    private Integer level;
    private String pullUrl;
    private Integer state;
    private Integer length;
}
