package com.bookmngsys.domain;

import com.bookmngsys.entity.Book;
import com.bookmngsys.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author Newway
 */
@Getter
@Setter
public class BookDto {
    private String isbn;
    private int categoryId;
    private String categoryName;
    private String bookName;
    private String author;
    private String publisher;
    private Date publishDate;
    private float unitPrice;
    private String imgUrl;
    private String summary;
    private int allNum;
    private String tags;
    private int lastNum;
    private int status = 1;

    public static Book convertToBook(BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        Category category = new Category();
        category.setId(bookDto.categoryId);
        category.setCategoryName(bookDto.categoryName);
        book.setCategory(category);
        return book;
    }

    public static BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        bookDto.setCategoryName(book.getCategory().getCategoryName());
        bookDto.setCategoryId(book.getCategory().getId());
        return bookDto;
    }
}
