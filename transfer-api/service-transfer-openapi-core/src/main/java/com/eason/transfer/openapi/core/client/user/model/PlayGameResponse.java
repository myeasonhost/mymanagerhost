package com.eason.transfer.openapi.core.client.user.model;


import com.eason.transfer.openapi.core.api.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("playGameResponse")
public class PlayGameResponse extends Response {

    private static final long serialVersionUID = 1L;

    private String message;
    private String status;

}
