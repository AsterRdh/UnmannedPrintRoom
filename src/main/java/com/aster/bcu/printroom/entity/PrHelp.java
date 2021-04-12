package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pr_help
 * @author 
 */
@Data
public class PrHelp implements Serializable {
    /**
     * 文档主键
     */
    private Integer pkHelp;

    /**
     * 文档内容
     */
    private String helpInfo;

    /**
     * 文档资源
     */
    private String src;

    /**
     * 更新日期
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}