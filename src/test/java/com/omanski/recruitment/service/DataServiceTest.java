package com.omanski.recruitment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DataServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private DataService dataService;

    @Test
    void generateJsons() {
        //given

        //when

        //then
    }

    @Test
    void getBasicData() {
    }

    @Test
    void getSpecifiedData() {
    }

    @Test
    void calculateGivenOperations() {
    }
}