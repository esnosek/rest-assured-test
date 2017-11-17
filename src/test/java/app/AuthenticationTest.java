package app;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthenticationTest {

    private static String TOKEN = "";
    @BeforeClass
    public void setUp(){
        RestAssured.port = Integer.valueOf("80");
        RestAssured.basePath = "/oauth2";
        RestAssured.baseURI = "http://brentertainment.com";
    }

    @Test
    public void getAuthenticationTokenTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "password");
        parameters.put("client_id", "demoapp");
        parameters.put("client_secret", "demopass");
        parameters.put("username", "demouser");
        parameters.put("password", "testpass");

        TOKEN = given()
                .formParams(parameters)
                .post("/lockdin/token")
                .then()
                .statusCode(200)
                .extract().path("access_token");
        System.out.println("token is: " + TOKEN);
    }

    @Test(dependsOnMethods = "getAuthenticationTokenTest")
    public void getResourcesUsingTokenTest() {
        given()
                .auth().preemptive().oauth2(TOKEN)
                .get("/client/request_resource")
                .then()
                .body(containsString("Resource Request Complete!"));
    }
}
