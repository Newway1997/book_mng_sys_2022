package com.bookmngsys.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Newway
 */
@Data
@Entity
public class Reader {
    /**
     * 读者id
     */
    @Id
    private int id;
    /**
     * 读者姓名
     */
    private String name;
    /**
     * 读者年龄
     */
    private int age;
    /**
     * 读者性别
     */
    private int sex;
    /**
     * 读者电话
     */
    private String phone;

    private Date regdate;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "level")
    private ReaderLevel level;
}
