package com.eason.transfer.openapi.core.client.img.model;


import com.eason.transfer.openapi.core.api.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgRequest extends Request {

    private static final long serialVersionUID = 1L;

    private String type;

}
