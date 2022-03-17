package com.bookmngsys.dao;

import com.bookmngsys.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author Newway
 */
public interface ReaderDao extends JpaRepository<Reader, Integer> {
    @Query(value = "select a.*,b.username,b.status from reader a,user b where a.id=b.id LIMIT :curPage,:pageSize", nativeQuery = true)
    List<Reader> getReaders(int curPage, int pageSize);
}
