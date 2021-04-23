package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.PrUsers;
import com.aster.bcu.printroom.mapper.PrPrintersDao;
import com.aster.bcu.printroom.mapper.PrUsersDao;
import com.aster.bcu.printroom.service.IRegisterService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Component
public class RegisterServiceImpl implements IRegisterService {

    @Resource
    private PrUsersDao usersDao;

    @Override
    public Boolean doRes(Map map) {

        PrUsers users1 = usersDao.selectByPrimaryKey((Integer)map.get("UserId"));
        users1.setUsername((String) map.get("nickName"));
        users1.setUserPassword("123qwe");
        usersDao.updateByPrimaryKey(users1);

        Map map2=new HashMap();
        map2.put("username",users1.getUsername());
        map2.put("userId",users1.getPkUser());
        map2.put("roleId","3");
        usersDao.resUserTable(map2);
        usersDao.resUserRole(map2);

        return true;
    }
}
