package com.aster.bcu.printroom.controller;

import com.aster.bcu.printroom.entity.Ads4Font;
import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.PrAds;
import com.aster.bcu.printroom.service.AdService;
import com.aster.bcu.printroom.service.IBillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad")
public class AdController {
    @Resource
    private AdService adService;

    private String getState(String a){
        switch (a){
            case "-1" : return "已关闭";
            case "0" : return "启用";
            case "1" : return "审批中";
            default: return "";
        }
    }

    @GetMapping("/getAll")
    public Message getAll(){
        List<PrAds> allAd = adService.getAllAd();
        List<Map> list=new ArrayList<>();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        for(PrAds ad:allAd){
            Map map=new HashMap();
            map.put("key",ad.getPkAd());
            map.put("name",ad.getName());
            map.put("src","http://39.107.232.66:8080/PrintRoom/resource/"+ad.getSrc());
            map.put("startDate",f.format(ad.getStartDate()));
            map.put("endDate",f.format(ad.getEndDate()));
            map.put("tags",new String[]{getState(ad.getState())});
            list.add(map);
        }

        Message<Object> success = Message.success("200");
        success.setObj(list);
        return success;
    }

    @GetMapping("/getOne")
    public Message getOne(String id){
        PrAds oneAd = adService.getOneAd(id);
        oneAd.setSrc("http://127.0.0.1:8080/PrintRoom/resource/"+oneAd.getSrc());
        Ads4Font ads4Font = new Ads4Font();
        ads4Font.sysnFromAds(oneAd);

        Message<Object> success = Message.success("200");
        success.setObj(ads4Font);
        return success;
    }

    @GetMapping("/adSrc")
    public List<String> getAd(int pos){
        switch (pos){
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
        List<String> adScr = adService.getAdScr();

        return adScr;
    }

    @PostMapping("/save")
    public Message saveBill(@RequestBody Map map){


        return Message.success("");
    }

}
