package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.PrPrinters;
import com.aster.bcu.printroom.mapper.PrPrintersDao;
import com.aster.bcu.printroom.service.PrintersService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PrintersServiceImpl implements PrintersService {
    @Resource
    private PrPrintersDao printersDao;

    @Override
    public Map<String, String> getAllPrinterLocation(Double longitude, Double latitude) {
        return null;
    }

    @Override
    public Map getById(String printerId) {
        Map map = printersDao.selectByPrimaryKey(printerId);
        return map;
    }

    @Override
    public List<PrPrinters> getPrintersByAddr(String addr) {
        return printersDao.selectAllEnable();
    }

    @Override
    public Map<String, String> getPrintersByLocation(Double longitude, Double latitude) {
        return null;
    }

    @Override
    public int updatePrinter(Map map) {
        Map map1 = printersDao.selectByPrimaryKey((String) map.get("pk_printer"));
        map1.putAll(map);
        map1.put("pkPrinter",map.get("pk_printer"));
        //printersDao.updateByPrimaryKey();
        return printersDao.updateByPrimaryKey(map1);
    }

    @Override
    public List<PrPrinters> test() {
        return printersDao.selectAll();
    }

    @Override
    public boolean updateState(String id, String state) {
        try {
            return printersDao.updateStateByPrimaryKey(id, state)>0;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public List<PrPrinters> getAllEnablePrinter() {
        printersDao.selectAllEnable();
        return printersDao.selectAllEnable();
    }
}
