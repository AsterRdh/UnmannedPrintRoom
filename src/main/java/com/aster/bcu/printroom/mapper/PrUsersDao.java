package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrUsers;

import java.util.List;
import java.util.Map;

public interface PrUsersDao {
    int deleteByPrimaryKey(Integer pkUser);

    int insert(PrUsers record);

    int insertSelective(PrUsers record);

    PrUsers selectByPrimaryKey(Integer pkUser);

    PrUsers selectByUserInfo(String openId);

    List<Map> selectAll();

    List<PrUsers> selectAll4Translate();

    int updateByPrimaryKeySelective(PrUsers record);

    int updateByPrimaryKey(PrUsers record);
}
