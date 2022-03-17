package com.bookmngsys.dao;

import com.bookmngsys.entity.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;


/**
 * @author Newway
 */
public interface BorrowBookDao extends JpaRepository<BorrowBook, Integer> {

    @Query(value = "select a.*,b.book_name,b.author,b.publisher,b.img_url from borrow_book a,book b where a.isbn=b.isbn and a.reader_id=:readerId and a.status=1", nativeQuery = true)
    ArrayList<BorrowBook> getBorrowBooks(int readerId);


    @Modifying
    @Query(value = "update borrow_book SET status = 0 WHERE id = :id and reader_id = :readerId", nativeQuery = true)
    void updateBorrowBookStatus(int id, int readerId);
}
