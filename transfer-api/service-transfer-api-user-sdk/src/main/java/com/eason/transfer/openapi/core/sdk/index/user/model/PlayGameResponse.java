package com.eason.transfer.openapi.core.sdk.index.user.model;


import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("PlayGameResponse")
public class PlayGameResponse extends Response {

    private static final long serialVersionUID = 1L;

    private String result;
    private String status;

}
