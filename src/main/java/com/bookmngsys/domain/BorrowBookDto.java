package com.bookmngsys.domain;

import com.bookmngsys.entity.BorrowBook;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author Newway
 */
@Getter
@Setter
public class BorrowBookDto {
    private int id;
    private String isbn;
    private Date borrowDate;
    private Date returnDate;
    private float fine = 0;
    private int status = 1;
    private int readerId;

    public static BorrowBook convertToBorrowBook(BorrowBookDto borrowBookDto) {
        BorrowBook borrowBook = new BorrowBook();
        BeanUtils.copyProperties(borrowBookDto, borrowBook);
        return borrowBook;
    }

    public static BorrowBookDto convertToBorrowBookDto(BorrowBook borrowBook) {
        BorrowBookDto borrowBookDto = new BorrowBookDto();
        BeanUtils.copyProperties(borrowBook, borrowBookDto);
        borrowBookDto.setIsbn(borrowBook.getBook().getIsbn());
        borrowBookDto.setReaderId(borrowBook.getReader().getId());
        return borrowBookDto;
    }
}
