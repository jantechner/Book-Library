package logic.domain;

import logic.visitor.Visitable;
import logic.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements Visitable {

    private static Library library = new Library();

    public static Library get() {
        return library;
    }

    private Library() {
    }

    private List<Book> books = new ArrayList<>();

    public void add(List<Book> books) {
        this.books.addAll(books);
    }

    public void add(Book book) {
        books.add(book);
    }

    public List<Book> books() {
        return books;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
