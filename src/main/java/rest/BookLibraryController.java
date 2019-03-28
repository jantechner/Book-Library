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
            return new ResponseEntity<>("404 Page not found ", HttpStatus.OK);
        }

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/category/{name}", produces = "application/json")
    public ResponseEntity<String> getBooksByCategory(@PathVariable String name) {
        return new ResponseEntity<>("Categories in progress...", HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/ratings", produces = "application/json")
    public ResponseEntity<String> getRatings() {
        return new ResponseEntity<>("Ratings in progress...", HttpStatus.OK);
    }

}

