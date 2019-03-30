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

//    public void show() {
//        System.out.println("------------------------------------");
//        System.out.println(isbn + "\n" + title + "\n" +  subtitle + "\n" + publisher + "\n" + publishedDate + "\n" +
//                description + "\n" + pageCount + "\n" + thumbnailUrl + "\n" + language + "\n" + previewLink + "\n" +
//                averageRating);
//        for (String author: authors) {
//            System.out.print(author + " ");
//        }
//        System.out.println();
//        for (String category: categories) {
//            System.out.print(category + " ");
//        }
//        System.out.println();
//        System.out.println("------------------------------------");
//        System.out.println();
//    }

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
