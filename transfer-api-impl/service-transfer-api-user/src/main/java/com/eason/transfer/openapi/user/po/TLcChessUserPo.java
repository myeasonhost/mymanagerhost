package com.eason.transfer.openapi.user.po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_lc_chess_user")
public class TLcChessUserPo implements Serializable {
    private int userId;
    private String account;
    private String chessAccount;
    private String siteId;
    private Byte status;
    private String loginIp;
    private Timestamp createTime;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "chess_account")
    public String getChessAccount() {
        return chessAccount;
    }

    public void setChessAccount(String chessAccount) {
        this.chessAccount = chessAccount;
    }

    @Basic
    @Column(name = "site_id")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "login_ip")
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TLcChessUserPo that = (TLcChessUserPo) o;
        return userId == that.userId &&
                Objects.equals(account, that.account) &&
                Objects.equals(chessAccount, that.chessAccount) &&
                Objects.equals(siteId, that.siteId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(loginIp, that.loginIp) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, account, chessAccount, siteId, status, loginIp, createTime);
    }
}
