package com.bookmngsys.service.impl;

import com.bookmngsys.dao.BookDao;
import com.bookmngsys.dao.BorrowBookDao;
import com.bookmngsys.dao.CategoryDao;
import com.bookmngsys.entity.Book;
import com.bookmngsys.entity.BorrowBook;
import com.bookmngsys.entity.Category;
import com.bookmngsys.entity.Reader;
import com.bookmngsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Newway
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    BorrowBookDao borrowBookDao;
    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Book> getBooks(int curPage, int pageSize, boolean isAdmin) {
        if (isAdmin) {
            return bookDao.findAll(PageRequest.of(curPage, pageSize)).getContent();
        }
        return bookDao.findEnabledBooks(curPage, pageSize);
    }

    @Override
    public Book getBook(String isbn) {
        return bookDao.findById(isbn).orElse(null);
    }

    @Transactional
    @Override
    public void borrowBook(String isbn, int readerId) throws Exception {
        Book existBook = bookDao.findById(isbn).orElse(null);
        if (existBook == null || existBook.getLastNum() < 1) {
            throw new Exception("余量不足");
        }
        BorrowBook borrowBookRecord = new BorrowBook();
        Book book = new Book();
        book.setIsbn(isbn);
        borrowBookRecord.setBook(book);

        Reader reader = new Reader();
        reader.setId(readerId);
        borrowBookRecord.setReader(reader);

        Date borrowDate = new Date();

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 1440);
        Date returnDate = nowTime.getTime();

        borrowBookRecord.setBorrowDate(borrowDate);
        borrowBookRecord.setReturnDate(returnDate);

        borrowBookDao.save(borrowBookRecord);
        bookDao.reduceBookNum(isbn);
    }

    @Override
    public ArrayList<BorrowBook> getBorrowBooks(int readerId) {
        return borrowBookDao.getBorrowBooks(readerId);
    }

    @Override
    @Transactional
    public void returnBook(int id, String isbn, int readerId) {
        borrowBookDao.updateBorrowBookStatus(id, readerId);
        bookDao.addBookNum(isbn);
    }

    @Override
    public void enableBook(String isbn) {
        bookDao.updateBookStatus(isbn, 1);
    }

    @Override
    public void disableBook(String isbn) {
        bookDao.updateBookStatus(isbn, 0);
    }

    @Override
    public List<Category> getCategory() {
        return categoryDao.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookDao.save(book);
    }

    @Override
    public void updateBook(String originBook, Book book) {
        bookDao.save(book);
    }
}
