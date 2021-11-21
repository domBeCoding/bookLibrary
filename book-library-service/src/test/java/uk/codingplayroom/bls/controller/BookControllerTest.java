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
    private static final String SCI_FI = "sci-fi";
    private static final String POLITICAL_SATIRE = "political-satire";
    private static final String GEORGE_ORWELL = "george-orwell";
    private static final String JK_ROWLING = "jk-rowling";
    private static final String RICK_RIORDIAN = "rick-riordian";


    @Autowired
    private BookController underTest;

    private static List<Book> bookList = new ArrayList<>();
    private static List<Book> bookListForFantasyGenre = new ArrayList<>();
    private static List<Book> bookListForSciFiGenre = new ArrayList<>();
    private static List<Book> bookListForPoliticalSatireGenre = new ArrayList<>();
    private static List<Book> bookListForGeorgeOrwell = new ArrayList<>();
    private static List<Book> bookListForJkRowling = new ArrayList<>();
    private static List<Book> bookListForRickRiordian = new ArrayList<>();

    private static Book BOOK1;
    private static Book BOOK2;
    private static Book BOOK3;
    private static Book BOOK4;
    private static Book BOOK5;
    private static Book BOOK6;
    private static Book emptyBook;

    @BeforeAll
    public static void setUp() {
        BOOK1 = new Book(1, "harry-potter-chamber-of-secrets", JK_ROWLING, 2500, FANTASY);
        BOOK2 = new Book(2, "harry-potter-deathly-hallows", JK_ROWLING, 3000, FANTASY);
        BOOK3 = new Book(3, "percy-jackson-the-sea-of-monsters", RICK_RIORDIAN, 2500, FANTASY);
        BOOK4 = new Book(4, "percy-jackson-the-lightening-thief", RICK_RIORDIAN, 3500, FANTASY);
        BOOK5 = new Book(5, "1984", GEORGE_ORWELL, 3500, SCI_FI);
        BOOK6 = new Book(6, "animal-farm", GEORGE_ORWELL, 1000, POLITICAL_SATIRE);

        bookList.add(BOOK1);
        bookList.add(BOOK2);
        bookList.add(BOOK3);
        bookList.add(BOOK4);
        bookList.add(BOOK5);
        bookList.add(BOOK6);

        //Genre
        bookListForFantasyGenre.add(BOOK1);
        bookListForFantasyGenre.add(BOOK2);
        bookListForFantasyGenre.add(BOOK3);
        bookListForFantasyGenre.add(BOOK4);
        bookListForSciFiGenre.add(BOOK5);
        bookListForPoliticalSatireGenre.add(BOOK6);

        //Author
        bookListForGeorgeOrwell.add(BOOK5);
        bookListForGeorgeOrwell.add(BOOK6);
        bookListForJkRowling.add(BOOK1);
        bookListForJkRowling.add(BOOK2);
        bookListForRickRiordian.add(BOOK3);
        bookListForRickRiordian.add(BOOK4);
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
    @MethodSource("bookIdSource")
    public void whenABookIdIsRequested_thenBookIsReturned(int id, Book book) {
        ResponseEntity<Book> responseEntity = underTest.getBookForId(id);

        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(responseEntity.getBody())
                .usingRecursiveComparison()
                .isEqualTo(book);
    }

    @ParameterizedTest
    @MethodSource("bookTitleSource")
    public void whenABookIsRequested_thenBookIsReturned(String bookTitle, Book book) {
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

    @ParameterizedTest
    @MethodSource("bookAuthorSource")
    public void whenAnAuthorIsRequested_thenAllRelevantBooksIsReturned(String author, List<Book> books) {
        ResponseEntity<List<Book>> responseEntity = underTest.getAllBookForAuthor(author);

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

    private static Stream<Arguments> bookIdSource() {
        return Stream.of(
                Arguments.of(1, BOOK1),
                Arguments.of(2, BOOK2),
                Arguments.of(3, BOOK3),
                Arguments.of(4, BOOK4),
                Arguments.of(5, BOOK5),
                Arguments.of(6, BOOK6)
                );
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
                Arguments.of(FANTASY, bookListForFantasyGenre),
                Arguments.of(SCI_FI, bookListForSciFiGenre),
                Arguments.of(POLITICAL_SATIRE, bookListForPoliticalSatireGenre)
        );
    }

    private static Stream<Arguments> bookAuthorSource() {
        return Stream.of(
                Arguments.of(JK_ROWLING, bookListForJkRowling),
                Arguments.of(RICK_RIORDIAN, bookListForRickRiordian),
                Arguments.of(GEORGE_ORWELL, bookListForGeorgeOrwell)
        );
    }
}
