package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrActives;

public interface PrActivesDao {
    int deleteByPrimaryKey(String pkAct);

    int insert(PrActives record);

    int insertSelective(PrActives record);

    PrActives selectByPrimaryKey(String pkAct);

    int updateByPrimaryKeySelective(PrActives record);

    int updateByPrimaryKey(PrActives record);
}