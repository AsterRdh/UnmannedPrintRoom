package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrPrinters;
import com.aster.bcu.printroom.service.PrintFileService;
import com.aster.bcu.printroom.service.PrintersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/printer")
public class PrintersController {

    @Resource
    private PrintersService printersService;

    @GetMapping("/test")
    public  Message getAllPrinterLocation(){
        List<PrPrinters> test = printersService.test();
        List<Map<String,Object>> temp=new ArrayList<>();
        for (PrPrinters printer:test) {
            Map<String,Object> tempMap=new HashMap<>();
            tempMap.put("name",printer.getName());
            tempMap.put("address",printer.getLocation());
            List<String> tagList=new ArrayList<>();
            tagList.add(printer.getState());
            tempMap.put("tags",tagList);
            temp.add(tempMap);
        }
        Message<Object> success = Message.success("200");
        success.setObj(temp);
        return success;
    }
}
