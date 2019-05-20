package com.eason.report.pull.sgs.xstreamDemo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("result")
public class AgList {
    @XStreamImplicit(itemFieldName="row")
    private List<AgModel> agList;
    @XStreamAlias("info")
    private String info;
    @XStreamAlias("addition")
    private String addition;

    public List<AgModel> getAgList() {
        return agList;
    }

    public void setAgList(List<AgModel> agList) {
        this.agList = agList;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }
}
