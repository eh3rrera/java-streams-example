package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.service.BookService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class Module1_Tests {
    private Class bookServiceClass;

    private Method cheaperBookMethod;

    private Field comparatorField;

    private Order orderMock;

    private BookService bookService;

    private List<Book> testOrder;

    @Before
    public void setup() {
        try {
            bookServiceClass = Class.forName("com.example.bookstore.service.BookService");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }

        try {
            cheaperBookMethod = bookServiceClass.getMethod("findCheaperBookInOrder", Order.class);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        try {
            comparatorField = bookServiceClass.getDeclaredField("priceComparator");
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
        }

        orderMock = Mockito.mock(Order.class);

        bookService = new BookService();

        testOrder = Arrays.asList(
                new Book("Book 1", 12.99),
                new Book("Book 2", 9.99),
                new Book("Book 3", 19.99)
        );
    }

    @Test
    public void task_1() {
        assertNotNull("Task 1: Method `findCheaperBookInOrder()` doesn't exist in the BookService class.", cheaperBookMethod);
    }

    @Test
    public void task_2() {
        Order order = new Order(1, 42.97, testOrder);
        Order emptyOrder = new Order(2, 0.0, null);

        Stream<Book> resultOrder = null;
        Stream<Book> resultEmptyOrder = null;

        try {
            resultOrder = order.books();
            resultEmptyOrder = emptyOrder.books();
        } catch(NullPointerException e) {
            assertTrue("Task 2: Method `Order.books()` throws a NullPointerException when the order contains no books.", false);
        }

        assertTrue("Task 2: Method `Order.books()` doesn't return a non-empty Stream when the order contains one or more books.",
                resultOrder != null && resultOrder.anyMatch(e -> true));
        assertTrue("Task 2: Method `Order.books()` doesn't return an empty Stream when the order contains no books.",
                resultEmptyOrder != null && !resultEmptyOrder.anyMatch(e -> true));
    }

    @Test
    public void task_3() {
        Mockito.when(orderMock.books()).thenReturn(testOrder.stream());

        assertNotNull("Task 1: Method `findCheaperBookInOrder()` doesn't exist in the BookService class.", cheaperBookMethod);

        try {
            cheaperBookMethod.invoke(bookService, orderMock);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean methodInvoked = false;
        try {
            Mockito.verify(orderMock).books();
            methodInvoked = true;
        } catch (Error e) {
            e.printStackTrace();
        }

        assertTrue("Task 3: Did not call the `books()` method on the `Order` object.",
                methodInvoked);

    }

    @Test
    public void task_4() {
        assertNotNull("Task 4: Field `priceComparator` doesn't exist in the BookService class.",
                comparatorField);
    }

    @Test
    public void task_5() {
        Stream mockStream = Mockito.mock(Stream.class, i -> Optional.of(new Book("", 0.0)));
        Mockito.when(orderMock.books()).thenReturn(mockStream);

        assertNotNull("Task 1: Method `findCheaperBookInOrder()` doesn't exist in the BookService class.", cheaperBookMethod);
        assertNotNull("Task 4: Field `priceComparator` doesn't exist in the BookService class.",
                comparatorField);

        try {
            cheaperBookMethod.invoke(bookService, orderMock);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean methodInvoked = false;
        try {
            Mockito.verify(mockStream).min((Comparator) comparatorField.get(bookService));
            methodInvoked = true;
        } catch (Error e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertTrue("Task 5: Didn't call the `min()` method on the stream of books.",
                methodInvoked);

    }
}
