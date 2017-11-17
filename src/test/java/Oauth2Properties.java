import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class Oauth2Properties {

    @BeforeClass
    public static void setup() {

        RestAssured.port = Integer.valueOf("80");
        RestAssured.basePath = "/oauth2";
        RestAssured.baseURI = "http://brentertainment.com";
    }

}
