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
public class ResourceVo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3452102188155127633L;

    private String id;

    private String title;

    private String href;

    private String parentId;

    private String code;
}
