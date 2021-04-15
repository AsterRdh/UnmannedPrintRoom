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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserCenterService userCenterService;

    @GetMapping("/getAll")
    public Message getAllUser(){
        List<Map> allUser = userCenterService.getAllUser();
        for(Map map:allUser){
            List list=new ArrayList();
            list.add(map.get("role"));
            map.put("role",list);
        }
        Message<Object> success = Message.success("200");
        success.setObj(allUser);
        return success;
    }


    @GetMapping("/user_info")
    public Message getAllPrinterLocation(String userId){


        PrUsers userInfo = userCenterService.getUserInfo(Integer.parseInt(userId));
        Message<Object> success = Message.success("");
        success.setObj(userInfo);
        return success;
    }
}
