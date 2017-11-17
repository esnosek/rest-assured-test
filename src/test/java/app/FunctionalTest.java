package app;


import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class FunctionalTest {


    @BeforeClass
    public void setUp(){
        RestAssured.port = Integer.valueOf("8080");
        RestAssured.basePath = "/library/";
        RestAssured.baseURI = "http://localhost";
    }

}
