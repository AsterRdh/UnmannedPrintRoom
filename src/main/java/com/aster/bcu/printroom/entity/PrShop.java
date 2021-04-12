package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * pr_shop
 * @author 
 */
@Data
public class PrShop implements Serializable {
    /**
     * 产品主键
     */
    private String pkItem;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品编码
     */
    private String code;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 产品接受
     */
    private String info;

    /**
     * 产品数量
     */
    private Integer num;

    /**
     * 删除标识
     */
    private String dr;

    /**
     * 时间戳
     */
    private Date ts;

    /**
     * 资源位置
     */
    private String src;

    /**
     * 使用规则
     */
    private String useRule;

    private static final long serialVersionUID = 1L;
}