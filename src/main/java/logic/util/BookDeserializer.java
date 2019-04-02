package logic.util;

import com.google.gson.*;
import logic.domain.Book;
import logic.domain.BookBuilder;
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

        BookBuilder bookBuilder = new BookBuilder();

        setISBN(bookBuilder, volumeinfo, jsonElement);
        setPublishedDate(bookBuilder, volumeinfo);

        if (volumeinfo.get("imageLinks") != null) {
            bookBuilder.add("thumbnailUrl", volumeinfo.get("imageLinks").getAsJsonObject().get("thumbnail").getAsString());
        }

        String[] fields = {"title", "subtitle", "publisher", "description", "language", "previewLink",};
        for (String field : fields) {
            if (volumeinfo.get(field) != null) {
                bookBuilder.add(field, volumeinfo.get(field).getAsString());
            }
        }

        String[] multipleFields = {"authors", "categories"};
        for (String field : multipleFields) {
            if (volumeinfo.get(field) != null) {
                List<String> list = new ArrayList<>();
                volumeinfo.get(field).getAsJsonArray().forEach(element -> list.add(element.getAsString()));
                String[] array = list.toArray(new String[0]);
                bookBuilder.add(field, array);
            }
        }

        String fieldName = "pageCount";
        if (volumeinfo.get(fieldName) != null) {
            bookBuilder.add(fieldName, volumeinfo.get(fieldName).getAsInt());
        }

        fieldName = "averageRating";
        if (volumeinfo.get(fieldName) != null) {
            bookBuilder.add(fieldName, volumeinfo.get(fieldName).getAsDouble());
        }

        return bookBuilder.createBook();
    }

    private void setISBN(BookBuilder bookBuilder, JsonObject volumeinfo, JsonElement jsonElement) {
        JsonArray identifiers = volumeinfo.get("industryIdentifiers").getAsJsonArray();
        boolean foundISBN13 = false;
        if (identifiers != null) {
            for (JsonElement identifier : identifiers) {
                if (identifier.getAsJsonObject().get("type").getAsString().equals("ISBN_13")) {
                    bookBuilder.add("isbn", identifier.getAsJsonObject().get("identifier").getAsString());
                    foundISBN13 = true;
                }
            }
            if (!foundISBN13) {
                bookBuilder.add("isbn", jsonElement.getAsJsonObject().get("id").getAsString());
            }
        }
    }

    private void setPublishedDate(BookBuilder bookBuilder, JsonObject volumeinfo) {
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
                bookBuilder.add(fieldName, dateFormat.parse(dateString).getTime());
            } catch (ParseException pe) {
                LoggerFactory.getLogger(BookDeserializer.class).error(pe.getMessage());
            }
        }

    }
}
