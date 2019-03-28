package logic.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import logic.domain.Book;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDownloader {

    public static void getBooks(String sURL) throws Exception {
        try {
            URLConnection request;
            request = connect(sURL);
            JsonElement booksJson = getJSON(request);
            createBooks(booksJson);
        } catch (IOException e) {
            throw e;
        }
    }

    private static URLConnection connect(String sURL) throws IOException {
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        return request;
    }

    private static JsonElement getJSON(URLConnection request) throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(new InputStreamReader((InputStream) request.getContent()));
        return element;
    }

    private static void createBooks(JsonElement booksJson) throws ParseException {
        Gson gson = new Gson();
        JsonArray booksJsonElement = booksJson.getAsJsonObject().getAsJsonArray("items");
        for (JsonElement bookJsonElement : booksJsonElement) {
            JsonElement bookMeta = bookJsonElement.getAsJsonObject().get("volumeInfo");
            Book book = gson.fromJson(bookMeta, Book.class);
            book.setIsbn(getISBN(bookJsonElement));
            book.setPublishedDate(getPublishedDate(bookMeta));
            book.setThumbnailUrl(getThumbnailUrl(bookMeta));
            Book.booksList.add(book);
        }
    }

    private static String getISBN(JsonElement bookJson) {
        JsonArray identifiers = bookJson.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("industryIdentifiers").getAsJsonArray();
        for (JsonElement identifier : identifiers) {
            if (identifier.getAsJsonObject().get("type").getAsString().equals("ISBN_13")) {
                return identifier.getAsJsonObject().get("identifier").getAsString();
            }
        }
        return bookJson.getAsJsonObject().get("id").getAsString();
    }

    private static long getPublishedDate(JsonElement bookMeta) throws ParseException {
        try {
            String dateString = bookMeta.getAsJsonObject().get("publishedDate").getAsString();
            DateFormat dateFormat;
            switch (dateString.length()) {
                case 4:
                    dateFormat = new SimpleDateFormat("yyyy");
                    break;
                case 7:
                    dateFormat = new SimpleDateFormat("yyyy-MM");
                    break;
                default:
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            Date date = dateFormat.parse(dateString);
            long unixTime = date.getTime() / 1000;
            return unixTime;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    private static String getThumbnailUrl(JsonElement bookMeta) {
        try {
            return bookMeta.getAsJsonObject().get("imageLinks").getAsJsonObject().get("thumbnail").getAsString();
        } catch (NullPointerException e) {
            return "";
        }

    }

}
