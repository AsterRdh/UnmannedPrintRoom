package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrAds;

public interface PrAdsDao {
    int deleteByPrimaryKey(String pkAd);

    int insert(PrAds record);

    int insertSelective(PrAds record);

    PrAds selectByPrimaryKey(String pkAd);

    int updateByPrimaryKeySelective(PrAds record);

    int updateByPrimaryKey(PrAds record);
}