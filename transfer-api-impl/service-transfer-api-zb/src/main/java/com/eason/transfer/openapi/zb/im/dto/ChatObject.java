package com.eason.transfer.openapi.zb.im.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatObject implements Serializable {

    private String userName;
    private String message;

}
