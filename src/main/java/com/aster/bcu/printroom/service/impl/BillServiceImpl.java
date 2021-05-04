package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.*;
import com.aster.bcu.printroom.mapper.*;
import com.aster.bcu.printroom.service.IBillService;
import com.aster.bcu.printroom.service.WebSocketServer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BillServiceImpl implements IBillService {

    @Resource
    private ChargingStandardDao chargingStandardDao;

    @Resource
    private PrBillsMapper billsMapper ;

    @Resource
    private PrWalletDao walletDao ;

    @Resource
    private PrUsersDao usersDao;

    @Resource
    private PrPrintersDao printersDao;

    @Override
    public List<PrBills> getAllBill() {
        List<PrUsers> users = usersDao.selectAll4Translate();
        List<PrPrinters> prPrinters = printersDao.selectAll();
        List<PrBills> prBills = billsMapper.selectAll();

        Map<Integer, String> userMap = users.stream().collect(Collectors.toMap(PrUsers::getPkUser, PrUsers::getUsername));
        Map<String, String> printerMap = prPrinters.stream().collect(Collectors.toMap(PrPrinters::getPkPrinter, PrPrinters::getName));

        for (PrBills bill:prBills) {
            bill.setPkUser(userMap.get(Integer.parseInt(bill.getPkUser() )));
            bill.setPkPrinter(printerMap.get(bill.getPkPrinter()));

        }
        return prBills;
    }

    @Override
    public PrBills addBill( PrBills bill) {

        PrUsers users = usersDao.selectByPrimaryKey(Integer.parseInt(bill.getPkUser()));
        BigDecimal subtract = users.getMoney().subtract(new BigDecimal(bill.getAmount()));
        if(subtract.doubleValue()>0){
            bill.setPkBill(UUID.randomUUID().toString());
            int insert = billsMapper.insert(bill);
            users.setMoney(subtract);
            usersDao.updateByPrimaryKey(users);
        }else {
           return null;
        }


        return bill;
    }



    @Override
    public List<ChargingStandard> getChargingRole() {
        return chargingStandardDao.selectAll();
    }

    @Override
    public Map getChargingRoleTree() {
        List<ChargingStandard> chargingStandards = chargingStandardDao.selectAll();
        Map<String,Object> chargingTree=new HashMap();


        return listToMap(chargingTree,chargingStandards);
    }

    @Override
    public boolean doPay(String user, String pkBill) {
        BigDecimal amount = billsMapper.getAmount(pkBill);
        BigDecimal record = walletDao.getRecord(user);

        if(record.compareTo(amount)>-1){
            PrWallet prWallet = new PrWallet();
            prWallet.setPkUser(user);
            prWallet.setBalance(record.subtract(amount));
            int a=walletDao.pay(prWallet);
            int i = billsMapper.updateStateByPrimaryKey(pkBill, "2");
            return a==0 && i==0;
        }else {
            return false;
        }
    }

    @Override
    public Message doCancel(String billId){
        PrBills prBills = billsMapper.selectByPrimaryKey(billId);
        String pkPrinter = prBills.getPkPrinter();
        try {
            WebSocketServer.doDrop(pkPrinter,billId);
            Message message=Message.success("200");
            billsMapper.updateStateByPrimaryKey(billId,"-1");
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            Message message=Message.fail("500");
            message.setMsg("无法取消订单");
            return message;
        }
    }

    @Override
    public Message doOpenDoor(String billId) {
        PrBills prBills = billsMapper.selectByPrimaryKey(billId);
        String pkPrinter = prBills.getPkPrinter();
        try {
            WebSocketServer.doOpenDoor(pkPrinter,billId);
            Message message=Message.success("200");
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            Message message=Message.fail("500");
            message.setMsg("开门失败");
            return message;
        }

    }

    @Override
    public String getPrinter(String name) {
        return null;
    }

    @Override
    public List<Map> getBillsByUser(String user) {



        List<Map> list = billsMapper.selectAllByUser(user);
        for(Map map:list){
            switch ((String) map.get("state")){
                case "-1":map.put("stateZh","已关闭"); break;
                case "0":map.put("stateZh","待付款"); break;
                case "1":map.put("stateZh","已完成"); break;
                case "2":map.put("stateZh","排队中"); break;
                case "3":map.put("stateZh","打印中"); break;
                case "4":map.put("stateZh","待取件"); break;
            }
        }
        return list;
    }

    @Override
    public PrBills getOneBill(String id) {
        PrBills prBills = billsMapper.selectByPrimaryKey(id);
        return prBills;
    }

    private Map listToMap(Map<String,Object> chargingTree,List<ChargingStandard> csList){
        List<ChargingStandard> cList=new ArrayList();
        Map<Integer ,String> map1=new HashMap<>();
        for(ChargingStandard cs:csList){
            if(cs.getPid()==null){
                chargingTree.put(cs.getName(),new HashMap<String, BigDecimal>() );
                map1.put(cs.getId(),cs.getName());
            }
            cList.add(cs);
        }

        for(ChargingStandard cs:cList){
            if(cs.getPid()!=null){
                String pName=map1.get(cs.getPid());
                Map o = (Map<String, BigDecimal>)chargingTree.get(pName);
                o.put(cs.getName(),cs.getPrice());
            }
        }
        return chargingTree;
    }

}
