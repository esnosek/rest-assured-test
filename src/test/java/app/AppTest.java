package app;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AppTest extends FunctionalTest {

    private static String ID = "";
    private static String ID1 = "";

    @Test(dataProvider = "books", dataProviderClass = BooksDataProvider.class)
    public void postAddBookTest(Map<String, Object> book) {
        given().contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", not(isEmptyString()))
                .body("title", equalTo(book.get("title")))
                .body("author", equalTo(book.get("author")))
                .body("year", equalTo(book.get("year")));
    }

    @Test(dataProvider = "books2", dataProviderClass = BooksDataProvider.class)
    public void postAddBookAndGetIdTest1(Map<String, Object> book) {
        ID1 = given().contentType("application/json")
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .extract().path("id");
    }

    @Test(dataProvider = "books", dataProviderClass = BooksDataProvider.class, dependsOnMethods = "postAddBookAndGetIdTest1")
    public void getId(Map<String, Object> book) {
        given().pathParam("id", ID1).when()
                .get("/books/{id}")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", not(isEmptyString()))
                .body("title", equalTo(book.get("title")))
                .body("author", equalTo(book.get("author")))
                .body("year", equalTo(book.get("year")));
    }

    @Test(dataProvider = "books1", dataProviderClass = BooksDataProvider.class)
    public void addBooksTest(Map<String, Object> book1) {
                given().contentType("application/json")
                        .body(book1)
                        .when()
                        .post("/books")
                        .then()
                        .statusCode(200);
    }

    @Test(dependsOnMethods = "addBooksTest", dataProvider = "titles", dataProviderClass = BooksDataProvider.class)
    public void searchByAuthorTest(List<String> expectedTitles) {
        List<String> titles = given()
                .formParam("author", "Sienkiewicz")
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", hasSize(2)).extract().path("title");

        assertThat(titles, equalTo(expectedTitles));
    }

    @Test
    public void deleteAllBooks() {
        given().contentType("application/json")
                .when().delete("/books")
                .then()
                .statusCode(200);
    }
}
