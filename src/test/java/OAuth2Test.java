import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class OAuth2Test extends Oauth2Properties{

    private static String accessToken;

    private static Map<String,String> params = new HashMap<>();

    @BeforeClass
    public void setUp(){
        String grantType = "password";
        params.put("grant_type", grantType);
        String clientId = "demoapp";
        params.put("client_id", clientId);
        String clientSecret = "demopass";
        params.put("client_secret", clientSecret);
        String username = "demouser";
        params.put("username", username);
        String password = "testpass";
        params.put("password", password);
    }

    @Test(priority = 1)
    public void verifyGetAccessToken() {
        accessToken = given().formParams(params).post("/lockdin/token").then().extract().path("access_token");
        System.out.println(accessToken);
    }

    @Test(priority = 2)
    public void verifyAccessTokenIsCorrect() {
        given().formParams("token", accessToken).get("/client/request_resource").then().body(containsString("Resource Request Complete!"));
    }
}
