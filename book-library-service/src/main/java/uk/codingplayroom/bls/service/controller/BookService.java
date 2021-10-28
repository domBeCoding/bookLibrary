package uk.codingplayroom.bls.service.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private List<Book> bookList = new ArrayList<>();
    private Book emptyBook;

    public List<Book> returnAllBooks() {
        return bookList;
    }

    public Book returnBookByTitle(String requestedTitle) {
        Optional<Book> book = bookList.stream()
                .filter((x) -> x.getTitle().equals(requestedTitle))
                .findFirst();

        return book.orElseGet(() -> emptyBook);
    }

    @Bean
    public void generateBookList() {
        Book book1 = new Book("harrypotter", "jkrowling", 2500);
        Book book2 = new Book("percyjackson", "rickriodian", 2500);

        bookList.add(book1);
        bookList.add(book2);
    }
}
