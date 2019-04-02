package logic.domain;

import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;

public class BookBuilder {

    String isbn = null;
    String title = null;
    String subtitle = null;
    String publisher = null;
    Long publishedDate = null;
    String description = null;
    Integer pageCount = null;
    String thumbnailUrl = null;
    String language = null;
    String previewLink = null;
    Double averageRating = null;
    String[] authors = null;
    String[] categories = null;

    public BookBuilder() {
    }

    public BookBuilder add(String name, Object value) {
        try {
            Field field = BookBuilder.class.getDeclaredField(name);
            field.setAccessible(true);
            field.set(this, value);
        } catch (Exception e) {
            LoggerFactory.getLogger(Book.class).error(e.getMessage());
        }
        return this;
    }

    public Book createBook() {
        return new Book(this);
    }


}
