package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.OnlineUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OnlineUserMapper {
    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectBySessionKey(OnlineUser record);

    int updataTSBySessionKey(OnlineUser record);


}