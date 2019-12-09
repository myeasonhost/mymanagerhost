package com.eason.transfer.openapi.core.system.entity.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 所有VO，一定要有空构造
 * @author eason
 *
 */
public class BaseVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 978645466946702814L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
