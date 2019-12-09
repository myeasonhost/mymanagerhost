package com.eason.transfer.openapi.core.system.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CmsUserrole entity.
 */
@Entity
@Table(name = "t_sys_userrole")
public class UserRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4671512379193965667L;
	private String id;
	private String userId;
	private String roleId;

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** full constructor */
	public UserRole(String id, String userId, String roleId) {
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "userId", nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "roleId", nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}