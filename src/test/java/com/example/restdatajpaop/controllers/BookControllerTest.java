package com.example.restdatajpaop.controllers;

import com.example.restdatajpaop.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment  =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

//    resttemplate builder para lanzar las peticiones
    private TestRestTemplate testRestTemplate;

    @Autowired
//    nos permite construir el anterior, el testRestTemplate
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;


    //permite construir el objeto para hacer solicitues
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        String url = "http://localhost:" + port;
        ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books", Book[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());

        Arrays.asList(response.getBody());
        List<Book> books = Arrays.asList(response.getBody());
        System.out.println("books = " + books.size());
    }

    @Test
    void findById() {
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/books/1", Book.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
//        forma 1
//        headers.set("Content-Type", "application/json");
//        forma 2
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String body = """
                 {
                     "author": "Gabriel Garcia Marquez",
                     "description": "about life",
                     "online": true,
                     "price": 50.0,
                     "releaseDate": "2018-11-01",
                     "title": "libro prueba"
                 }
                """;

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);
        Book result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("libro prueba", result.getTitle());
    }
}