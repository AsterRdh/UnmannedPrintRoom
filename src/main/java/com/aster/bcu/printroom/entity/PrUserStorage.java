package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pr_user_storage
 * @author 
 */
@Data
public class PrUserStorage implements Serializable {
    /**
     * 物品栏主键
     */
    private String pkStorage;

    /**
     * 物品主键
     */
    private String pkItem;

    /**
     * 物品状态
     */
    private String stage;

    /**
     * 生效日期
     */
    private Date startDate;

    /**
     * 失效日期
     */
    private Date endDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属人
     */
    private String pkUser;

    private static final long serialVersionUID = 1L;
}