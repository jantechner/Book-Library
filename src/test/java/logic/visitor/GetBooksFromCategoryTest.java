package logic.visitor;

import logic.domain.Book;
import logic.domain.BookBuilder;
import logic.domain.Library;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetBooksFromCategoryTest {

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

            Book book3 = new BookBuilder()
                    .add("isbn", "9789793780146")
                    .add("averageRating", 4.5)
                    .add("authors", new String[]{"Lewis Carroll", "John ABC"})
                    .createBook();
            Library.get().add(book3);

            setUpIsDone = true;
        }
    }

    @Test
    public void findBookFromCategory1() {
        String expected =
                "[{\"isbn\":\"123456789\",\"averageRating\":5.0,\"categories\":[\"Computers\",\"Java\"]}," +
                "{\"isbn\":\"biMiAQAAIAAJ\",\"categories\":[\"Computers\",\"C++\"]}]";
        String real = new GetBooksFromCategoryVisitor("Computers").visit(Library.get());
        assertEquals(expected, real);
    }

    @Test
    public void findBookFromCategory2() {
        String expected = "[]";
        String real = new GetBooksFromCategoryVisitor("Religion").visit(Library.get());
        assertEquals(expected, real);
    }

    @Test
    public void findBookFromCategory3() {
        String expected = "[]";
        String real = new GetBooksFromCategoryVisitor("").visit(Library.get());
        assertEquals(expected, real);
    }

}

