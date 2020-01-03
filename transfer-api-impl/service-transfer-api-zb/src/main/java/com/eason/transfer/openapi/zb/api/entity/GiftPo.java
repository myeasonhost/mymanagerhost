package com.eason.transfer.openapi.zb.api.entity;

import java.math.BigDecimal;

public class GiftPo {
    private Integer id;

    private String giftName;

    private String giftLmg;

    private BigDecimal giftPrice;

    private String specialstyle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftLmg() {
        return giftLmg;
    }

    public void setGiftLmg(String giftLmg) {
        this.giftLmg = giftLmg;
    }

    public BigDecimal getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(BigDecimal giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getSpecialstyle() {
        return specialstyle;
    }

    public void setSpecialstyle(String specialstyle) {
        this.specialstyle = specialstyle;
    }
}