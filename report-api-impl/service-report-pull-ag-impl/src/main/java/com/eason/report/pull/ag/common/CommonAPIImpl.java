package com.eason.report.pull.ag.common;

import com.eason.report.pull.ag.ICommonAPI;
import com.eason.report.pull.ag.vo.common.SumordersDaysVo;
import com.eason.report.pull.ag.vo.common.SumordersDaysXinVo;
import com.eason.report.pull.ag.vo.common.SumordersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author EASON LI
 */
public class CommonAPIImpl implements ICommonAPI {
    private static Logger _log = LoggerFactory.getLogger(CommonAPIImpl.class);

    // 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getsumorders(SumordersVo vo) {
//        restTemplate.getForEntity()
        return null;
    }

    @Override
    public String getsumorders_days(SumordersDaysVo vo) {
        return null;
    }

    @Override
    public String getsumorders_days_xin(SumordersDaysXinVo vo) {
        return null;
    }
}
