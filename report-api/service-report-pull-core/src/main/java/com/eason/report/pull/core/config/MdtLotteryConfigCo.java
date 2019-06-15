package com.eason.report.pull.core.config;

import com.eason.report.pull.core.base.BaseConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MdtLotteryConfigCo extends BaseConfig {
    private int id;
    private String code;
    private Integer liveId;
    private String liveName;
    private String gameKind;
    private String gameKindName;
    private String user;
    private String siteId;
    private Integer level;
    private String recordUrl;
    private Integer length;
    private Integer state;
    private String info;

}
