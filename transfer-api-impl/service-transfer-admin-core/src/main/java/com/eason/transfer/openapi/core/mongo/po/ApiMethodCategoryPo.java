package com.eason.transfer.openapi.core.mongo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiMethodCategoryPo extends BasePo {
    @Id
    private String tid;
    private String cateEnName;
    private String cateCnName;
    private String cateDescription;
    private Byte isDeleted;

}
