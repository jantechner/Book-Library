package logic.visitor;

import logic.domain.Book;
import logic.domain.BookBuilder;
import logic.domain.Library;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GetBookVisitorTest {

    private static boolean setUpIsDone = false;

    @Before
    public void initialize() {
        if (!setUpIsDone) {
            Book book1 = new BookBuilder()
                    .add("isbn", "123456789")
                    .add("averageRating", 5.0)
                    .add("categories", new String[]{"Computers", "Java"})
                    .createBook();
            Library.get().add(book1);

            Book book2 = new BookBuilder()
                    .add("isbn", "biMiAQAAIAAJ")
                    .add("categories", new String[]{"Computers", "C++"})
                    .createBook();
            Library.get().add(book2);

            setUpIsDone = true;
        }
    }

    @Test
    public void findBook1() {
        String expected = "{\"isbn\":\"123456789\",\"averageRating\":5.0,\"categories\":[\"Computers\",\"Java\"]}";
        String real = new GetBookVisitor("123456789").visit(Library.get());
        assertEquals(expected, real);
    }

    @Test
    public void findBook2() {
        String expected = "{\"isbn\":\"biMiAQAAIAAJ\",\"categories\":[\"Computers\",\"C++\"]}";
        String real = new GetBookVisitor("biMiAQAAIAAJ").visit(Library.get());
        assertEquals(expected, real);
    }

    @Test
    public void findNoBook1() {
        String real = new GetBookVisitor("").visit(Library.get());
        assertNull(real);
    }

    @Test
    public void findNoBook2() {
        String real = new GetBookVisitor("20399292901").visit(Library.get());
        assertNull(real);
    }
}
