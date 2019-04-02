package logic.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import logic.domain.Book;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {

    private JsonUtils() {}

    public static String toJsonString(Book book) {
        if (book != null) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(book);
        } else {
            return null;
        }
    }

    public static <T> String toJsonString(List<T> list) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<T>>(){}.getType();
        return gson.toJson(list, listType);
    }

    public static List<Book> extractBooks(JsonObject jsonFile) {
        List<Book> books = new ArrayList<>();
        Gson gson = new GsonBuilder().registerTypeAdapter(Book.class, new BookDeserializer()).create();
        JsonArray jsonBooksArray = jsonFile.getAsJsonArray("items");
        for (JsonElement jsonBook: jsonBooksArray) {
            books.add(gson.fromJson(jsonBook, Book.class));
        }
        return books;
    }

}
