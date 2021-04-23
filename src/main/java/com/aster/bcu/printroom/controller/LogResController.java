package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrUsers;
import com.aster.bcu.printroom.entity.TestEntity;
import com.aster.bcu.printroom.entity.login.WeChatLoginBase;
import com.aster.bcu.printroom.service.IRegisterService;
import com.aster.bcu.printroom.service.LogService;

import com.aster.bcu.printroom.tools.web.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lr")
public class LogResController {
    @Resource
    private LogService logService;
    @Resource
    private IRegisterService registerService;

    private List<String> TokenList;


    @PostMapping("/test")
    Message<String> test(@RequestBody TestEntity requestBody, HttpServletResponse response){
        return Message.success(requestBody.toString());
    }

    @PostMapping("/login")
    String login(){
        return "123";
    }

    @PostMapping("")
    Message<String> res(){

        return Message.success("");
    }

    @PostMapping("/register")
    Message register(@RequestBody Map requestBody, HttpServletResponse response){

        //Map paramData= new Gson().fromJson(requestBody, HashMap.class);

        System.out.println(requestBody);
        registerService.doRes(requestBody);
        Message<Object> success = Message.success("");
        return success;
    }

    @GetMapping("/login/wechat")
    Message wechatLogin(String code){
        HttpUtils httpUtils=new HttpUtils();
        List<NameValuePair> pairList=new ArrayList<>();
        pairList.add(new BasicNameValuePair("appid","wx3123e81d720fe3f3"));//小程序 appId
        pairList.add(new BasicNameValuePair("secret","6214876e41c31fceafe2ad800c0b17a2"));//小程序 appSecret
        pairList.add(new BasicNameValuePair("js_code",code));
        pairList.add(new BasicNameValuePair("grant_type","authorization_code"));
        String getString=httpUtils.get("https://api.weixin.qq.com/sns/jscode2session",pairList);
        WeChatLoginBase weChatLoginBase= new Gson().fromJson(getString,WeChatLoginBase.class);
        switch (weChatLoginBase.getErrcode()){
            case -1:
                return Message.fail("#-1 系统繁忙，请稍后再试");
            case 0:
                //todo 将openid和session_key写入内存（暂时用Mysql实现，之后转redis）
                Map map=null;
                try {
                    map = logService.doLoginForWeChat(weChatLoginBase.getOpenid());
                }catch (Exception r){
                    r.printStackTrace();
                }
                Message<Object> success = Message.success("200");
                success.setObj(map);
                return success;//1为需要绑定 0为不需要绑定
            case 40029:
                return Message.fail("#40029 用户标识无效");
            case 45011:
                return Message.fail("#45011 系统繁忙，请稍后再试");
            default:
                return Message.fail("#"+weChatLoginBase.getErrcode()+" 未知的错误");
        }
    }


}
