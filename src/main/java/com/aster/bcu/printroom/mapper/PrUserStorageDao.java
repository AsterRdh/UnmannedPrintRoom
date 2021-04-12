package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrUserStorage;

public interface PrUserStorageDao {
    int deleteByPrimaryKey(String pkStorage);

    int insert(PrUserStorage record);

    int insertSelective(PrUserStorage record);

    PrUserStorage selectByPrimaryKey(String pkStorage);

    int updateByPrimaryKeySelective(PrUserStorage record);

    int updateByPrimaryKey(PrUserStorage record);
}