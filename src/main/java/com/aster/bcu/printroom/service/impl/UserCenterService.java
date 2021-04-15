package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.PrUsers;
import com.aster.bcu.printroom.mapper.PrUsersDao;
import com.aster.bcu.printroom.service.IUserCenterService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class UserCenterService implements IUserCenterService {

    @Resource
    private PrUsersDao usersDao;


    @Override
    public PrUsers getUserInfo(int userId) {
        return usersDao.selectByPrimaryKey(userId);
    }

    @Override
    public List<Map> getAllUser() {
        return usersDao.selectAll();
    }
}
