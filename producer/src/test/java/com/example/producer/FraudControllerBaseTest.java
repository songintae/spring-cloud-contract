package com.example.producer;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class FraudControllerBaseTest {

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(FraudController.class);
    }
}