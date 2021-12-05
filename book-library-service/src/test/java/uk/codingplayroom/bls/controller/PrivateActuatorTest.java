package uk.codingplayroom.bls.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@AutoConfigureMetrics
@SpringBootTest
public class PrivateActuatorTest {
    private static final String BOOK_LIBRARY_SERVICE = "Book Library Service";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenMetricsRequested_thenReturnRelevantFields() throws Exception {
        mockMvc.perform(get("/private/metrics"))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("jvm_memory_max_bytes")))
                .andExpect(content().string(containsString("jvm_memory_used_bytes")))
                .andExpect(content().string(containsString("jvm_memory_committed_bytes")));
    }

    @Test
    public void whenBuildInfoRequested_thenReturnRelevantFields() throws Exception {
        mockMvc.perform(get("/private/info"))
                .andExpect(jsonPath("$.app.name").value(BOOK_LIBRARY_SERVICE))
                .andExpect(jsonPath("$.git.branch").exists())
                .andExpect(jsonPath("$.build.group").value("com.codingPlayroom"))
                .andExpect(jsonPath("$.config.appName").value(BOOK_LIBRARY_SERVICE));
    }

}
