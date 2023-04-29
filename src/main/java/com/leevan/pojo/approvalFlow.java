package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      approvalFlow
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/1 16:56
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class approvalFlow {

    private Integer FlowNo;  //'主键，可作为审批编号'

    private String title;//'标题'

    private String busType; //'审批类型(字典表设置code用作区分类型)'

    private Integer userId;//'审批人主键'

    private Date createTime; //'创建时间'

    private String status;  //'审核状态（1：待审核；2：通过；3：驳回；4：撤销）'


}