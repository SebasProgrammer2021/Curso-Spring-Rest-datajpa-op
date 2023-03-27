package com.example.restdatajpaop.controllers;

import com.example.restdatajpaop.entities.Book;
import com.example.restdatajpaop.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    //creacion de log para monitoring of the application
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    //    atributo
    private final BookRepository bookRepository;


    //constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //    getter and setter


    //CRUD

    //    BUSCAR TODOS

    /**
     * buscar todos los libros
     *
     * @return List of books
     */
    @GetMapping("/api/books")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Request
     * Response
     *
     * @param bookId
     * @return
     */
    //    BUSCAR X ID
    @GetMapping("/api/books/{bookId}")
    //opción 1
//    public Book findById(@PathVariable Long bookId) {
//    opción 2
    public ResponseEntity<Book> findById(@PathVariable Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent())
            return ResponseEntity.ok(bookOptional.get());
        else
            return ResponseEntity.notFound().build();

//        OPcion 2
//        return bookOptional.orElse(null)
//        return bookOptional.map(ResponseEntity.ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //    CREAR
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders httpHeaders) {
        System.out.println("httpHeaders = " + httpHeaders);
        System.out.println("httpHeaders agent= " + httpHeaders.get("User-Agent"));

        if (book.getId() != null) {
            log.warn("The book already exists");
            System.out.println("The book already exists");
            return ResponseEntity.badRequest().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //    ACTUALIZAR
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
//        si no tiene id, es una creación
        if (book.getId() == null) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }

//        el proceso de actualización
        Optional<Book> bookToUpdate = bookRepository.findById(book.getId());
        if (!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    //    BORRAR
    @DeleteMapping("/api/books/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(bookId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAllBooks() {
        log.info("All books deleted");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
