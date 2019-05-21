package com.eason.report.pull.ds.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MsgModel implements Serializable {
    private String code;
    private String message;
    private Object object;
}
