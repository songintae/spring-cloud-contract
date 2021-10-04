package com.example.consumer;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = {"com.example:producer:+:stubs:8085"}
)
class FraudRestTest {
    @Test
    void validate_fraud_stub() throws Exception {
        //Given
        String contents = "{\n" +
                "   \"clientId\":\"9183197499\",\n" +
                "   \"loanAmount\":99999\n" +
                "}";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(contents, httpHeaders);

        //When
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8085/fraudcheck", HttpMethod.PUT, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("{\"fraudCheckStatus\":\"FRAUD\",\"reason\":\"Amount too high\"}");
    }

    @Test()
    void validate_undefinition_request() {
        String contents = "{\n" +
                "   \"clientId\":\"9183197499\",\n" +
                "   \"loanAmount\":99999\n" +
                "}";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(contents, httpHeaders);

        //When & Then
        assertThrows(HttpClientErrorException.class, () -> restTemplate.exchange("http://localhost:8085/v1/fraudcheck", HttpMethod.PUT, request, String.class));
    }
}
