package com.eason.report.pull.platform.ag.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("addition")
public class AgAdditionModel {

    @XStreamAsAttribute
    @XStreamAlias("total")
    private Integer total;
    @XStreamAsAttribute
    @XStreamAlias("num_per_page")
    private Integer numPerPage;
    @XStreamAsAttribute
    @XStreamAlias("currentpage")
    private Integer currentPage;
    @XStreamAsAttribute
    @XStreamAlias("totalpage")
    private Integer totalPage;
    @XStreamAsAttribute
    @XStreamAlias("perpage")
    private Integer perPage;

}
