package com.eason.transfer.openapi.core.client.chess.dao.entity;

import java.util.Date;

public class TChessGameConfigPo {
    private Integer id;

    private String agentId;

    private String gameKind;

    private String gameKindName;

    private String siteId;

    private String deskey;

    private String md5key;

    private String walleturl;

    private String transferurl;

    private String pullurl;

    private Byte length;

    private Byte status;

    private Date initstartid;

    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getGameKind() {
        return gameKind;
    }

    public void setGameKind(String gameKind) {
        this.gameKind = gameKind;
    }

    public String getGameKindName() {
        return gameKindName;
    }

    public void setGameKindName(String gameKindName) {
        this.gameKindName = gameKindName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDeskey() {
        return deskey;
    }

    public void setDeskey(String deskey) {
        this.deskey = deskey;
    }

    public String getMd5key() {
        return md5key;
    }

    public void setMd5key(String md5key) {
        this.md5key = md5key;
    }

    public String getWalleturl() {
        return walleturl;
    }

    public void setWalleturl(String walleturl) {
        this.walleturl = walleturl;
    }

    public String getTransferurl() {
        return transferurl;
    }

    public void setTransferurl(String transferurl) {
        this.transferurl = transferurl;
    }

    public String getPullurl() {
        return pullurl;
    }

    public void setPullurl(String pullurl) {
        this.pullurl = pullurl;
    }

    public Byte getLength() {
        return length;
    }

    public void setLength(Byte length) {
        this.length = length;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getInitstartid() {
        return initstartid;
    }

    public void setInitstartid(Date initstartid) {
        this.initstartid = initstartid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}