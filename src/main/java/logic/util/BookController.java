package logic.util;

import logic.domain.Book;

public class BookController {

    private BookController() {
        //not called
    }

    public static String getBook(String number) {
        for (Book book: Book.booksList) {
            if (book.getIsbn().equals(number)) {
                return book.toJsonString();
            }
        }
        return null;
    }
}
