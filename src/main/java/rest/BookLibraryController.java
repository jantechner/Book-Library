package rest;

import logic.util.BookController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookLibraryController {

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/isbn/{number}", produces = "application/json")
    public ResponseEntity<String> getBookByIsbnNumber(@PathVariable String number) {

        String bookString = BookController.getBook(number);
        if (bookString != null) {
            return new ResponseEntity<>(bookString, HttpStatus.OK);
        } else {
            return error404();
        }

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/category/{name}", produces = "application/json")
    public ResponseEntity<String> getBooksByCategory(@PathVariable String name) {
        String booksString = BookController.getBooksFromCategory(name);
        return new ResponseEntity<>(booksString, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/ratings", produces = "application/json")
    public ResponseEntity<String> getRatings() {
        return new ResponseEntity<>("Ratings in progress...", HttpStatus.OK);
    }

    private ResponseEntity<String> error404() {
        return new ResponseEntity<>("404 Page not found ", HttpStatus.OK);
    }

}

