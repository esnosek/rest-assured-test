import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BookEndpointsTest extends FunctionalTest{

    // GET METHODS //

    @Test
    public void verifyLibraryNameAndAddress() {
        get().then()
                .statusCode(200)
                .assertThat()
                .body("name", containsString("Super Library"))
                .body("address", containsString("Street 46"))
                .body("books", nullValue());
    }

    @Test
    public void verifyBooksInLibrary() {
        given().formParam("showBooks", true)
                .when()
                .get()
                .then()
                .statusCode(200)
                .assertThat()
                .body("books.id", hasItems("1001","1002","1003","1004","1005"));
    }

    @Test
    public void verifyGetBookById() {
        get("books/1001")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo("Ogniem i Mieczem"))
                .body("author", equalTo("Sienkiewicz"))
                .body("year", equalTo(1880));
    }

    @Test
    public void invalidBookId() {
        get("/books/0009")
                .then()
                .statusCode(404);
    }

    @Test
    public void verifyGetBookByAuthor() {
        given().formParam("author", "Dostojewski")
                .get("books")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", hasSize(2))
                .body("title", hasItems("Zbrodnia i Kara", "Bracia Karamazow"));
    }

    //POST METHOD//

    @Test
    public void verifyAddNewBookToLibrary() {
        Map<String,Object> book = new HashMap<>();
        book.put("title", "New Book");
        book.put("author", "Unknown");
        book.put("year", 2017);

        given().contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", not(isEmptyString()))
                .body("title", equalTo("New Book"))
                .body("author", equalTo("Unknown"))
                .body("year", equalTo(2017));
    }

    //PUT METHOD//

    @Test
    public void verifyPutNewBookToLibrary() {
        Map<String,Object> book = new HashMap<>();
        book.put("title", "New Book");
        book.put("author", "Unknown");
        book.put("year", 2017);

        String id = "11117";

        given().contentType("application/json")
                .body(book)
                .pathParam("id", id)
                .when()
                .put("/books/{id}")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(id))
                .body("title", equalTo("New Book"))
                .body("author", equalTo("Unknown"))
                .body("year", equalTo(2017));

        given().pathParam("id", id)
                .when()
                .delete("/books/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyCannotPutBookInOccupiedPlace() {
        Map<String,Object> book = new HashMap<>();
        book.put("title", "New Book");
        book.put("author", "Unknown");
        book.put("year", 2017);

        String id = "1119";

        given().contentType("application/json")
                .body(book)
                .pathParam("id", id)
                .when()
                .put("/books/{id}")
                .then()
                .statusCode(200);

        given().contentType("application/json")
                .body(book)
                .pathParam("id", id)
                .when()
                .put("/books/{id}")
                .then()
                .statusCode(400);

        given().pathParam("id", id)
                .when()
                .delete("/books/{id}")
                .then()
                .statusCode(200);
    }

    //DELETE METHOD//

    @Test
    public void verifyBookIsRemoved() {
        Map<String,Object> book = new HashMap<>();
        book.put("title", "New Book");
        book.put("author", "Unknown");
        book.put("year", 2017);

        String bookId = given().contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .assertThat()
                .body("title", equalTo("New Book"))
                .extract()
                .path("id");

        given().pathParam("id", bookId)
                .when()
                .delete("/books/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyIfDeleteNonExistBook() {
        Map<String,Object> book = new HashMap<>();
        book.put("title", "New Book");
        book.put("author", "Unknown");
        book.put("year", 2017);

        String bookId = given().contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .assertThat()
                .body("title", equalTo("New Book"))
                .extract()
                .path("id");

        given().pathParam("id", bookId)
                .when()
                .delete("/books/{id}")
                .then()
                .statusCode(200);

        given().pathParam("id", bookId)
                .when()
                .delete("/books/{id}")
                .then()
                .statusCode(404);
    }


}
