package logic.util;

import com.google.gson.reflect.TypeToken;
import logic.domain.Book;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookController {

    private BookController() {
        //not called
    }

    public static String getBook(String number) {
        for (Book book: Book.booksList) {
            if (book.getIsbn().equals(number)) {
                return JSONUtils.toJsonString(book);
            }
        }
        return null;
    }

    public static String getBooksFromCategory(String requiredCategory) {
        List<Book> booksFromCategory = new ArrayList<>();
        for (Book book: Book.booksList) {
            book.getCategories().forEach(category -> {
                if (category.equals(requiredCategory)) {
                    booksFromCategory.add(book);
                }
            });
        }
        return JSONUtils.toJsonString(booksFromCategory);
    }
}
