package rest;

import logic.domain.Library;
import logic.visitor.GetBookVisitor;
import logic.visitor.GetBooksFromCategoryVisitor;
import logic.visitor.GetRatingsVisitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookLibraryController {

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/isbn/{number}", produces = "application/json")
    public ResponseEntity<String> getBookByIsbnNumber(@PathVariable String number) {
        String bookString = new GetBookVisitor(number).visit(Library.get());
        if (bookString != null) {
            return new ResponseEntity<>(bookString, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/category/{name}", produces = "application/json")
    public ResponseEntity<String> getBooksByCategory(@PathVariable String name) {
        String booksString = new GetBooksFromCategoryVisitor(name).visit(Library.get());
        return new ResponseEntity<>(booksString, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/ratings", produces = "application/json")
    public ResponseEntity<String> getRatings() {
        String ratings = new GetRatingsVisitor().visit(Library.get());
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

}

