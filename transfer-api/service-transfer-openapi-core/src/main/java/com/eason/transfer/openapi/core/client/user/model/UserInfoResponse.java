package com.eason.transfer.openapi.core.client.user.model;

import com.eason.transfer.openapi.core.api.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("userInfoResponse")
public class UserInfoResponse extends Response {

    private static final long serialVersionUID = 1L;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserInfoResponse [result=" + result + "]";
    }

}