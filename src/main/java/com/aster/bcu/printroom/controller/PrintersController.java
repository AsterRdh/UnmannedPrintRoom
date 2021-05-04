package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrPrinters;
import com.aster.bcu.printroom.service.PrintFileService;
import com.aster.bcu.printroom.service.PrintersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
            Map<String,Object> tempMap=new LinkedHashMap<>();
            tempMap.put("key",printer.getPkPrinter());
            tempMap.put("name",printer.getName());
            tempMap.put("address",printer.getInfo());
            List<String> tagList=new ArrayList<>();
            if(printer.getState()!=null) {
                switch (printer.getState()){
                    case "-1":tagList.add("关闭"); break;
                    case "0":tagList.add("正常"); break;
                    case "1":tagList.add("异常"); break;
                    default: break;
                }
            }
            tempMap.put("tags",tagList);
            temp.add(tempMap);
        }
        Message<Object> success = Message.success("200");
        success.setObj(temp);
        return success;
    }

    @GetMapping("/getOne")
    public  Message getAllPrinterLocation(String id){
        Map byId = printersService.getById(id);
        Message<Object> success = Message.success("200");
        success.setObj(byId);
        return success;
    }

    @GetMapping("/getPrinterlistEnable")
    public Message getEnablePrinter(double x,double y){
        List<PrPrinters> allEnablePrinter = printersService.getAllEnablePrinter();
        List<String> resMap=new ArrayList<>();
        for (PrPrinters printer:allEnablePrinter){
            resMap.add(printer.getName());
        }
        Message message=Message.success("200");
        message.setObj(resMap);
        return message;
    }

    @GetMapping("/enable")
    public Boolean setState(String id,String state){
        return printersService.updateState(id,state);
    }

    @PostMapping("/update")
    public boolean updatePrinter(@RequestBody Map map){
        return printersService.updatePrinter(map)!=0;
    }
}
