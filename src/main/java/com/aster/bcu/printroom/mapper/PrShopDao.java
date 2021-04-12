package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrShop;

public interface PrShopDao {
    int deleteByPrimaryKey(String pkItem);

    int insert(PrShop record);

    int insertSelective(PrShop record);

    PrShop selectByPrimaryKey(String pkItem);

    int updateByPrimaryKeySelective(PrShop record);

    int updateByPrimaryKey(PrShop record);
}