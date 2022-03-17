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
@Entity(name = "reader_level")
public class ReaderLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 最大可借数量
     */
    private int maxBrNum;
    /**
     * 最大可借天数
     */
    private int maxBrDay;
}
