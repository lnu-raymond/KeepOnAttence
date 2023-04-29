package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      approvalFlowDetail
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/1 17:03
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class approvalFlowDetail {

  private int  id;// '明细表主键'

  private Integer  flowNo; // '审批表主键,关联审批表'

  private Integer  userId;  //'审批人主键'

  private Date createTime;// '创建时间'

  private String approvalTime;// '审批时间'

  private String approvator;//审批人名字

  private String opinion; // '审核意见'

  private String status;// '审核状态（1：审核中；2：等待我审核；3：通过；4：驳回）'

  private String comments; // '评论'

  private Integer order;//审批顺序




}