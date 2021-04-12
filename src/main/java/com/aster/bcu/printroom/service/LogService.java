package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrUsers;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
    Message<String> tokenTest();
    Message<PrUsers> getUserInfo(String pkUser);
    PrUsers doLogin(String username,String password);
    String doLoginForWeChat(String code);

    int bandCheck(String openId);

    boolean doBand(String openId,String user);

}
