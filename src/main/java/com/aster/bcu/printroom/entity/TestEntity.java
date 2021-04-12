package com.aster.bcu.printroom.entity;

import lombok.Data;

@Data
public class TestEntity {
    private String a;
    private int b;
    private TestBodyEntity bodyEntity;

    @Override
    public String toString() {
        return "TestEntity{" +
                "a='" + a + '\'' +
                ", b=" + b +
                ", bodyEntity=" + bodyEntity.toString() +
                '}';
    }
}
