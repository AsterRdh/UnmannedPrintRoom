package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pr_actives
 * @author 
 */
@Data
public class PrActives implements Serializable {
    /**
     * 
活动编号 
     */
    private String pkAct;

    /**
     * 活动名
     */
    private String name;

    /**
     * 活动编码
     */
    private String code;

    /**
     * 资源地址
     */
    private String src;

    /**
     * 活动信息
     */
    private String info;

    /**
     * 活动规则
     */
    private String rule;

    /**
     * 删除标识
     */
    private Integer dr;

    /**
     * 创建人 
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批时间
     */
    private Date approvalTime;

    /**
     * 开始日期 
     */
    private Date startDate;

    /**
     * 结束时间 
     */
    private Date endDate;

    private static final long serialVersionUID = 1L;
}