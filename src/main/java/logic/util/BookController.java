package logic.util;

import logic.domain.AuthorRating;
import logic.domain.Rating;
import logic.domain.Book;

import java.util.*;
import java.util.stream.Collectors;

public class BookController {

    private BookController() {}

    public static String getBook(String number) {
        for (Book book : Book.getList()) {
            if (book.getIsbn().equals(number)) {
                return JSONUtils.toJsonString(book);
            }
        }
        return null;
    }

    public static String getBooksFromCategory(String requiredCategory) {
        List<Book> booksFromCategory = new ArrayList<>();
        Book.getList().forEach(book ->
            book.getCategories().forEach(category -> {
                if (category.equals(requiredCategory)) {
                    booksFromCategory.add(book);
                }
            })
        );
        return JSONUtils.toJsonString(booksFromCategory);
    }

    public static String getAuthorsRatings() {
        Map<String, Rating> map = new HashMap<>();
        Book.getList().forEach(book -> {
            if (book.getRating() != null) {
                book.getAuthors().forEach(author -> {
                    if (!map.containsKey(author)) {
                        map.put(author, new Rating(book.getRating()));
                    } else {
                        map.get(author).updateRatings(book.getRating());
                    }
                });
            }
        });
        List<AuthorRating> authorsRatingList =  map.entrySet()
                .stream()
                .map(entry -> new AuthorRating(entry.getKey(), entry.getValue().getAverage()))
                .collect(Collectors.toList());
        return JSONUtils.toJsonString(authorsRatingList);
    }
}
