package uk.codingplayroom.bls.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/all")
    public ResponseEntity<String> getAllBooks(){
        return (ResponseEntity<String>) ResponseEntity.ok("OK");
    }
}
