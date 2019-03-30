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
        Gson gson = new GsonBuilder().create();
        return gson.toJson(book);
    }

    static <T> String toJsonString(List<T> list) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<T>>(){}.getType();
        return gson.toJson(list, listType);
    }

}
