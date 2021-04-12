package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
import org.springframework.util.DigestUtils;

/**
 * pr_bills
 * @author 
 */
@Data
public class PrBills implements Serializable {
    /**
     * 订单主键
     */
    private String pkBill;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 订单名
     */
    private String name;

    /**
     * 订单金额
     */
    private Double amount;

    /**
     * 订单详情
     */
    private String info;

    /**
     * 订单类型
     */
    private String type;

    /**
     * 订单状态
     */
    private String state;

    /**
     * 订单开始日期
     */
    private Date startDate;

    /**
     * 订单结束日期
     */
    private Date endDate;

    /**
     * 删除标识
     */
    private Integer dr;

    /**
     * 来源站点
     */
    private String pkPrinter;

    private String pkUser;

    private static final long serialVersionUID = 1L;


    public void newPrBills(String name, Double amount, String info, String type, String state, Date startDate, Integer dr, String pkPrinter, String pkUser) {
        String str =   UUID.randomUUID().toString();
        str=DigestUtils.md5DigestAsHex(str.getBytes());
        String regex = "(.{4})";
        str=str.replaceAll(regex, "$1-");
        str=str.substring(0,str.length()-1);
        this.pkBill =str;
        this.name = name;
        this.amount = amount;
        this.info = info;
        this.type = type;
        this.state = state;
        this.startDate = startDate;
        this.dr = dr;
        this.pkPrinter = pkPrinter;
        this.pkUser = pkUser;
    }
}