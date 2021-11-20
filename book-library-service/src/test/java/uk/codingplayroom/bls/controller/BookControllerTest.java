package uk.codingplayroom.bls.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import uk.codingplayroom.bls.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class BookControllerTest {
    public static final String HARRY_POTTER_1 = "harry-potter-chamber-of-secrets";
    public static final String HARRY_POTTER_2 = "harry-potter-deathly-hallows";
    public static final String PERCY_JACKSON_1 = "percy-jackson-the-sea-of-monsters";
    public static final String PERCY_JACKSON_2 = "percy-jackson-the-lightening-thief";
    public static final String FAKE_BOOK = "fake";
    public static final String FANTASY = "fantasy";
    public static final String GEORGE_ORWELL = "george-orwell";

    @Autowired
    private BookController underTest;

    private static List<Book> bookList = new ArrayList<>();
    private static List<Book> bookListForFantasyGenre = new ArrayList<>();
    private static List<Book> bookListForGeorgeOrwell = new ArrayList<>();

    private static Book BOOK1;
    private static Book BOOK2;
    private static Book BOOK3;
    private static Book BOOK4;
    private static Book BOOK5;
    private static Book BOOK6;
    private static Book emptyBook;

    @BeforeAll
    public static void setUp() {
        BOOK1 = new Book(1, "harry-potter-chamber-of-secrets", "jk-rowling", 2500, "FANTASY");
        BOOK2 = new Book(2, "harry-potter-deathly-hallows", "jk-rowling", 3000, "FANTASY");
        BOOK3 = new Book(3, "percy-jackson-the-sea-of-monsters", "rick-riordian", 2500, "FANTASY");
        BOOK4 = new Book(4, "percy-jackson-the-lightening-thief", "rick-riordian", 3500, "FANTASY");
        BOOK5 = new Book(4, "1984", "george-orwell", 3500, "SCI-FI");
        BOOK6 = new Book(4, "animal-farm", "george-orwell", 1000, "POLITICAL-SATIRE");

        bookList.add(BOOK1);
        bookList.add(BOOK2);
        bookList.add(BOOK3);
        bookList.add(BOOK4);
        bookList.add(BOOK5);
        bookList.add(BOOK6);

        bookListForFantasyGenre.add(BOOK1);
        bookListForFantasyGenre.add(BOOK2);
        bookListForFantasyGenre.add(BOOK3);
        bookListForFantasyGenre.add(BOOK4);

        bookListForGeorgeOrwell.add(BOOK5);
        bookListForGeorgeOrwell.add(BOOK6);
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

    @ParameterizedTest
    @MethodSource("bookTitleSource")
    public void whenABookIsRequested_thenInformationIsReturned(String bookTitle, Book book) {
        ResponseEntity<Book> responseEntity1 = underTest.getABook(bookTitle);

        Assertions.assertThat(responseEntity1.getBody())
                .usingRecursiveComparison()
                .isEqualTo(book);
    }

    @ParameterizedTest
    @MethodSource("bookGenreSource")
    public void whenAGenreIsRequested_thenAllBooksWithGenreIsReturned(String genre, List<Book> books) {
        ResponseEntity<List<Book>> responseEntity = underTest.getAllBooksForGenre(genre);

        Assertions.assertThat(responseEntity.getStatusCodeValue())
                .isEqualTo(200);
        Assertions.assertThat(responseEntity.getBody())
                .usingRecursiveComparison()
                .isEqualTo(books);
    }

    @Test
    public void whenANonExistentBookIsRequested_thenReturnEmptyBook() {
        ResponseEntity<Book> responseEntity = underTest.getABook(FAKE_BOOK);

        Assertions.assertThat(responseEntity.getBody())
                .isEqualTo(emptyBook);
    }

    private static Stream<Arguments> bookTitleSource() {
        return Stream.of(
                Arguments.of(HARRY_POTTER_1, BOOK1),
                Arguments.of(HARRY_POTTER_2, BOOK2),
                Arguments.of(PERCY_JACKSON_1, BOOK3),
                Arguments.of(PERCY_JACKSON_2, BOOK4)
        );
    }

    private static Stream<Arguments> bookGenreSource() {
        return Stream.of(
                Arguments.of(FANTASY, bookListForFantasyGenre)
        );
    }
}
