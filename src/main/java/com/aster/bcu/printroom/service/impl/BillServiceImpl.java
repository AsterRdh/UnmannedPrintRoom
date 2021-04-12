package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.ChargingStandard;
import com.aster.bcu.printroom.entity.PrBills;
import com.aster.bcu.printroom.entity.PrPrinters;
import com.aster.bcu.printroom.entity.PrWallet;
import com.aster.bcu.printroom.mapper.ChargingStandardDao;
import com.aster.bcu.printroom.mapper.PrBillsMapper;
import com.aster.bcu.printroom.mapper.PrPrintersDao;
import com.aster.bcu.printroom.mapper.PrWalletDao;
import com.aster.bcu.printroom.service.IBillService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class BillServiceImpl implements IBillService {

    @Resource
    private ChargingStandardDao chargingStandardDao;

    @Resource
    private PrBillsMapper billsMapper ;

    @Resource
    private PrWalletDao walletDao ;

    @Override
    public PrBills addBill( PrBills bill) {
        billsMapper.insert(bill);
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
    public List<PrBills> getBillsByUser(String user) {
        return billsMapper.selectAllByUser(user);
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
