package com.eason.transfer.openapi.core.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceRoleVo {
    /**
     * 资源Id
     */
    private String id;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 资源代码
     */
    private String code;

    /**
     * 标题
     */
    private String title;

    /**
     * 资源位置
     */
    private String href;

    /**
     * 资源被使用
     * @return
     */
    private boolean isChecked = false;
}
