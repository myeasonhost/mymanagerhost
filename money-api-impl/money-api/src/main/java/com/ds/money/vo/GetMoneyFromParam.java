package com.ds.money.vo;

import java.io.Serializable;
import java.util.List;

public class GetMoneyFromParam implements Serializable{

	private static final long serialVersionUID = 8429647066723262127L;

	private List<MemberMoney> memberMoneyList;

	public List<MemberMoney> getMemberMoneyList() {
		return memberMoneyList;
	}

	public void setMemberMoneyList(List<MemberMoney> memberMoneyList) {
		this.memberMoneyList = memberMoneyList;
	}
	
	

}
