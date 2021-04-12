package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.PrPrinters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PrintersService {

    public Map<String,String> getAllPrinterLocation(Double longitude ,Double latitude);
    public PrPrinters getById(String printerId);
    public List<PrPrinters> getPrintersByAddr(String addr);
    public Map<String,String> getPrintersByLocation(Double longitude ,Double latitude);

    public List<PrPrinters> test();



}
