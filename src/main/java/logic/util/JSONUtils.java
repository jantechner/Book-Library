package logic.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import logic.domain.Book;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class JSONUtils {

    private JSONUtils() {}

    static String toJsonString(Book book) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(book);
    }

    public static String toJsonString(List<Book> books) {
        Gson gson = new GsonBuilder().create();
        Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.toJson(books, bookListType);
    }
}
