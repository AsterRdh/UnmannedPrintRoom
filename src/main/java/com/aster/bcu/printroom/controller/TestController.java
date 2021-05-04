package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Security";
    }
    @PostMapping("/test")
    public Message test(@RequestBody Map data){
        Message message=Message.success("200");
        message.setObj(data);
        return message;
    }
}
