package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * pr_wallet
 * @author 
 */
@Data
public class PrWallet implements Serializable {
    private String pkUser;

    private BigDecimal balance;

    private static final long serialVersionUID = 1L;
}