package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {

    public List<SysUserRole> listByUserId(Integer userId);

    public SysUserRole selectByPrimaryKey(Integer userId);
}
