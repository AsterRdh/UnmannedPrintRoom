package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrUsers;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LogService {
    Message<String> tokenTest();
    Message<PrUsers> getUserInfo(String pkUser);
    PrUsers doLogin(String username,String password);
    Map doLoginForWeChat(String code);

    int bandCheck(String openId);

    boolean doBand(String openId,String user);

}
