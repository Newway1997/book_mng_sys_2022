package com.bookmngsys.service;

import com.bookmngsys.entity.Book;
import com.bookmngsys.entity.BorrowBook;
import com.bookmngsys.entity.Category;

import java.util.List;

/**
 * @author Newway
 */
public interface BookService {
    List<Book> getBooks(int curPage, int pageSize, boolean isAdmin);

    Book getBook(String isbn);

    void borrowBook(String isbn, int readerId) throws Exception;

    List<BorrowBook> getBorrowBooks(int id);

    void returnBook(int id, String isbn, int readerId);

    void disableBook(String isbn);

    void enableBook(String isbn);

    List<Category> getCategory();

    void addBook(Book book);

    void updateBook(String originBook, Book book);
}
