package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.PrAds;
import com.aster.bcu.printroom.mapper.PrAdsDao;
import com.aster.bcu.printroom.service.AdService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Component
public class AdServiceImpl implements AdService {

    @Resource
    private PrAdsDao prAdsDao;

    @Override
    public List<PrAds> getAllAd() {
        return prAdsDao.selectAll();
    }

    @Override
    public PrAds getOneAd(String id) {
        return prAdsDao.selectByPrimaryKey(id);
    }

    @Override
    public int insertAd(PrAds ad) {
        return 0;
    }

    @Override
    public int updateAd(PrAds ad) {
        return 0;
    }

    @Override
    public int approve(String id) {
        return 0;
    }

    @Override
    public int updateState(String id, String state) {
        return 0;
    }
}
