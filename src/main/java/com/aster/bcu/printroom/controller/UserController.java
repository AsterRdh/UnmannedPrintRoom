package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrPrinters;
import com.aster.bcu.printroom.entity.PrUsers;
import com.aster.bcu.printroom.service.IUserCenterService;
import com.aster.bcu.printroom.service.PrintersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserCenterService userCenterService;

    @GetMapping("/user_info")
    public Message getAllPrinterLocation(String userId){


        PrUsers userInfo = userCenterService.getUserInfo(Integer.parseInt(userId));
        Message<Object> success = Message.success("");
        success.setObj(userInfo);
        return success;
    }
}
