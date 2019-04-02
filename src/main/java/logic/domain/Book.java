package logic.domain;

import com.google.gson.annotations.Expose;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class Book {

    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    private String description;
    private Integer pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private Double averageRating;
    private String[] authors;
    private String[] categories;

    public Book(BookBuilder bookBuilder) {
        this.isbn = bookBuilder.isbn;
        this.title = bookBuilder.title;
        this.subtitle = bookBuilder.subtitle;
        this.publisher = bookBuilder.publisher;
        this.publishedDate = bookBuilder.publishedDate;
        this.description = bookBuilder.description;
        this.pageCount = bookBuilder.pageCount;
        this.thumbnailUrl = bookBuilder.thumbnailUrl;
        this.language = bookBuilder.language;
        this.previewLink = bookBuilder.previewLink;
        this.averageRating = bookBuilder.averageRating;
        this.authors = bookBuilder.authors;
        this.categories = bookBuilder.categories;
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
