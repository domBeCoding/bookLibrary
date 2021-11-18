package uk.codingplayroom.bls.ft.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.codingplayroom.bls.ft.components.Client;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HttpClientSteps {

    @Autowired
    private Client client;

    @When("{string} endpoint is polled")
    public void whenEndpointPolled(String endpoint) throws ExecutionException, InterruptedException {
        client.sendGetRequest(endpoint);
    }

    @Then("status of {int} is returned")
    public void returnsStatusOf(int status) {
        assertThat(client.getResponse().getStatus())
                .isEqualTo(status);
    }

    @Then("body of {string} is returned")
    public void returnsStatusOf(String body) {
        assertThat(client.getResponse().getBody())
                .isEqualTo(body);
    }
}
