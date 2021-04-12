package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrWallet;

import java.math.BigDecimal;

public interface PrWalletDao {
    int insert(PrWallet record);

    int insertSelective(PrWallet record);
    int pay(PrWallet record);

    BigDecimal getRecord(String user);
}