package logic.domain;

import com.google.gson.annotations.Expose;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public final class Book {

    @Expose
    private String isbn = null;
    @Expose
    private String title = null;
    @Expose
    private String subtitle = null;
    @Expose
    private String publisher = null;
    @Expose
    private Long publishedDate = null;
    @Expose
    private String description = null;
    @Expose
    private Integer pageCount = null;
    @Expose
    private String thumbnailUrl = null;
    @Expose
    private String language = null;
    @Expose
    private String previewLink = null;
    @Expose
    private Double averageRating = null;
    @Expose
    private String[] authors = null;
    @Expose
    private String[] categories = null;

    public void set(String name, Object value) {
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

    public String[] getCategories() {
        return categories;
    }

    public String[] getAuthors() {
        return authors;
    }

    public Double getRating() {
        return averageRating;
    }
}
