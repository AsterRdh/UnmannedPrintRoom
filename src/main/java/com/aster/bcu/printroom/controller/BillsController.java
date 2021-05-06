package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrBills;
import com.aster.bcu.printroom.service.IBillService;
import com.aster.bcu.printroom.service.PrintersService;
import com.aster.bcu.printroom.service.WebSocketServer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bills")
public class BillsController {

    @Resource
    private IBillService billService;

    @GetMapping("/list")
    Message getBillsListForUser(String user, HttpServletRequest request){
        List<Map> billsByUser = billService.getBillsByUser(user);
        Message<Object> message = Message.success("获取订单信息");
        message.setObj(billsByUser);
        return message;
    }

    @GetMapping("/getOne")
    public Message getBillInfo(String id){
        PrBills bill = billService.getOneBill(id);
        Message<Object> message = Message.success("获取订单信息");
        message.setObj(bill);
        return message;
    }

    @GetMapping("/getAll")
    public Message getAllBill(){
        List<PrBills> allBill = billService.getAllBill();
        Message<Object> message = Message.success("获取订单信息");
        message.setObj(allBill);
        return message;
    }

    @GetMapping("/doCancel")
    public Message doCancel(String billId){
        return billService.doCancel(billId);
    }

    @GetMapping("/doOpenDoor")
    public Message doOpenDoor(String billId){
        return billService.doOpenDoor(billId);
    }

    @GetMapping("/getPrintTaskCount")
    public Message getTaskCount(String printer){

        int taskCont = WebSocketServer.getTaskCont(printer);
        Message message=Message.success("200");
        message.setObj(taskCont);
        return message;
    }

    @PostMapping("/doPrint")
    Message addBill(@RequestBody Map requestBody, HttpServletResponse response){

        String printer=(String)requestBody.get("printer");
        String size=(String)requestBody.get("size");
        int count=(Integer)requestBody.get("count");
        String color=(String)requestBody.get("color");
        String name=(String)requestBody.get("file");
        String user=(String)requestBody.get("user");
        String fileUrl=(String)requestBody.get("fileUrl");
        int printCount=(Integer)requestBody.get("printCount");

        Map chargingRoleTree = billService.getChargingRoleTree();
        BigDecimal price = (BigDecimal)((Map) chargingRoleTree.get(size)).get(color);

        BigDecimal total=price.multiply(new BigDecimal(count));

        PrBills bill=new PrBills();
        bill.newPrBills(name,total,size+" 共"+count+"页 " + color,"","2",new Date(), 0,printer,user);
        PrBills prBills = billService.addBill(bill);
        try {
            WebSocketServer.sendMessage("newTask "+fileUrl+" "+printCount,"1");
        } catch (IOException e) {
            e.printStackTrace();
            Message<Object> success = Message.fail("500");
            success.setObj("无法连接打印机");
            return success;
        }

        if (prBills==null){
            Message<Object> success = Message.fail("500");
            success.setObj("余额不足");
            return success;
        }
        Message<Object> success = Message.success("200");
        success.setObj(prBills);
        return success;
    }

    @GetMapping("/getChargingRoleTree")
    Message getChargingRoleTree(){
        Map chargingRoleTree = billService.getChargingRoleTree();
        Message<Map> success = Message.success("");
        success.setObj(chargingRoleTree);
        return success;
    }
    @GetMapping("/getChargingRole")
    Message getChargingRole(){
        List chargingRoleTree = billService.getChargingRole();
        Message<List> success = Message.success("");
        success.setObj(chargingRoleTree);
        return success;
    }

    @PostMapping("/pay")
    Message doPay(@RequestBody String requestBody, HttpServletResponse response){
        Map paramData= new Gson().fromJson(requestBody,HashMap.class);
        String user=(String)paramData.get("user");
        String bill=(String)paramData.get("bill");
        boolean b = billService.doPay(user, bill);

        Message<Object> success = Message.success("支付结果");
        success.setObj(b);
        return success;
    }
}
