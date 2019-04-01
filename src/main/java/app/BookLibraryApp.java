package app;

import logic.util.BookDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"rest"})

public class BookLibraryApp {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(BookLibraryApp.class);
        try {
            BookDownloader.getLibrary(args);
            SpringApplication.run(BookLibraryApp.class, args);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}