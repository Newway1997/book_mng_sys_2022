package com.bookmngsys.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Newway
 */
@Data
@Entity(name = "user")
public class User {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 类型，普通用户1，管理员0
     */
    private int type = 1;
    /**
     * 状态，启用1，不启用0
     */
    private int status = 1;
}
