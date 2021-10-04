package com.example.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

class BaseTestClass {

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(FraudController.class);
    }
}