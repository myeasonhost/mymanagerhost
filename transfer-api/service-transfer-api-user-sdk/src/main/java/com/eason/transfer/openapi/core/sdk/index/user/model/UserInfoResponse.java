package com.eason.transfer.openapi.core.sdk.index.user.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("UserInfoResponse")
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
