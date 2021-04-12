package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.ChargingStandard;
import com.aster.bcu.printroom.entity.PrBills;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IBillService {



    public PrBills addBill( PrBills bill);

    public List<ChargingStandard> getChargingRole();

    public Map getChargingRoleTree();

    public boolean doPay(String user,String bill);

    public List<PrBills> getBillsByUser(String user);
}
