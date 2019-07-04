package com.eason.report.pull.platform.ag.model.yoplay;

import com.eason.report.pull.platform.ag.model.common.AgAdditionModel;
import com.eason.report.pull.platform.ag.model.agin.AginModel;
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
public class YoplayListModel {
    @XStreamImplicit(itemFieldName="row")
    private List<YoplayModel> agList;
    @XStreamAlias("info")
    private String info;
    @XStreamAlias("addition")
    private AgAdditionModel addition;
}
