package com.ds.money.dao.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ds.money.entity.CheckEntity;
import com.ds.money.entity.DsMemberMoney;
import com.ds.money.entity.DsMemberMoneyLog;
import com.ds.money.entity.MemberEntity;
import com.ds.money.vo.MoneyLogByDateParam;
import com.ds.money.vo.MoneyLogByDateVo;
import com.ds.money.vo.MoneyLogParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
import java.util.List;

//import com.ds.money.entity.DsMemberMoney;

public interface DsMoneyDao {

//	DsMemberMoney selectMoneyForUpdate(@Param("id")Long id);

	List<DsMemberMoneyLog> findMoneyLogByPage(@Param("offset") int offset, @Param("limit") int limit,
                                              @Param("param") MoneyLogParam queryParam);

	Long countMoneyLogByPage(@Param("param") MoneyLogParam queryParam);

	Double fromKeyTypeSum(@Param("type") Integer type, @Param("param") MoneyLogParam queryParam);

	List<DsMemberMoneyLog> findMoneyLogByPage(RowBounds rowBounds,
                                              @Param("param") MoneyLogParam queryParam);

	List<MoneyLogByDateVo> moneyLogByDate(@Param("param") MoneyLogByDateParam queryParam);

	List<DsMemberMoneyLog> findMoneyLogAgentLevelByPage(RowBounds rowBounds,
                                                        @Param("param") MoneyLogParam queryParam);

	Long countMoneyLogAgentLevelByPage(@Param("param") MoneyLogParam queryParam);

	public void updateUserState(@Param("agentLevel") String agentLevel, @Param("agentName") String agentName, @Param("state") Integer state,
                                @Param("siteId") Integer siteId);

	BigDecimal getTotalBalanceByState(@Param("param") JSONObject obj, @Param("state") int state);

	List<DsMemberMoney> selectUserMoney(@Param("username") String username, @Param("siteId") Integer siteId,
                                        @Param("tableName") String tableName);

	void insert(@Param("param") DsMemberMoney memberMoney, @Param("tableName") String tableName);

	int updateMoneyByVersion(@Param("param") DsMemberMoney money, @Param("version") Integer version,
                             @Param("tableName") String tableName);

	void insertCheck(@Param("siteId") Integer siteId, @Param("check") CheckEntity check, @Param("tableName") String tableName);

	List<CheckEntity> findCheckByTransId(@Param("siteId") Integer siteId, @Param("transId") String transId, @Param("tableName") String tableName);

	List<DsMemberMoneyLog> getMoneyLogByNo(@Param("username") String username, @Param("siteId") Integer siteId, @Param("remitno") String remitno);

	void updateMoneyLogMemo(@Param("siteId") Integer siteId, @Param("id") Long id, @Param("memo") String memo);

	void insertMember(@Param("member") MemberEntity member);

	void updateMember(@Param("member") MemberEntity member);

	List<MemberEntity> getUserByName(@Param("siteId") Integer siteId, @Param("username") String username);

	List<MemberEntity> getMemberInfo(@Param("siteId") Integer siteId, @Param("userLevel") String userLevel, @Param("username") String username,
                                     @Param("agentLevel") String agentLevel, @Param("agentName") String agentName);
	
}
