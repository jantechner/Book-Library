package logic.util;

import logic.domain.Library;
import logic.domain.Rating;
import logic.domain.Book;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryController {

    private LibraryController() {
    }

    public static String getBookAsString(String number) {
        Book foundBook = findBook(number);
        return JSONUtils.toJsonString(foundBook);
    }

    public static String getBooksFromCategoryAsString(String requiredCategory) {
        List<Book> booksFromCategory = findBooksFromCategory(requiredCategory);
        return JSONUtils.toJsonString(booksFromCategory);
    }

    public static String getAuthorsRatingsAsString() {
        Map<String, Rating> map = findAuthorsRatings();
        List<Rating> authorsRatingList = convertMapToList(map);
        return JSONUtils.toJsonString(authorsRatingList);
    }

    static Book findBook(String number) {
        for (Book book : Library.getBooks()) {
            if (book.getIsbn().equals(number)) {
                return book;
            }
        }
        return null;
    }

    static List<Book> findBooksFromCategory(String requiredCategory) {
        List<Book> booksFromCategory = new ArrayList<>();
        Library.getBooks().forEach(book -> {
            if (book.getCategories() != null) {
                for (String category : book.getCategories()) {
                    if (category.equals(requiredCategory)) {
                        booksFromCategory.add(book);
                    }
                }
            }
        });
        return booksFromCategory;
    }

    static Map<String, Rating> findAuthorsRatings() {
        Map<String, Rating> map = new HashMap<>();
        Library.getBooks().forEach(book -> {
            if (book.getRating() != null && book.getAuthors() != null) {
                for (String author : book.getAuthors()) {
                    if (!map.containsKey(author)) {
                        map.put(author, new Rating(book.getRating(), author));
                    } else {
                        map.get(author).updateRatings(book.getRating());
                    }
                }
            }
        });
        return map;
    }

    private static List<Rating> convertMapToList (Map<String, Rating> map) {
        return map.entrySet()
                .stream()
                .peek(entry -> entry.getValue().eraseUnnecessaryValues())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
