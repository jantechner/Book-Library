package rest;

import com.google.gson.JsonObject;
import logic.domain.Book;
import logic.util.BookController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookLibraryController {

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(path = "/isbn/{number}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> isbnNuber(@PathVariable String number) {

        String bookString = BookController.getBook(number);
        if (bookString != null) {
            return new ResponseEntity<>(bookString, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("404 Page not found ", HttpStatus.OK);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/category/{name}", method = RequestMethod.GET, produces = "application/json")
    public void categoryName(@PathVariable String name) {

        System.out.println("category " + name);

    }
}

