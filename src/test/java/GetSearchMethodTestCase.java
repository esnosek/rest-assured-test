import app.dto.BookDto;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class GetSearchMethodTestCase extends FunctionalTest{

    private static List<String> ids = new LinkedList<>();

    @DataProvider(name = "books")
    public static Object[] credentials() {
        BookDto book1 = new BookDto("Ogniem i Mieczem", "Sienkiewicz", 1880);
        BookDto book2 = new BookDto("W Pustyni i w puszczy", "Sienkiewicz", 1870);
        BookDto book3 = new BookDto("Zbrodnia i Kara", "Dostojewski", 1888);
        BookDto book4 = new BookDto("Bracia Karamazow", "Dostojewski", 1875);
        BookDto book5 = new BookDto("Wprowadzenie do algorytmów", "Cormen", 1980);
        return new Object[] { book1, book2, book3, book4, book5};
    }

    @Test(dataProvider = "books", priority = 1)
    public void addBooksToLibrary(BookDto bookDto) {
        String id =given().contentType("application/json")
                .body(bookDto)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", not(isEmptyString()))
                .body("title", equalTo(bookDto.getTitle()))
                .body("author", equalTo(bookDto.getAuthor()))
                .body("year", equalTo(bookDto.getYear()))
                .extract().path("id");
        ids.add(id);
    }

    @Test(priority = 2)
    public void verifyBooksInLibrary() {
        given().formParam("showBooks", true)
                .when()
                .get()
                .then()
                .statusCode(200)
                .assertThat()
                .body("books.id", hasItems(ids.toArray()));
    }
}
