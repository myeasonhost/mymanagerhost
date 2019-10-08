package com.eason.transfer.openapi.core.mongo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Document(collection = "app_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppInfoPo extends BasePo {
    @Id
    private String tid;
    private String appName;
    private String appImage;
    private String appKey;
    private String appSecret;
    private String appUserTable;
    private Date createTime;
    private Date updateTime;
    private Integer createBy;
    private Integer updateBy;
    private String lowestVersion;
    private Byte isDeleted;
    private String lastVersion;
    private String content;

}
