package com.eason.transfer.openapi.zb.api.zhubo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RUser implements Serializable {
    private String id;
    private String username;
    private String nickName;
    private String avatar;
    private String sessionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RUser rUser = (RUser) o;
        return username.equals(rUser.username) &&
                sessionId.equals(rUser.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, sessionId);
    }
}
