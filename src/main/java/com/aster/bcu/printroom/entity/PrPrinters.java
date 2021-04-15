package com.aster.bcu.printroom.entity;

import java.awt.*;
import java.io.Serializable;
import lombok.Data;

/**
 * pr_printers
 * @author 
 */
@Data
public class PrPrinters implements Serializable {
    /**
     * 网点主键
     */
    private String pkPrinter;

    /**
     * 网点编号
     */
    private String code;

    /**
     * 网点名称
     */
    private String name;

    /**
     * 网点位置
     */
    private String location;

    /**
     * 网点状态
     */
    private String state;

    /**
     * 网点报错信息
     */
    private String errorInfo;

    /**
     * 网点详情
     */
    private String info;

    /**
     * 删除标记
     */
    private Integer dr;

    /**
     * 保存时间标记
     */
    private String ts;

    /**
     * 网点密钥
     */
    private String printerKey;

    private static final long serialVersionUID = 1L;
}