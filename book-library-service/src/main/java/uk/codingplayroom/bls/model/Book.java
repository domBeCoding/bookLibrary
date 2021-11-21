package uk.codingplayroom.bls.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor
public class Book {

    @Id
    private int bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PAGES")
    private int pages;

    @Column(name = "GENRE")
    private String genre;
}