package com.ruoyi.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.pay.domain.TronFish;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 鱼苗管理Mapper接口
 *
 * @author eason
 * @date 2022-04-20
 */
public interface TronFishMapper extends BaseMapper<TronFish> {
    @Select({"${sql}"})
    String executeQuery(@Param("sql") String sql);
}
