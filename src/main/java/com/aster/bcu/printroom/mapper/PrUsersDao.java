package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrUsers;

public interface PrUsersDao {
    int deleteByPrimaryKey(Integer pkUser);

    int insert(PrUsers record);

    int insertSelective(PrUsers record);

    PrUsers selectByPrimaryKey(Integer pkUser);

    PrUsers selectByUserInfo(String openId);

    int updateByPrimaryKeySelective(PrUsers record);

    int updateByPrimaryKey(PrUsers record);
}