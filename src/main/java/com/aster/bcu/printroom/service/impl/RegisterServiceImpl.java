package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.PrUsers;
import com.aster.bcu.printroom.mapper.PrPrintersDao;
import com.aster.bcu.printroom.mapper.PrUsersDao;
import com.aster.bcu.printroom.service.IRegisterService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
@Component
public class RegisterServiceImpl implements IRegisterService {

    @Resource
    private PrUsersDao usersDao;

    @Override
    public Boolean doRes(Map map) {

        PrUsers users1 = usersDao.selectByPrimaryKey(Integer.parseInt((String) map.get("UserId")));
        users1.setUsername((String) map.get("UserName"));
        usersDao.updateByPrimaryKey(users1);
        return true;
    }
}
