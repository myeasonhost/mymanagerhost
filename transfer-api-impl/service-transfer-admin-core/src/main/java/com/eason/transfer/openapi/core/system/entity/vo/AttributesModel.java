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
public class AttributesModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3452102188155127633L;

    /**
     * 资源路径
     */
    private String href;

    /**
     *资源父节点
     */
    private String parentId;

    /**
     * 资源编码
     */
    private String code;
}
