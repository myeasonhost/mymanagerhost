package com.eason.transfer.openapi.core.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 472232414742295048L;
	private String id;
	private String code;
	private String title;
	/**
	 * 复选框选中状态，用于展现
	 */
	private boolean ck = false;
}
