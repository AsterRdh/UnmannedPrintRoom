package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.PrPrinters;
import com.aster.bcu.printroom.mapper.PrPrintersDao;
import com.aster.bcu.printroom.service.PrintersService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    public PrPrinters getById(String printerId) {
        return null;
    }

    @Override
    public List<PrPrinters> getPrintersByAddr(String addr) {
        return null;
    }

    @Override
    public Map<String, String> getPrintersByLocation(Double longitude, Double latitude) {
        return null;
    }

    @Override
    public List<PrPrinters> test() {
        return printersDao.selectAll();
    }
}
