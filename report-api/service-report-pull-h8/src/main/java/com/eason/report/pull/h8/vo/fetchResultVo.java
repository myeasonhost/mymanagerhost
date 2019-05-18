package com.eason.report.pull.h8.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fetchResultVo implements Serializable {
    private String secret;
    private String agent;
    private String action;
    private String lang;

}
