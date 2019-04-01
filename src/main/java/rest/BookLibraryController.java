package rest;

import logic.util.LibraryController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookLibraryController {

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/isbn/{number}", produces = "application/json")
    public ResponseEntity<String> getBookByIsbnNumber(@PathVariable String number) {
        String bookString = LibraryController.getBookAsString(number);
        if (bookString != null) {
            return new ResponseEntity<>(bookString, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/category/{name}", produces = "application/json")
    public ResponseEntity<String> getBooksByCategory(@PathVariable String name) {
        String booksString = LibraryController.getBooksFromCategoryAsString(name);
        return new ResponseEntity<>(booksString, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/ratings", produces = "application/json")
    public ResponseEntity<String> getRatings() {
        String ratings = LibraryController.getAuthorsRatingsAsString();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

}

