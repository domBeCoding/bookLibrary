package uk.codingplayroom.bls.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.returnAllBooks());
    }

    @GetMapping("/{bookTitle}")
    public ResponseEntity<Book> getABook(@PathVariable("bookTitle") String requestedTitle) {
        return ResponseEntity.ok(bookService.returnBookByTitle(requestedTitle));
    }
}
