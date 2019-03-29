package app;

import logic.util.BookDownloader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"rest"})


public class BookLibraryApp {

    public static void main(String[] args) {
        try {
            BookDownloader.getBooks(args[0]);
            SpringApplication.run(BookLibraryApp.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}