package com.eason.transfer.openapi.core.api.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XStreamAlias("response")
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 成功记录数 */
    private Integer successCount = 1;

    /**
     * 错误数
     */
    private Integer errorCount = 0;

    /**
     * 错误信息列表
     */
    private List<ErrDetailInfo> errInfoList = null;

    /**
     * 异常错误信息
     */
    private String exception = null;

    /**
     * 累积添加错误信息，同时增长errorCount值
     *
     * @param response  返回对象
     * @param errorCode 错误编码
     * @param errorDes  错误描述
     * @param pkInfo    关键词
     */
    public void addErrInfo(String errorCode, String errorDes, String pkInfo) {

        if (errInfoList == null) {
            errInfoList = new ArrayList<ErrDetailInfo>();
        }

        ErrDetailInfo detailInfo = new ErrDetailInfo(errorCode, errorDes,
                pkInfo);
        errInfoList.add(detailInfo);

        // 设置错误个数
        errorCount = errInfoList.size();

        successCount = 0;
    }

    public void setException(Exception e) {
        try {
            if (e != null) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                exception = str;
            }
        } catch (Exception ex) {
        }
    }

}
