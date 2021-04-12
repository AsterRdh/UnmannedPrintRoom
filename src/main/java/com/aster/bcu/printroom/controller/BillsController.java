package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrBills;
import com.aster.bcu.printroom.service.IBillService;
import com.aster.bcu.printroom.service.PrintersService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        List<PrBills> billsByUser = billService.getBillsByUser(user);
        Message<Object> message = Message.success("获取订单信息");
        message.setObj(billsByUser);
        return message;
    }

    @PostMapping("/doPrint")
    Message addBill(@RequestBody String requestBody, HttpServletResponse response){
        //规格
        Map paramData= new Gson().fromJson(requestBody,HashMap.class);

        String printer=(String)paramData.get("printer");
        String size=(String)paramData.get("size");
        int count=((Double)paramData.get("count")).intValue();
        String color=(String)paramData.get("color");
        String name=(String)paramData.get("file");
        String user=(String)paramData.get("user");



        Map chargingRoleTree = billService.getChargingRoleTree();
        BigDecimal price = (BigDecimal)((Map) chargingRoleTree.get(size)).get(color);

        BigDecimal total=price.multiply(new BigDecimal(count));

        PrBills bill=new PrBills();
        bill.newPrBills(name,total.doubleValue(),size+" 共"+count+"页 " + color,"","1",new Date(), 0,printer,user);
        PrBills prBills = billService.addBill(bill);
        Message<Object> success = Message.success("");
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
