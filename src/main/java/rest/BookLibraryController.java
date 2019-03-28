package rest;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookLibraryController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/isbn/{number}", method = RequestMethod.POST, produces = "application/json")
    public void isbnNuber(@PathVariable String number) {
        System.out.println("isbn " + number);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/category/{name}", method = RequestMethod.POST, produces = "application/json")
    public void categoryName(@PathVariable String name) {

        System.out.println("category " + name);

    }
}

