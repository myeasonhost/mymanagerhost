package com.eason.report.pull.platform.ag.model.xin;

import com.eason.report.pull.platform.ag.model.common.AgAdditionModel;
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
public class XinListModel {
    @XStreamImplicit(itemFieldName="row")
    private List<XinModel> agList;
    @XStreamAlias("info")
    private String info;
    @XStreamAlias("addition")
    private AgAdditionModel addition;
}
