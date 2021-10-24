package uk.codingplayroom.bls.service.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void whenAllBooksEndpointIsCalled_ReturnOK() {
        ResponseEntity<String> responseEntity = bookController.getAllBooks();
        String body = responseEntity.getBody();
        int code = responseEntity.getStatusCodeValue();

        Assertions.assertThat(code).isEqualTo(200);
        Assertions.assertThat(body).isEqualTo("OK");
    }


}
