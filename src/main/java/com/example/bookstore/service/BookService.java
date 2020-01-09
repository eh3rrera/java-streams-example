package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;

import java.util.Comparator;
import java.util.stream.Stream;

public class BookService {
    public Comparator<Book> priceComparator = Comparator.comparing(Book::getPrice);

    public Book findCheaperBookInOrder(Order order) {
        Stream<Book> stream = order.books();

        return stream.min(priceComparator).get();
    }
}
