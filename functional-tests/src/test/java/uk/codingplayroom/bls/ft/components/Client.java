package uk.codingplayroom.bls.ft.components;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

@Component
@Data
public class Client {

    HttpClient client = HttpClient.newHttpClient();

    @Value(value = "${service.host}")
    private String host;

    @Value("${service.port}")
    private String port;

    private Response response;

    public void sendGetRequest(String endpoint) throws ExecutionException, InterruptedException {
        HttpRequest httpRequest = createGetRequest(endpoint);
        HttpResponse<String> httpResponse = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString()).get();

        toResponseObject(httpResponse);
    }

    public HttpRequest createGetRequest(String endpoint) {
        URI uri = UriComponentsBuilder.fromUriString(host)
                .port(port)
                .path(endpoint)
                .build()
                .toUri();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return httpRequest;
    }

    public void toResponseObject(HttpResponse httpResponse) {

        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body().toString());

        response = Response.builder()
                .header(httpResponse.headers().toString())
                .body(httpResponse.body().toString())
                .status(httpResponse.statusCode())
                .build();
    }
}
