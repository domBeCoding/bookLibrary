package uk.codingplayroom.bls.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/private/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("OK");
    }
}
