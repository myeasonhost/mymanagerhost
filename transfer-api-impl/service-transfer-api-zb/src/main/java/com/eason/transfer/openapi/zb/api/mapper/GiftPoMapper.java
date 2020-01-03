package com.eason.transfer.openapi.zb.api.mapper;

import com.eason.transfer.openapi.zb.api.entity.GiftPo;
import com.eason.transfer.openapi.zb.api.entity.GiftPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftPoMapper {
    long countByExample(GiftPoExample example);

    int deleteByExample(GiftPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GiftPo record);

    int insertSelective(GiftPo record);

    List<GiftPo> selectByExample(GiftPoExample example);

    GiftPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GiftPo record, @Param("example") GiftPoExample example);

    int updateByExample(@Param("record") GiftPo record, @Param("example") GiftPoExample example);

    int updateByPrimaryKeySelective(GiftPo record);

    int updateByPrimaryKey(GiftPo record);

    GiftPo selectByGiftName(String giftName);
}