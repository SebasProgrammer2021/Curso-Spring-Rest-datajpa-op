package com.example.restdatajpaop.services;

import com.example.restdatajpaop.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {
        BookPriceCalculator calculator = new BookPriceCalculator();
        Book book = new Book(12L, "sebas", "exito", true, 200.34, LocalDate.now(), "viviendo una vida que valga la pena");
        Book bookLargePrice = new Book(350L, "sebas", "exito", true, 310.34, LocalDate.now(), "viviendo una vida que valga la pena");
        double price = calculator.calculatePrice(book);
        double largePrice = calculator.calculatePrice(bookLargePrice);

        System.out.println(price + " price");
        System.out.println(largePrice + " price");

        assert (price > 0);
        assert (largePrice > 0);
//        assertEquals(203.33, price);
    }
}