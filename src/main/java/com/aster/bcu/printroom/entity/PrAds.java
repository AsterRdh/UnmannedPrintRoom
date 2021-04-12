package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pr_ads
 * @author 
 */
@Data
public class PrAds implements Serializable {
    /**
     * 广告编码
     */
    private String pkAd;

    /**
     * 广告名称
     */
    private String name;

    /**
     * 资源位置
     */
    private String src;

    /**
     * 供应商
     */
    private Date startDate;

    /**
     * 开始时间
     */
    private Date endDate;

    /**
     * 结束时间
     */
    private String state;

    /**
     * 广告状态
     */
    private String dr;

    /**
     * 删除标示
     */
    private String supplier;

    /**
     * 负责人
     */
    private String charge;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批时间
     */
    private Date approvalTime;

    private static final long serialVersionUID = 1L;
}