package com.eason.transfer.openapi.core.common.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XStreamAlias("errDetailInfo")
public class ErrDetailInfo implements Serializable {

    private static final long serialVersionUID = 4049522523279414519L;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorDes;

    /**
     * 主键信息
     */
    private String pkInfo;

}
