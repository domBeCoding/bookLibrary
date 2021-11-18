package uk.codingplayroom.bls.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import uk.codingplayroom.bls.model.Book;
import uk.codingplayroom.bls.model.BookGenre;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookControllerTest {
    public static final String HARRY_POTTER = "harrypotter";
    public static final String FAKE_BOOK = "fake";

    @Autowired
    private BookController underTest;

    private static List<Book> bookList = new ArrayList<>();
    private static Book BOOK1;
    private static Book BOOK2;
    private static Book emptyBook;

    @BeforeAll
    public static void setUp() {
        BOOK1 = new Book("harrypotter", "jkrowling", 2500, BookGenre.FANTASY);
        BOOK2 = new Book("percyjackson", "rickriodian", 2500, BookGenre.FANTASY);

        bookList.add(BOOK1);
        bookList.add(BOOK2);
    }

    @Test
    public void whenAllBooksRequested_ReturnsOK() {
        ResponseEntity<List<Book>> responseEntity = underTest.getAllBooks();

        Assertions.assertThat(responseEntity.getStatusCodeValue())
                .isEqualTo(200);
        Assertions.assertThat(responseEntity.getBody())
                .usingRecursiveComparison()
                .isEqualTo(bookList);
    }

    @Test
    public void whenABookIsRequested_thenInformationIsReturned() {
        ResponseEntity<Book> responseEntity1 = underTest.getABook(HARRY_POTTER);

        Assertions.assertThat(responseEntity1.getBody())
                .usingRecursiveComparison()
                .isEqualTo(BOOK1);
    }

    @Test
    public void whenANonExistentBookIsRequested_thenReturnEmptyBook() {
        ResponseEntity<Book> responseEntity = underTest.getABook(FAKE_BOOK);

        Assertions.assertThat(responseEntity.getBody())
                .isEqualTo(emptyBook);
    }
}
