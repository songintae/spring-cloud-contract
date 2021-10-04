package com.example.producer;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

class FraudControllerBaseTest {

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(FraudController.class);
    }
}