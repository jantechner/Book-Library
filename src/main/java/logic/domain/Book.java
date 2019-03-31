package logic.domain;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class Book {

    private static List<Book> booksList = new ArrayList<>();

    private String isbn = null;
    private String title = null;
    private String subtitle = null;
    private String publisher = null;
    private Long publishedDate = null;
    private String description = null;
    private Integer pageCount = null;
    private String thumbnailUrl = null;
    private String language = null;
    private String previewLink = null;
    private Double averageRating = null;
    private List<String> authors = new ArrayList<>();
    private List<String> categories = new ArrayList<>();

    public <T> void set(String name, T value) {
        try {
            Field field = Book.class.getDeclaredField(name);
            field.setAccessible(true);
            field.set(this, value);
        } catch (Exception e) {
            LoggerFactory.getLogger(Book.class).error(e.getMessage());
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Double getRating() {
        return averageRating;
    }

    public static List<Book> getList() {
        return booksList;
    }

    public static void add(Book book) {
        booksList.add(book);
    }
}
