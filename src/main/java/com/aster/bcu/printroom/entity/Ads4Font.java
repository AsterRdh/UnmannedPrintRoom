package com.aster.bcu.printroom.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Ads4Font {
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
     * 开始时间
     */
    private List<String> dateRange;

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

    public void sysnFromAds(PrAds ads){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        this.pkAd=ads.getPkAd();
        this.name=ads.getName();
        this.src=ads.getSrc();
        this.dateRange=new ArrayList<>();
        this.dateRange.add(f.format(ads.getStartDate()));
        this.dateRange.add(f.format(ads.getEndDate()));
        this.state=ads.getState();
        this.dr=ads.getDr();
        this.supplier=ads.getSupplier();
        this.charge=ads.getCharge();
        this.charge=ads.getCharge();
        this.approver=ads.getApprover();
        this.approvalTime=ads.getApprovalTime();


    }
}
