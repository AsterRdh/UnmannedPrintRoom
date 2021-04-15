package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.PrAds;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdService {
    public List<PrAds> getAllAd();
    public PrAds getOneAd(String id);
    public int insertAd(PrAds ad);
    public int updateAd(PrAds ad);
    public int approve(String id);
    public int updateState(String id,String state);

}
