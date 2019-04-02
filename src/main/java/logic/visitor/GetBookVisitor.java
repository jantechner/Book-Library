package logic.visitor;

import logic.domain.Book;
import logic.domain.Library;
import logic.util.JsonUtils;

public class GetBookVisitor implements Visitor{

    private String isbn;

    public GetBookVisitor(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String visit(Library library) {
        Book foundBook = null;
        for (Book book : library.books()) {
            if (book.getIsbn().equals(isbn)) {
                foundBook = book;
            }
        }
        return JsonUtils.toJsonString(foundBook);
    }
}
