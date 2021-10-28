package uk.codingplayroom.bls.service.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookControllerTest {
    public static final String HARRY_POTTER = "harrypotter";

    @Autowired
    private BookController bookController;

    private static List<Book> bookList = new ArrayList<>();
    private static Book BOOK1;
    private static Book BOOK2;

    @BeforeAll
    public static void setUp() {
        BOOK1 = new Book("harrypotter", "jkrowling", 2500);
        BOOK2 = new Book("percyjackson", "rickriodian", 2500);

        bookList.add(BOOK1);
        bookList.add(BOOK2);
    }

    @Test
    public void whenAllBooksRequested_ReturnsOK() {
        ResponseEntity<List<Book>> responseEntity = bookController.getAllBooks();

        Assertions.assertThat(responseEntity.getStatusCodeValue())
                .isEqualTo(200);
        Assertions.assertThat(responseEntity.getBody())
                .usingRecursiveComparison()
                .isEqualTo(bookList);
    }

    @Test
    public void whenABookIsRequested_thenInformationIsReturned() {
        ResponseEntity<Book> responseEntity1 = bookController.getABook(HARRY_POTTER);

        Assertions.assertThat(responseEntity1.getBody())
                .usingRecursiveComparison()
                .isEqualTo(BOOK1);
    }
}
