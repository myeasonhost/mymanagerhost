package com.ds.money.entity;

public class CheckEntity {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ds_check_id.id
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ds_check_id.trans_id
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	private String transId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ds_check_id.status
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	private String status;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ds_check_id.id
	 * @return  the value of ds_check_id.id
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ds_check_id.id
	 * @param id  the value for ds_check_id.id
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ds_check_id.trans_id
	 * @return  the value of ds_check_id.trans_id
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	public String getTransId() {
		return transId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ds_check_id.trans_id
	 * @param transId  the value for ds_check_id.trans_id
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	public void setTransId(String transId) {
		this.transId = transId == null ? null : transId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ds_check_id.status
	 * @return  the value of ds_check_id.status
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ds_check_id.status
	 * @param status  the value for ds_check_id.status
	 * @mbggenerated  Fri Sep 18 08:58:16 EDT 2015
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
}