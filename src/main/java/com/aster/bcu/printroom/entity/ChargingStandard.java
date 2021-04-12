package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * charging_standard
 * @author 
 */
@Data
public class ChargingStandard implements Serializable {
    private Integer id;

    private String name;

    private Integer pid;

    private BigDecimal price;

    private String type;

    private static final long serialVersionUID = 1L;
}