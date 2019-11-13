package com.eason.transfer.openapi.core.client.user.model;


import com.eason.transfer.openapi.core.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest extends Request {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
