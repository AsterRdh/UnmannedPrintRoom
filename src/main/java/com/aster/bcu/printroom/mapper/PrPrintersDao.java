package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrPrinters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface PrPrintersDao {
    int deleteByPrimaryKey(String pkPrinter);

    int insert(PrPrinters record);

    int insertSelective(PrPrinters record);

    PrPrinters selectByPrimaryKey(String pkPrinter);

    List<PrPrinters> selectAll();

    int updateByPrimaryKeySelective(PrPrinters record);

    int updateByPrimaryKey(PrPrinters record);
}