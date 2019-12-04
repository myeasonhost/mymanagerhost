package com.eason.transfer.openapi.user.api.app.dao.entity;

import java.util.Date;

public class UserDeviceInfoPo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6069050200214179402L;
	private int id;	//主键
	private int userId;	//主键
	private String name;	//varchar(50) NOT NULL设备名称
	private String lastLoginTime;	//datetime NOT NULL最后一次登录时间
	private String registerId;		//设备推送id
	private String equipmentId;		//varchar(100) NULL设备唯一标识
	private int time;	//timeint(11) NOT NULL登录次数
	private String osversion;	//varchar(100) NULL系统版本号
	private Date createTime;		//datetime NOT NULL创建时间
	private Date updateTime;		//datetime NOT NULL更新时间
	private int isDeleted;	//tinyint(4) NOT NULL0：未删除 1：已删除
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
