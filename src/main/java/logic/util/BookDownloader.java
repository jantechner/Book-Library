package logic.util;

import com.google.gson.*;
import logic.domain.Book;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class BookDownloader {

    private BookDownloader() {}

    public static void getBooks(String path) throws IOException {
        JsonElement booksJson;
        try {
            URLConnection request = connect(path);
            booksJson = getJSON(request);
        } catch (IOException e) {
            System.out.println("Can't connect to the remote database");
            booksJson = getJSON(path);
        }
        createBooks(booksJson);
    }

    private static URLConnection connect(String sURL) throws IOException {
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        return request;
    }

    private static JsonElement getJSON(URLConnection request) throws IOException {
        return new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
    }

    private static JsonElement getJSON(String filepath) throws IOException {
        return new JsonParser().parse(new FileReader(filepath));
    }

    private static void createBooks(JsonElement booksJson) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Book.class, new BookDeserializer()).create();
        JsonArray booksJsonElement = booksJson.getAsJsonObject().getAsJsonArray("items");
        for (JsonElement bookJsonElement : booksJsonElement) {
            Book book = gson.fromJson(bookJsonElement, Book.class);
            Book.booksList.add(book);
        }
    }

}
