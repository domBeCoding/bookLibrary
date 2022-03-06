package uk.codingplayroom.bls.generator;

import com.github.javafaker.Faker;
import lombok.Getter;
import uk.codingplayroom.bls.model.Book;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookGenerator {

    private Faker faker = new Faker();
    private List<Book> bookList = new ArrayList<>();

    public Book returnBook() {
        return new Book(faker.number().randomDigitNotZero(),
                faker.book().title(),
                faker.book().author(),
                faker.number().numberBetween(300, 800),
                faker.book().genre());
    }

    public void populateBookList(int amount) {
        for (int i = 0; i < amount; i++) {
            bookList.add(returnBook());
        }
    }
}
