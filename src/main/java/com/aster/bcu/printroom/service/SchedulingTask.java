package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.PrPrinters;
import com.aster.bcu.printroom.mapper.PrPrintersDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SchedulingTask {
    @Resource
    WebSocketServer webSocketServer;

    @Resource
    PrPrintersDao printersDao;

    @Scheduled(cron = "0 1/2 * * * ?")
    public void execute() {
        List<PrPrinters> prPrinters = printersDao.selectAllEnable();
        for (PrPrinters printer:prPrinters){
            try{
                webSocketServer.sendMessage("?",printer.getPkPrinter());
            }catch (Exception e){
                printersDao.updateState(printer.getPkPrinter(),"-1");
            }

        }

    }
}
