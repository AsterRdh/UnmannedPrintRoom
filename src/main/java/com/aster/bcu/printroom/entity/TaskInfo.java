package com.aster.bcu.printroom.entity;

import java.io.File;

public class TaskInfo {
    private String name;
    private String code;
    private String fillPath;

    public TaskInfo(String name, String code, String fillPath) {
        this.name = name;
        this.code = code;
        this.fillPath = fillPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFillPath() {
        return fillPath;
    }

    public void setFillPath(String fillPath) {
        this.fillPath = fillPath;
    }

}
