package logic.util;

import com.google.gson.JsonObject;
import logic.domain.Book;

public class BookController {

    public static String getBook(String number) {
        for (Book book: Book.booksList) {
            if (book.getIsbn().equals(number)) {
                return book.toJsonString();
            }
        }
        return null;
    }
}
