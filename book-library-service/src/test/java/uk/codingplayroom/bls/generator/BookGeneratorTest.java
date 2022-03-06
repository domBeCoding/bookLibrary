package uk.codingplayroom.bls.generator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.codingplayroom.bls.model.Book;

import java.util.List;

public class BookGeneratorTest {

    public BookGenerator bookGenerator;
    public Book book;

    @BeforeEach
    public void Setup() {
        bookGenerator = new BookGenerator();
    }

    @Test
    void returnBook_withRandomlyGeneratedFields() {
        book = bookGenerator.returnBook();

        Assertions.assertThat(book).isNotNull();
    }


    @Test
    void returnXAmountOfBooks_withRandomlyGeneratedFields() {
        bookGenerator.populateBookList(5);
        List<Book> bookList = bookGenerator.getBookList();

        bookList.stream()
                .forEach(x -> System.out.println(x.toString()));

        Assertions.assertThat(bookList.size())
                .isEqualTo(5);
        Assertions.assertThat(bookList)
                .isNotEmpty();
    }
}
