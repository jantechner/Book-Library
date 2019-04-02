package logic.visitor;

import logic.domain.Book;
import logic.domain.Library;
import logic.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class GetBooksFromCategoryVisitor implements Visitor{

    private String requiredCategory;

    public GetBooksFromCategoryVisitor(String requiredCategory) {
        this.requiredCategory = requiredCategory;
    }

    @Override
    public String visit(Library library) {
        List<Book> booksFromCategory = findBooksFromCategory(requiredCategory, library);
        return JsonUtils.toJsonString(booksFromCategory);
    }

    private List<Book> findBooksFromCategory(String requiredCategory, Library library) {
        List<Book> booksFromCategory = new ArrayList<>();
        library.books().forEach(book -> {
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
}
