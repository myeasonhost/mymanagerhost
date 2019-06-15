package com.eason.report.pull.core.config;

import com.eason.report.pull.core.base.BaseConfig;
import com.eason.report.pull.core.mongo.config.GeneratedValue;
import com.eason.report.pull.core.mongo.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;

@Document(collection = "ds_mg_game_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MGConfigMgoCo extends BaseConfig {
    @Id
    @GeneratedValue
    private int id;
    private Integer agentId;
    private String username;
    private String password;
    private String prex;
    private String siteId;
    private Integer length;
    private String pullUrl;
    private Integer state;
    private String info;

    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createTime;
    private long upTime;
}
