package com.bookmngsys.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Newway
 */
@Data
@Entity
@Table(name = "borrow_book")
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "isbn")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;
    private Date borrowDate;
    private Date returnDate;
    private float fine = 0;
    private int status = 1;
}
