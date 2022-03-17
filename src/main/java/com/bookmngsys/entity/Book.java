package com.bookmngsys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author Newway
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
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
}
