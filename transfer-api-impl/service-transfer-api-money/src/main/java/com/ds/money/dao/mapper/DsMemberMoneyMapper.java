package com.ds.money.dao.mapper;

import com.ds.money.entity.DsMemberMoney;
import com.ds.money.entity.DsMemberMoneyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DsMemberMoneyMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int countByExample(DsMemberMoneyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int deleteByExample(DsMemberMoneyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int insert(DsMemberMoney record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int insertSelective(DsMemberMoney record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	List<DsMemberMoney> selectByExample(DsMemberMoneyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	DsMemberMoney selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int updateByExampleSelective(@Param("record") DsMemberMoney record,
                                 @Param("example") DsMemberMoneyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int updateByExample(@Param("record") DsMemberMoney record,
                        @Param("example") DsMemberMoneyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int updateByPrimaryKeySelective(DsMemberMoney record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ds_member_money
	 * @mbggenerated  Wed Aug 12 03:06:15 EDT 2015
	 */
	int updateByPrimaryKey(DsMemberMoney record);
}