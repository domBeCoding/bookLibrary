package uk.codingplayroom.bls.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMetrics
public class AuthValidationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/private/status", "/private/info", "/private/env", "/private/metrics"})
    public void whenAdminRequestsPrivateEndpoints_thenAccessIsPermitted(String privateEndpoint) throws Exception {
        mockMvc.perform(get(privateEndpoint)
                .header("Authorization", "Basic QWRtaW46UGFzc3dvcmQ="))
                .andExpect(status().is(200));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/private/status", "/private/info", "/private/env", "/private/metrics"})
    public void whenUserWithInvalidCredentialsRequestsPrivateEndpoint_thenAccessIsDenied(String privateEndpoint) throws Exception {
        mockMvc.perform(get(privateEndpoint))
                .andExpect(status().is(401));
    }
}
