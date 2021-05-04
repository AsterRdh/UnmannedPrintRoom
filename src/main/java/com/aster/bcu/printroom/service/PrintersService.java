package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.PrPrinters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PrintersService {

    public Map<String,String> getAllPrinterLocation(Double longitude ,Double latitude);
    public Map getById(String printerId);
    public List<PrPrinters> getPrintersByAddr(String addr);
    public Map<String,String> getPrintersByLocation(Double longitude ,Double latitude);

    public int updatePrinter(Map map);

    public List<PrPrinters> test();

    public boolean updateState(String id,String state);

    public List<PrPrinters> getAllEnablePrinter();

}
