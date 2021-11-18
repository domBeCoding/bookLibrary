package uk.codingplayroom.bls.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.codingplayroom.bls.model.Book;
import uk.codingplayroom.bls.model.BookGenre;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    private static final String FAKE_BOOK = "fake_title";
    private Book emptyBook;
    private List<Book> bookList = new ArrayList<>();

    Book book1 = new Book("harrypotter", "jkrowling", 2500, BookGenre.FANTASY);
    Book book2 = new Book("percyjackson", "rickriodian", 2500, BookGenre.FANTASY);

    @BeforeEach
    public void setUp() {
        bookList.add(book1);
        bookList.add(book2);
    }

    @Mock
    private BookService underTest;

    @Test
    public void whenAFakeBookIsRequested_thenReturnEmptyBook() {
        when(underTest.returnBookByTitle(FAKE_BOOK)).thenReturn(emptyBook);
        Book returnedBook = underTest.returnBookByTitle(FAKE_BOOK);

        Assertions.assertThat(returnedBook).isEqualTo(emptyBook);
    }

    @Test
    public void whenAllBooksAreRequested_thenReturnExpectedBooks() {
        when(underTest.returnAllBooks()).thenReturn(bookList);
        List<Book> returnedBookList = underTest.returnAllBooks();

        Assertions.assertThat(returnedBookList).isEqualTo(bookList);
    }


}
