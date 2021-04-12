package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.ChargingStandard;

import java.util.List;

public interface ChargingStandardDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ChargingStandard record);

    int insertSelective(ChargingStandard record);

    ChargingStandard selectByPrimaryKey(Integer id);

    List<ChargingStandard> selectAll();

    int updateByPrimaryKeySelective(ChargingStandard record);

    int updateByPrimaryKey(ChargingStandard record);
}