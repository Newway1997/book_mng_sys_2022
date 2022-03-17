package com.bookmngsys.dao;

import com.bookmngsys.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author Newway
 */
public interface BookDao extends JpaRepository<Book, String> {
    @Query(value = "select * from book where status=1 LIMIT :curPage,:pageSize", nativeQuery = true)
    ArrayList<Book> findEnabledBooks(int curPage, int pageSize);

    @Modifying
    @Query(value = "UPDATE Book SET lastNum=lastNum+1 WHERE isbn = :isbn and lastNum>0")
    void addBookNum(String isbn);

    @Modifying
    @Query(value = "UPDATE Book SET lastNum=lastNum-1 WHERE isbn = :isbn and lastNum>0")
    void reduceBookNum(String isbn);

    @Transactional
    @Modifying
    @Query(value = "update Book set status=:status where isbn=:isbn")
    void updateBookStatus(String isbn, int status);

}
