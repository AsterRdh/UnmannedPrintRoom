package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad")
public class AdController {
    @GetMapping("/")
    public Message getAd(int pos){
        switch (pos){
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }

        return Message.success("");
    }
}
