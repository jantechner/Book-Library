package logic.visitor;

import logic.domain.Book;
import logic.domain.BookBuilder;
import logic.domain.Library;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GetRatingsVisitorTest {

    private static boolean setUpIsDone = false;

    @Before
    public void initialize() {
        if (!setUpIsDone) {
            Library.get().clear();
            Book book1 = new BookBuilder()
                    .add("isbn", "123456789")
                    .add("averageRating", 5.0)
                    .add("categories", new String[]{"Computers", "Java"})
                    .add("authors", new String[]{"John ABC", "George Eliot"})
                    .createBook();
            Library.get().add(book1);

            Book book2 = new BookBuilder()
                    .add("isbn", "biMiAQAAIAAJ")
                    .add("averageRating", 3.5)
                    .add("authors", new String[]{"George Eliot"})
                    .add("categories", new String[]{"Computers", "C++"})
                    .createBook();
            Library.get().add(book2);

            Book book3 = new BookBuilder()
                    .add("isbn", "9789793780146")
                    .add("averageRating", 4.0)
                    .add("authors", new String[]{"Lewis Carroll", "John ABC"})
                    .createBook();
            Library.get().add(book3);

            Book book4 = new BookBuilder()
                    .add("isbn", "97897")
                    .add("authors", new String[]{"Robert Browning", "William Allingham"})
                    .createBook();
            Library.get().add(book4);

            setUpIsDone = true;
        }
    }

    @Test
    public void getRatings1() {
        String expected = "[{\"author\":\"John ABC\",\"averageRating\":4.5}," +
                "{\"author\":\"George Eliot\",\"averageRating\":4.25}," +
                "{\"author\":\"Lewis Carroll\",\"averageRating\":4.0}]";
        String real = new GetRatingsVisitor().visit(Library.get());
        System.out.println(expected);
        System.out.println(real);
        assertEquals(expected, real);
    }
}
