package com.eason.report.pull.core.config;

import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GFAppInfoConfig implements Serializable {
    private List<DtLotteryConfigPo> gfListConfig=new ArrayList<>();
}

