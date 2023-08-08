package API;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class New {
@BeforeMethod
  public void beforeTest() {
    //RestAssured.baseURI="https://rahulshettyacademy.com";
    RestAssured.baseURI="http://216.10.245.166";
  }
  @AfterMethod
  public void afterTest() {
  }

  @Test(priority = 1)
  public void add() throws  IOException, ParseException, org.json.simple.parser.ParseException {
    String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload.json";
               
   String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(new String (Files.readAllBytes(Paths.get(file)))).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
   System.out.println(response);
    JsonPath js = new JsonPath(response);
    System.out.println(js.getString("place_id"));
    file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload2.json";
 
                JSONObject json = (JSONObject) Reuse.get(file);
                FileWriter fileWriter = new FileWriter(file,false);
                fileWriter.write(json.toString().replace(json.get("place_id").toString(), js.getString("place_id")));
                fileWriter.close();
}
  @Test(priority = 2)
  public void update() throws IOException, org.json.simple.parser.ParseException{
    String file = "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload2.json";
    
given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(new String (Files.readAllBytes(Paths.get(file)))).when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
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
   JsonPath jj = Reuse.getJsonPath(js.toString());
   System.out.println(jj.getString("name"));
   String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get(file)))).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();



   JsonPath j = Reuse.getJsonPath(response);
   String place_id = j.getString("place_id");
    System.out.println(j.getString("place_id"));
    
    String resonse2 = given().queryParam("key", "qaclick123").queryParam("place_id", place_id).when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
    System.out.println(resonse2);
  } 

  @Test(dataProvider = "datap")
  public void post(String a){

    String response = given().queryParam("Content-Type", "application/json").body(payload.addbook(a)).when().post("/Library/Addbook.php").then().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();
    System.out.println(response);
  }
  @DataProvider(name="datap")
  public Object[] data(){
    return new Object[]{"rayan","123"};
  }
  @Test
  public void playaround3() throws IOException{
    String file= "C:\\Users\\RayanAlOthaim\\OneDrive - JODAYN\\Documents\\VSC\\API testing\\Proj0\\api\\src\\test\\java\\Payload\\payload3.json";
    String a = new String (Files.readAllBytes(Paths.get(file)));
    JsonPath js = Reuse.getJsonPath(a);
    System.out.println(js.getString("dashboard.purchaseAmount"));
  }
}
/*


//update json
 *    File jsonFile = new File(file);
 
String jsonString = FileUtils.readFileToString(jsonFile); 
JsonElement jelement = new JsonParser().parse(jsonString);
JsonObject jobject = jelement.getAsJsonObject();
jobject.addProperty("isThisCodeAmazing", "Yes it is");
 
Gson gson = new Gson();
 
String resultingJson = gson.toJson(jelement);
FileUtils.writeStringToFile(jsonFile, resultingJson);
 
//specific path
// JsonPath js = Reuse.getJsonPath(f);


//read json file
 JSONObject json = Reuse.get(file);
 
// JsonPath js = Reuse.getJsonPath(json.toString(););
    System.out.println(js.getString("dashboard.purchaseAmount"));

or
String a = new String (Files.readAllBytes(Paths.get(file)));
    JsonPath js = Reuse.getJsonPath(a);
    System.out.println(js.getString("dashboard.purchaseAmount"));
  }

 */