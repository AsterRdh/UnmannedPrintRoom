package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.SysRole;
import com.aster.bcu.printroom.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleService {
    @Resource
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }
}
