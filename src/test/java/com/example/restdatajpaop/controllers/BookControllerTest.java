package com.example.restdatajpaop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment  =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

//    resttemplate builder para lanzar las peticiones
    private TestRestTemplate testRestTemplate;

    @Autowired
//    nos permite construir el anterior, el testRestTemplate
private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;


    @BeforeEach
    void setUp() {
        restTemplateBuilder =  restTemplateBuilder.rootUri("http://localhost:"+port);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }
}