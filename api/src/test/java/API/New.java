package API;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

  @Test(priority = 1)
  public void add() throws  IOException, ParseException, org.json.simple.parser.ParseException {
    String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload.json";
                JSONObject json = Reuse.get(file);
String f = json.toString();
System.out.println(f);
   String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(f).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
   System.out.println(response);
    JsonPath js = new JsonPath(response);
    System.out.println(js.getString("place_id"));
    file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload2.json";
 
                json = (JSONObject) Reuse.get(file);
                FileWriter fileWriter = new FileWriter(file,false);
                fileWriter.write(json.toString().replace(json.get("place_id").toString(), js.getString("place_id")));
                fileWriter.close();
}
  @Test(priority = 2)
  public void update() throws IOException, org.json.simple.parser.ParseException{
    String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload2.json";
    JSONParser parser = new JSONParser();      
        FileReader fileReader = new FileReader(file);
                JSONObject json = (JSONObject) parser.parse(fileReader);
String f = json.toString();
given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(f).when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
  }
  @Test
  public void get(){
    String gett = given().queryParam("key", "qaclick123").queryParam("place_id", "4bf0f7e4a1095cec0a978fddb8e13396").when().get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
    JsonPath js = Reuse.getJsonPath(gett);
    String actual = js.getString("address");
    Assert.assertEquals(actual, "70 Summer walk, USA");
  }
  @Test
  public void test() throws IOException, org.json.simple.parser.ParseException{
    String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload3.json";
                JSONObject json = Reuse.get(file);
String f = json.toString();
    JsonPath js = Reuse.getJsonPath(f);
  int count = js.getInt("courses.size()");
  int price = 0;
    for (int i = 0; i < count; i++) {
    price = price + js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
    
  }
  int purchaseAmount = js.getInt("dashboard.purchaseAmount");
  Assert.assertEquals(price, purchaseAmount);
    
  }
  @Test
  public void playaround() throws IOException, org.json.simple.parser.ParseException{
     String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload3.json";
   JSONObject js = Reuse.get(file);
   JsonPath j = Reuse.getJsonPath(js.toString());
   int count = j.getInt("courses.size()");
   for (int i = 0; i < count; i++) {
    System.out.println(j.getString("courses["+i+"].title"));
    System.out.println(j.getInt("courses["+i+"].price"));
    
   }

  }
  @Test
  public void playaround2() throws IOException, org.json.simple.parser.ParseException{
     String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload.json";
   JSONObject js = Reuse.get(file);
   String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(js.toString()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
    System.out.println(response);
   JsonPath j = Reuse.getJsonPath(response);
   String place_id = j.getString("place_id");
    System.out.println(j.getString("place_id"));
    String resonse2 = given().queryParam("key", "qaclick123").queryParam("place_id", place_id).when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
    System.out.println(resonse2);
  } 
}
