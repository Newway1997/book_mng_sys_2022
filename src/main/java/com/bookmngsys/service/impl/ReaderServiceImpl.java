package com.bookmngsys.service.impl;

import com.bookmngsys.dao.ReaderDao;
import com.bookmngsys.entity.Reader;
import com.bookmngsys.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Newway
 */
@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    ReaderDao readerDao;

    @Override
    public Reader getReader(int id) {
        return readerDao.findById(id).orElse(null);
    }

    @Override
    public void updateReader(Reader reader) {
        Reader n_reader = readerDao.findById(reader.getId()).get();
        reader.setLevel(n_reader.getLevel());
        readerDao.save(reader);
    }

    @Override
    public List<Reader> getReaders(int curPage, int pageSize) {
        return readerDao.getReaders(curPage, pageSize);
    }
}
