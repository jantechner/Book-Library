package logic.domain;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private Library() {
    }

    private static List<Book> books = new ArrayList<>();

    public static void add(Book book) {
        books.add(book);
    }

    public static List<Book> getBooks() {
        return books;
    }
}
