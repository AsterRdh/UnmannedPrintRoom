package com.aster.bcu.printroom.tools;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Token {
    private Date date=new Date();
    private SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmss");


    public String creatToken(String userId, String ip){
        String token="";
        token=String.format("%ty",date);
        token=userId+token+ip;

        return token;
    }
}
