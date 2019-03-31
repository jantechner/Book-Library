package logic.util;

import com.google.gson.*;
import logic.domain.Book;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookDeserializer implements JsonDeserializer<Book> {
    @Override
    public Book deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {


        JsonObject volumeinfo = jsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject();

        Book book = new Gson().fromJson(jsonElement.getAsJsonObject(), Book.class);

        setISBN(book, volumeinfo, jsonElement);
        setPublishedDate(book, volumeinfo);

        if (volumeinfo.get("imageLinks") != null) {
            book.set("thumbnailUrl", volumeinfo.get("imageLinks").getAsJsonObject().get("thumbnail").getAsString());
        }

        String[] fields = {"title", "subtitle", "publisher", "description", "language", "previewLink",};
        for (String field : fields) {
            if (volumeinfo.get(field) != null) {
                book.set(field, volumeinfo.get(field).getAsString());
            }
        }

        String[] multipleFields = {"authors", "categories"};
        for (String field : multipleFields) {
            if (volumeinfo.get(field) != null) {
                List<String> list = new ArrayList<>();
                volumeinfo.get(field).getAsJsonArray().forEach(element -> list.add(element.getAsString()));
                String[] array = list.toArray(new String[0]);
                book.set(field, array);
            }
        }

        String fieldName = "pageCount";
        if (volumeinfo.get(fieldName) != null) {
            book.set(fieldName, volumeinfo.get(fieldName).getAsInt());
        }

        fieldName = "averageRating";
        if (volumeinfo.get(fieldName) != null) {
            book.set(fieldName, volumeinfo.get(fieldName).getAsDouble());
        }

        return book;
    }

    private void setISBN(Book book, JsonObject volumeinfo, JsonElement jsonElement) {
        JsonArray identifiers = volumeinfo.get("industryIdentifiers").getAsJsonArray();
        if (identifiers != null) {
            for (JsonElement identifier : identifiers) {
                if (identifier.getAsJsonObject().get("type").getAsString().equals("ISBN_13")) {
                    book.set("isbn", identifier.getAsJsonObject().get("identifier").getAsString());
                }
            }
            if (book.getIsbn() == null) {
                book.set("isbn", jsonElement.getAsJsonObject().get("id").getAsString());
            }
        }
    }

    private void setPublishedDate(Book book, JsonObject volumeinfo) {
        String fieldName = "publishedDate";
        if (volumeinfo.get(fieldName) != null) {
            String dateString = volumeinfo.get(fieldName).getAsString();
            DateFormat dateFormat;
            switch (dateString.length()) {
                case 4: dateFormat = new SimpleDateFormat("yyyy");
                    break;
                case 7: dateFormat = new SimpleDateFormat("yyyy-MM");
                    break;
                default: dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            try {
                book.set(fieldName, dateFormat.parse(dateString).getTime() /*/ 1000*/);
            } catch (ParseException pe) {
                LoggerFactory.getLogger(BookDeserializer.class).error(pe.getMessage());
            }
        }
    }
}
