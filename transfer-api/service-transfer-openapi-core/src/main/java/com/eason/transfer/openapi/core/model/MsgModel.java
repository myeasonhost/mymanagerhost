package com.eason.transfer.openapi.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MsgModel implements Serializable {
    private String type;
    private String message;
    private Model model;
}
