import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FacebookEndpointsTest {

    @Test
    public void testIfGetFacebookAccountIsOk() {
//        get("http://localhost:8080/rest/facebook/5a08ed7495bc09c5264948cd")
//                .then()
//                .statusCode(200)
//                .assertThat()
//                .body("name", equalTo("tego"));
    }

    @Test
    public void testIfGetFacebookAccountNotFound() {
//        get("http://localhost:8080/rest/facebook/1234")
//                .then()
//                .statusCode(404);
    }

}
