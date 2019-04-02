package logic.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import logic.domain.Book;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

final class JSONUtils {

    private JSONUtils() {}

    static String toJsonString(Book book) {
        if (book != null) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(book);
        } else {
            return null;
        }
    }

    static <T> String toJsonString(List<T> list) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<T>>(){}.getType();
        return gson.toJson(list, listType);
    }

    static List<Book> extractBooks(JsonElement booksJson) {
        List<Book> books = new ArrayList<>();
        Gson gson = new GsonBuilder().registerTypeAdapter(Book.class, new BookDeserializer()).create();
        JsonArray booksJsonElement = booksJson.getAsJsonObject().getAsJsonArray("items");
        for (JsonElement bookJsonElement: booksJsonElement) {
            books.add(gson.fromJson(bookJsonElement, Book.class));
        }
        return books;
    }

}
