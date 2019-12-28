package com.eason.transfer.openapi.core.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2255641424466390767L;
	/**
	 * 节点ID
	 */
	private String id;

	/**
	 * 节点文本
	 */
	private String text;

	/**
	 * 节点图标
	 */
	private String iconCls;

	/**
	 * 指示节点是否处于选中状态
	 */
	private boolean checked = false;

	/**
	 * 节点的初始化状态(关闭或展开)
	 */
	private String state="closed";

	/**
	 * 自定义属性
	 */
	private AttributesModel attributes;

	/**
	 * 子节点
	 */
	private List<TreeModel> children;

}
