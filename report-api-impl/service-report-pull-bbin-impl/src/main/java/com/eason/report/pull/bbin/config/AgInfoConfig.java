package com.eason.report.pull.ds.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgInfoConfig {
    private String pidtoken;
    private String pullUrl;
}
