package logic.domain;

import com.google.gson.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class Book {

    public static List<Book> booksList = new ArrayList<>();

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

    public String toJsonString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public void show() {
        System.out.println("------------------------------------");
        System.out.println(isbn + "\n" + title + "\n" +  subtitle + "\n" + publisher + "\n" + publishedDate + "\n" +
                description + "\n" + pageCount + "\n" + thumbnailUrl + "\n" + language + "\n" + previewLink + "\n" +
                averageRating);
        for (String author: authors) {
            System.out.print(author + " ");
        }
        System.out.println();
        for (String category: categories) {
            System.out.print(category + " ");
        }
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println();
    }

    public <T> void set(String name, T value) {
        try {
            Field field = Book.class.getDeclaredField(name);
            field.setAccessible(true);
            field.set(this, value);
        } catch (Exception e) {
            System.out.println("Nie można ustawić zmiennej");
        }
    }

    public String getIsbn() {
        return isbn;
    }


}
