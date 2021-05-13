package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Security";
    }
    @PostMapping("/test")
    public Map test(@RequestBody Map data){
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("data", new Gson().toJson(data));
        return response;
    }
}
