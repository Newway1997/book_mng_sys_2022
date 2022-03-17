package com.bookmngsys.domain;

import com.bookmngsys.entity.Reader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author Newway
 */
@Getter
@Setter
public class ReaderDto {
    /**
     * 读者id
     */
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

    /**
     * 用户名
     */
    private String username;

    /**
     * 类型，普通用户1，管理员0
     */
    private int type = 1;
    /**
     * 状态，启用1，不启用0
     */
    private int status = 1;

    /**
     * 最大可借数量
     */
    private int maxBrNum;
    /**
     * 最大可借天数
     */
    private int maxBrDay;

    public static ReaderDto convertToReaderDto(Reader reader) {
        ReaderDto readerDto = new ReaderDto();
        BeanUtils.copyProperties(reader, readerDto);
        readerDto.setUsername(reader.getUser().getUsername());
        readerDto.setStatus(reader.getUser().getStatus());
        readerDto.setType(reader.getUser().getType());
        readerDto.setMaxBrDay(reader.getLevel().getMaxBrDay());
        readerDto.setMaxBrNum(reader.getLevel().getMaxBrNum());
        return readerDto;
    }

    public static Reader convertToReader(ReaderDto readerDto) {
        Reader reader = new Reader();
        BeanUtils.copyProperties(readerDto, reader);
        return reader;
    }
}
