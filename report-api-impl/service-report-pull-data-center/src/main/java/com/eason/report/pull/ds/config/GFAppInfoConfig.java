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
public class GFAppInfoConfig implements Serializable {
    private String user;    //888821
    private Integer level;  //4
    private String pullUrl; //http://180.150.154.116/gfrecordapi/getRecord
    private Integer length; //1000
    private Map<Integer,String> siteId;  //TYZ_1020,MHD_1040,MAA_1070,MAB_1080
}
