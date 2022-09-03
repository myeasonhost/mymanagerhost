package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.model.FileItem;
import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("UploadPayImageRequest")
public class UploadPayImageRequest extends Request {
    private String orderId ;
    private FileItem payImage;
    private String remark;
}
