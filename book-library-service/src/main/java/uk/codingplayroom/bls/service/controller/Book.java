package uk.codingplayroom.bls.service.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Book {
    private String title;
    private String author;
    private int pages;
}