package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.SysUserRole;
import com.aster.bcu.printroom.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserRoleService {
    @Resource
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        List<SysUserRole> sysUserRoles = userRoleMapper.listByUserId(userId);

        return sysUserRoles;
    }
}
