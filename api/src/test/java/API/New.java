package API;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class New {
@BeforeMethod
  public void beforeTest() {
    RestAssured.baseURI="https://rahulshettyacademy.com";
  }
  @AfterMethod
  public void afterTest() {
  }

  @Test
  public void test() throws  IOException, ParseException, org.json.simple.parser.ParseException {
    String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload.json";
    JSONParser parser = new JSONParser();      
        FileReader fileReader = new FileReader(file);
                JSONObject json = (JSONObject) parser.parse(fileReader);
String f = json.toString();
   String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(f).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
   System.out.println(response);
    JsonPath js = new JsonPath(response);
    System.out.println(js.getString("place_id"));
  }
}
