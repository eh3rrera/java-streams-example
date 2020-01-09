package com.example.bookstore.model;

import java.util.List;
import java.util.stream.Stream;

public class Order {
    private long id;

    private double total;

    private List<Book> books;

    public Order(long id, double total, List<Book> books) {
        this.id = id;
        this.total = total;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Stream<Book> books() {
        return books != null ? books.stream() : Stream.empty();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", total=" + total +
                ", books=" + books +
                '}';
    }
}
