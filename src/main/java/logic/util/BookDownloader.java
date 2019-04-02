package logic.util.downloader;

import com.google.gson.*;
import logic.domain.Book;
import logic.domain.Library;
import logic.util.BookDeserializer;
import org.springframework.core.annotation.Order;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class BookDownloader {

    private BookDownloader() {}

    public static void getLibrary(String[] args) throws IOException {

        JsonElement file = getJSONFile(args[0]);
        if (args[1].equals("remote")) {
            String url = file.getAsJsonObject().get("requestedUrl").getAsString();
            URLConnection request = connect(url);
            file = getJSON(request);
        }
        createBooks(file);
    }

    private static JsonElement getJSON(URLConnection request) throws IOException {
        return new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
    }

    private static JsonElement getJSONFile(String filepath) throws IOException {
        return new JsonParser().parse(new FileReader(filepath));
    }

    private static URLConnection connect(String url) throws IOException {
        URLConnection request = new URL(url).openConnection();
        request.connect();
        return request;
    }

    private static void createBooks(JsonElement booksJson) {

        JsonArray booksJsonElement = booksJson.getAsJsonObject().getAsJsonArray("items");
        for (JsonElement bookJsonElement : booksJsonElement) {
            Book book = JSON
            Library.get().add(book);
        }
    }

}
