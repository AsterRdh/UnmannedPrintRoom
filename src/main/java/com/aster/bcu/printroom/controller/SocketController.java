package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.service.WebSocketServer;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/doSocket")
public class SocketController {
    @PostMapping(value="/pushVideoListToWeb")
    public Message pushVideoListToWeb(@RequestBody Map<String,Object> param){
        Message message;

        Object sltAccountId=param.get("sltAccountId");
        try {
            WebSocketServer.sendInfo("有新客户呼入,sltAccountId:"+sltAccountId);
            message=Message.success("200");
        }catch (IOException e) {
            message=Message.fail("io异常");
            message.setObj(e);
        }
        return message;
    }
}
