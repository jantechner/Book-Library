package logic.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Book {

    public static List<Book> booksList = new ArrayList<>();

    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private transient long publishedDate;
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private double averageRating;
    private String[] authors;
    private String[] categories;

    public void show() {
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
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublishedDate(long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
