package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.OnlineUser;
import com.aster.bcu.printroom.mapper.OnlineUserMapper;
import com.aster.bcu.printroom.mapper.PrBillsMapper;
import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrUsers;
import com.aster.bcu.printroom.mapper.PrUsersDao;
import com.aster.bcu.printroom.service.IUserCenterService;
import com.aster.bcu.printroom.service.LogService;
import com.aster.bcu.printroom.tools.Token;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class LocServiceImpl implements LogService {
//
    @Resource
    private OnlineUserMapper onlineUserMapper;
    @Resource
    private PrUsersDao usersDao;


    Token tokenTool=new Token();


    @Override
    public Message<String> tokenTest() {
        return Message.success(null).add( tokenTool.creatToken("00051234","017013156117"));
    }

    @Override
    public Message<PrUsers> getUserInfo(String pkUser) {
        return null;
    }

    @Override
    public PrUsers doLogin(String username,String password) {


        return null;
    }

    @Override
    public String doLoginForWeChat(String code) {

        PrUsers user = usersDao.selectByUserInfo(code);
        if(user==null) {
            //todo 注册
            PrUsers users = new PrUsers();
            users.newPrUsers(0, null, "", "", code, 0, "", 100l, "", "0", "0");
            usersDao.insert(users);
            user = usersDao.selectByUserInfo(code);
        }
        return user.getPkUser()+"";
    }

    @Override
    public int bandCheck(String openId) {

        PrUsers users = usersDao.selectByUserInfo(openId);

        return users==null?0:1;
    }

    @Override
    public boolean doBand(String openId, String user) {
        return false;
    }


}
