package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrHelp;

public interface PrHelpDao {
    int deleteByPrimaryKey(Integer pkHelp);

    int insert(PrHelp record);

    int insertSelective(PrHelp record);

    PrHelp selectByPrimaryKey(Integer pkHelp);

    int updateByPrimaryKeySelective(PrHelp record);

    int updateByPrimaryKey(PrHelp record);
}