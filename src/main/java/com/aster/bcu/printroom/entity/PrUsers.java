package com.aster.bcu.printroom.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * pr_users
 * @author 
 */
@Data
public class PrUsers implements Serializable {
    /**
     * 用户编号
     */
    private Integer pkUser;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户手机号
     */
    private String userphone;

    /**
     * 用户小程序编号
     */
    private String userMinipro;

    /**
     * 用户动态密码生成种子
     */
    private Integer userSeed;

    /**
     * 封禁理由
     */
    private String banInfo;

    /**
     * 用户经验
     */
    private Long userXp;

    /**
     * 用户头像
     */
    private String userImg;

    /**
     * 封禁表示
     */
    private String ban;

    /**
     * 删除标识
     */
    private String dr;

    private static final long serialVersionUID = 1L;

    public void newPrUsers(Integer pkUser, String username, String userPassword, String userphone, String userMinipro, Integer userSeed, String banInfo, Long userXp, String userImg, String ban, String dr) {
        this.pkUser = pkUser;
        this.username = username;
        this.userPassword = userPassword;
        this.userphone = userphone;
        this.userMinipro = userMinipro;
        this.userSeed = userSeed;
        this.banInfo = banInfo;
        this.userXp = userXp;
        this.userImg = userImg;
        this.ban = ban;
        this.dr = dr;
    }
}