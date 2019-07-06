package com.eason.report.pull.platform.ag.model.hunter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("PageData")
public class PageData {
    @XStreamAsAttribute
    @XStreamAlias("ItemTotal")
    private Integer itemTotal;
    @XStreamAsAttribute
    @XStreamAlias("perpage")
    private Integer perpage;
    @XStreamAsAttribute
    @XStreamAlias("NumPage")
    private Integer NumPage;
}
