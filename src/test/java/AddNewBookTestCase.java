import app.dto.BookDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddNewBookTestCase extends FunctionalTest{

    private Map<String,Object> book = new HashMap<>();
    private String title = "Title of the book";
    private String author = "Author";
    private Integer year = 1999;
    private String id;

    @BeforeClass
    public void setUp(){
        book.put("title", title);
        book.put("author", author);
        book.put("year", year);
    }

    @Test(priority = 1)
    public void verifyAddNewBookToTheLibrary() {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(author);
        bookDto.setTitle(title);
        bookDto.setYear(year);
        id = given().contentType("application/json")
                .body(bookDto)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .assertThat()
                .body("id", not(isEmptyString()))
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("year", equalTo(year))
                .extract().path("id");
    }

    @Test(priority = 2)
    public void verifyBookWasAdded() {
        given().pathParam("id", id)
                .get("books/{id}")
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo(title))
                .body("title", equalTo(title))
                .body("author", equalTo(author))
                .body("year", equalTo(year));
    }

    @AfterClass
    public void after(){
        given().pathParam("id", id)
                .delete("books/{id}")
                .then()
                .statusCode(200);
    }

}
