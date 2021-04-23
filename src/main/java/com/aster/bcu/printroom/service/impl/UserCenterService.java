package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.PrUsers;
import com.aster.bcu.printroom.entity.SysRole;
import com.aster.bcu.printroom.entity.SysUser;
import com.aster.bcu.printroom.entity.SysUserRole;
import com.aster.bcu.printroom.mapper.PrUsersDao;
import com.aster.bcu.printroom.mapper.SysRoleMapper;
import com.aster.bcu.printroom.mapper.SysUserMapper;
import com.aster.bcu.printroom.mapper.SysUserRoleMapper;
import com.aster.bcu.printroom.service.IUserCenterService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserCenterService implements IUserCenterService {

    @Resource
    private PrUsersDao usersDao;

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;


    @Override
    public Map getUserInfo(int userId) {
        PrUsers prUser = usersDao.selectByPrimaryKey(userId);
        SysUserRole sysUserRole = userRoleMapper.selectByPrimaryKey(userId);

        Map map=new HashMap();
        map.put("userName",prUser.getUsername());
        map.put("userGroup",sysUserRole.getRoleId()+"");
        map.put("userXP",prUser.getUserXp()== null ?0:prUser.getUserXp());
        map.put("userTel1","1");
        map.put("userTel2",prUser.getUserphone());
        map.put("ban",!prUser.getBan().equals("0"));
        map.put("banInfo",prUser.getBanInfo());
        map.put("pkUser",prUser.getPkUser()+"");
        map.put("openID",prUser.getUserMinipro()+"");
        map.put("money",prUser.getMoney());


        return map;
    }

    @Override
    public List<Map> getAllUser() {
        return usersDao.selectAll();
    }
}
