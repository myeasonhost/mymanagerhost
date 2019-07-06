package com.eason.report.pull.platform.ag.model.hunter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("Result")
public class HunterListModel {
    @XStreamImplicit(itemFieldName="Items")
    private List<HunterModel> agList;
    @XStreamAlias("Code")
    private String code;
    @XStreamAlias("PageData")
    private PageData addition;
}
