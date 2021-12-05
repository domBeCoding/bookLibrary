package uk.codingplayroom.bls.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HealthControllerTest {

    @Autowired
    HealthController healthController;

    @Test
    public void whenPrivateStatusRequested_thenOkReturned() {
        ResponseEntity<String> responseEntity = healthController.getStatus();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo("OK");
    }
}
