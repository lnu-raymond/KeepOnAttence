package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      approvalSubmit
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/4 21:32
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class approvalSubmit {
    private Integer flowNo;//流程编号

//    private Integer approvalId;//审批号

    private Integer approvalUserId;//审批人id


    private String approvalUsername;//审批人姓名

    private String opinion;//审批意见 驳回还是同意

    private String comments;//审批内容;
}