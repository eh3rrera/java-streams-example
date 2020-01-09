## Return a stream of type Book in the Order class

In the class `com.example.bookstore.model.Order`, change the method with the signature `Stream<Book> books()` to return a stream from the `books` attribute in case this attribute is not `null`, or an empty stream otherwise.

## Add a method to the BookService class

In the class `com.example.bookstore.service.BookService`, add a public method with the signature `Book findCheaperBookInOrder(Order order)`. For now, return `null` so the class can compile. Make sure to add the following imports if your IDE doesn’t do it automatically:
```java
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
```

## Get the stream of Book objects from the Order parameter

Inside the method `findCheaperBookInOrder(Order order)`, create a local variable of type `Stream<Book>` and assign the value returned from the method `order.books()` to it. Make sure to add the following import if your IDE doesn’t do it automatically:
```java
import java.util.stream.Stream;
```

## Set a Comparator to compare the price of the books

Add a public field of type `Comparator<Book>` to the class `BookService`. Name it `priceComparator`, and using the static method `Comparator.comparing(Function)`, assign to this field a comparator based on the price of a book (you can use the `getPrice()` method of the `Book` class as a method reference). Make sure to add the following import if your IDE doesn’t do it automatically:
```java
import java.util.Comparator;
```

## Use the min method passing the price comparator
Inside the method `findCheaperBookInOrder(Order order)`, return the value that you get from calling the `min` method on the stream of books passing `priceComparator` as its argument. The `min` method returns an `Optional` of type `Book` so you'll also have to chain a call to `get()` to this method to unwrap the value from the Optional and return a plain `Book` object.
