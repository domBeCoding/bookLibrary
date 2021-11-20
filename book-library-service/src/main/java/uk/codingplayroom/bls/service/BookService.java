package uk.codingplayroom.bls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.codingplayroom.bls.model.Book;
import uk.codingplayroom.bls.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private List<Book> bookList = new ArrayList<>();
    private Book emptyBook;
    private List<Book> emptyBookList;

//    public List<Book> returnAllBooks() {
//        return bookList;
//    }

//    public Book returnBookByTitle(String requestedTitle) {
//        Optional<Book> book = bookList.stream()
//                .filter((x) -> x.getTitle().equals(requestedTitle))
//                .findFirst();
//
//        return book.orElseGet(() -> emptyBook);
//    }

//    @Bean
//    public void generateBookList() {
//        Book book1 = new Book(1, "harrypotter", "jkrowling", 2500, BookGenre.FANTASY);
//        Book book2 = new Book(2, "percyjackson", "rickriodian", 2500, BookGenre.FANTASY);
//
//        bookList.add(book1);
//        bookList.add(book2);
//    }

    public Book returnBookById(int id) {
        return bookRepository.findById(id).orElse(emptyBook);
    }

    public List<Book> returnAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book returnBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(emptyBook);
    }

    public List<Book> returnBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author).orElse(emptyBookList);
    }

    public Book returnBookByPages(int pages) {
        return bookRepository.findByPages(pages).orElse(emptyBook);
    }

    public List<Book> returnBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre).orElse(emptyBookList);
    }
}
