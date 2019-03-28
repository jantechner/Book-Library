package app;

import logic.util.BookDownloader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"rest"})


public class BookLibraryApp {

    public static void main(String[] args) {
        try {
            String sURL = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40";
            BookDownloader.getBooks(sURL);
            SpringApplication.run(BookLibraryApp.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}