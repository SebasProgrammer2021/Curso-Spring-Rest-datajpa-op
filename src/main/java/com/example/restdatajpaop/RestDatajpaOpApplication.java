package com.example.restdatajpaop;

import com.example.restdatajpaop.entities.Book;
import com.example.restdatajpaop.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.restdatajpaop"})
public class RestDatajpaOpApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RestDatajpaOpApplication.class, args);
//        @Bean(name = "bookRepository");
        BookRepository bookRepository = context.getBean(BookRepository.class);


//        CRUD
//        create
        Book book = new Book(null, "Sebastián", "about success", true, 28.00, LocalDate.of(2018, 12, 1), "How to live good");
        Book otherBook = new Book(null, "Sofia", "about success", true, 18.00, LocalDate.of(2018, 11, 1), "How be you");
        bookRepository.save(book);
        bookRepository.save(otherBook);

//        Read
        System.out.println(bookRepository.findAll().size());

//        udpate

//        delete
        bookRepository.deleteById(1L);
        System.out.println(bookRepository.findAll().size());

    }

}
