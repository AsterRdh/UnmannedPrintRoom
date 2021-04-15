package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrPrinters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
public interface PrPrintersDao {
    int deleteByPrimaryKey(String pkPrinter);

    int insert(PrPrinters record);

    int insertSelective(PrPrinters record);

    Map selectByPrimaryKey(String pkPrinter);

    List<PrPrinters> selectAll();

    int updateByPrimaryKeySelective(PrPrinters record);

    int updateByPrimaryKey(Map record);

    int updateStateByPrimaryKey(String pkPrinter,String state);
}