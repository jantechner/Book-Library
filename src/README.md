### Book Library Application 

Book Library Application is a book library management platform. It enables user to get information about the books stored in the library (loaded from file or downloaded from Google Books API) by using specific endpoints provided by the WEB API. The user can find information about the specific book, list all books from given category and finally get list of authors ratings in descending order. All responses are sent in JSON.

##### Frameworks and tools

The app is written in Java using Spring Boot framework and REST architectural style. It managed by Maven project management tool. Endpoints are covered with unit tests held by JUnit4 library. Additionally the syntax in checked with SonarLint extension. The JSON files processing (serialization and deserialization) is done by GSON library privided by Google.

#####Design Patterns

In the app there are 3 Design Patterns used:

- Builder Pattern - to conveniently create Book objects consisted of many fields (also optional) and to separate object creation from the object itself
- Singleton Pattern - to create only one Library object holding all available Books and to provide easy access to it
- Visitor Pattern - to separate objects processing from the objects and allow for adding new functionalities to the app without changing the structure of objects

#####Running the application

To run the applicaton firstly run `mvn clean package` command. Then simply run `java -jar target/BookLibrary.jar src/main/resources/books.json file` (reading books from file) or `java -jar target/BookLibrary.jar src/main/resources/books.json remote` (reading book from Google Books API). When the program starts, the WEB API is avaliable in the browser under `localhost:8080` domain.

The endpoints are:

- `localhost:8080/isbn/{ISBNnumber}` - find a book identified by the `{ISBNnumber}`
- `localhost:8080/category/{name}` - list all the books from the `{name}` category 
- `localhost:8080/ratings` - get the list of authors ratings in descending order