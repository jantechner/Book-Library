package logic.util;

import logic.domain.Book;
import logic.domain.BookBuilder;
import logic.domain.Library;
import logic.domain.Rating;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LibraryControllerTest {

    private static boolean setUpIsDone = false;
    private static Book book1;
    private static Book book2;
    private static Book book3;
    private static Book book4;

    @Before
    public void initialize() {
        if (!setUpIsDone) {
            book1 = new BookBuilder()
                    .add("isbn", "123456789")
                    .add("averageRating", 5.0)
                    .add("authors", new String[]{"John ABC", "George Eliot"})
                    .add("categories", new String[]{"Computers", "Java"})
                    .createBook();
            Library.add(book1);

            book2 = new BookBuilder()
                    .add("isbn", "biMiAQAAIAAJ")
                    .add("authors", new String[]{"George Eliot"})
                    .add("categories", new String[]{"Computers", "C++"})
                    .createBook();
            Library.add(book2);


            book3 = new BookBuilder()
                    .add("isbn", "9789793780146")
                    .add("averageRating", 4.5)
                    .add("authors", new String[]{"Lewis Carroll", "John ABC"})
                    .createBook();
            Library.add(book3);

            book4 = new BookBuilder()
                    .add("isbn", "97897")
                    .add("authors", new String[]{"Robert Browning", "William Allingham"})
                    .createBook();
            Library.add(book4);

            setUpIsDone = true;
        }
    }

    @Test public void findBook1() {
        assertEquals(book1, LibraryController.findBook("123456789"));
    }

    @Test public void findBook2() {
        assertEquals(book2, LibraryController.findBook("biMiAQAAIAAJ"));
    }

    @Test public void findBookNull1() {
        assertNull(LibraryController.findBook(" "));
    }

    @Test public void findBookNull2() {
        assertNull(LibraryController.findBook("9789793780142"));
    }

    @Test public void getBookAsString1() {
        assertEquals("{\"isbn\":\"123456789\",\"averageRating\":5.0,\"authors\":[\"John ABC\",\"George Eliot\"],\"categories\":[\"Computers\",\"Java\"]}", LibraryController.getBookAsString("123456789"));
    }

    @Test public void getBookAsString2() {
        assertEquals("{\"isbn\":\"97897\",\"authors\":[\"Robert Browning\",\"William Allingham\"]}", LibraryController.getBookAsString("97897"));
    }

    @Test public void getBookAsString3() {
        assertNull(LibraryController.getBookAsString("sdaa"));
    }


    @Test public void findBooksFromCategory1() {
        List<Book> resultList = new ArrayList<>();
        resultList.add(book1);
        resultList.add(book2);
        assertEquals(resultList, LibraryController.findBooksFromCategory("Computers"));
    }

    @Test public void findBooksFromCategory2() {
        List<Book> resultList = new ArrayList<>();
        resultList.add(book1);
        assertEquals(resultList, LibraryController.findBooksFromCategory("Java"));
    }

    @Test public void findBooksFromCategoryNull1() {
        List<Book> resultList = new ArrayList<>();
        assertEquals(resultList, LibraryController.findBooksFromCategory("NoSuchCategory"));
    }

    @Test public void findBooksFromCategoryNull2() {
        List<Book> resultList = new ArrayList<>();
        assertEquals(resultList, LibraryController.findBooksFromCategory(" "));
    }

    @Test
    public void findAuthorsRatings1() {
        Map<String, Rating> map = LibraryController.findAuthorsRatings();
        assertTrue(map.containsKey("John ABC"));
        assertTrue(map.containsKey("George Eliot"));
        assertTrue(map.containsKey("Lewis Carroll"));
        assertFalse(map.containsKey("Robert Browning"));
        assertFalse(map.containsKey("William Allingham"));
        assertEquals((Double) 4.75, map.get("John ABC").getAverageRating());
        assertEquals( (Double) 5.0, map.get("George Eliot").getAverageRating());
        assertEquals( (Double) 4.5, map.get("Lewis Carroll").getAverageRating());
    }

}