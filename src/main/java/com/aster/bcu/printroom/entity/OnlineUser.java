package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * online_user
 * @author 
 */
@Data
public class OnlineUser implements Serializable {
    public OnlineUser(String userPk, String openid, String sessionKey, Date ts) {
        this.userPk = userPk;
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.ts = ts;
    }

    public OnlineUser() {
    }

    private String userPk;

    private String openid;

    private String sessionKey;

    private Date ts;

    private static final long serialVersionUID = 1L;
}