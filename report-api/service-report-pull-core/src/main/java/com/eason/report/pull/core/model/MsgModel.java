package com.eason.report.pull.core.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MsgModel extends Model {
    private String type;
    private String message;
    private Model model;
}
