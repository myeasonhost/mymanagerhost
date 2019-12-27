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
	 * 资源路径
	 */
	private String href;

	/**
	 * 子节点
	 */
	private List<TreeModel> children;

	public TreeModel(String id, String text, String href) {
		this.id = id;
		this.text = text;
		this.href = href;
	}
}
