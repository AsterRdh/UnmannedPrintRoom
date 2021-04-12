package com.aster.bcu.printroom.entity;

import lombok.Data;

import java.util.Map;

@Data
public class TestBodyEntity {
    private String bd1;
    private int bd2;
    private Map<String,Object> bd3;

    @Override
    public String toString() {
        return "TestBodyEntity{" +
                "bd1='" + bd1 + '\'' +
                ", bd2=" + bd2 +
                ", bd3=" + bd3.toString() +
                '}';
    }
}
