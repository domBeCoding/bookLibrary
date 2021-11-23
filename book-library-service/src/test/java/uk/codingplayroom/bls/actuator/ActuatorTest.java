package uk.codingplayroom.bls.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMetrics
public class ActuatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenInfoRequested_thenReturnExpectedValues() throws Exception {
        mockMvc.perform(get("/private/info"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.app").exists())
                .andExpect(jsonPath("$.app.name").value("Book Library Service"))
                .andExpect(jsonPath("$.app.description").value("Book Library Service - Rental Book Service For Families"))
                .andExpect(jsonPath("$.app.version").exists())
                .andExpect(jsonPath("$.app.java").exists())
                .andExpect(jsonPath("$.git").exists())
                .andExpect(jsonPath("$.config").exists());
    }

    @Test
    public void whenMetricsRequested_thenReturnExpectedValues() throws Exception {
        mockMvc.perform(get("/private/metrics"))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("jvm_memory_committed_bytes")));
    }
}
