package com.bookmngsys.service;

import com.bookmngsys.entity.Reader;

import java.util.List;

/**
 * @author Newway
 */
public interface ReaderService {
    /**
     * 根据id查找读者
     * @param id id
     * @return 读者
     */
    Reader getReader(int id);

    /**
     * 更新读者信息
     * @param reader 读者信息
     */
    void updateReader(Reader reader);

    /**
     * 获取读者
     * @param curPage 当前页
     * @param pageSize 每页大小
     * @return 读者列表
     */
    List<Reader> getReaders(int curPage, int pageSize);
}
