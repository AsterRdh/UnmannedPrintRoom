package com.aster.bcu.printroom.mapper;

import com.aster.bcu.printroom.entity.PrBills;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PrBillsMapper {
    int deleteByPrimaryKey(String pkBill);

    int insert(PrBills record);

    int insertSelective(PrBills record);

    PrBills selectByPrimaryKey(String pkBill);

    List<PrBills> selectAll();

    int updateByPrimaryKeySelective(PrBills record);

    int updateByPrimaryKey(PrBills record);

    BigDecimal getAmount(String pkBill);

    int updateStateByPrimaryKey(String pkBill,String state);

    List<PrBills> selectAllByUser(String pkUser);
}
