package API;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.path.json.JsonPath;

public class Reuse {
  public static void main(String[] args) {
  }
  public static JsonPath getJsonPath(String response){
    JsonPath js = new JsonPath(response);
    return js;
  }
  public static JSONObject get(String string) throws IOException, ParseException {
      JSONParser parser = new JSONParser();      
         FileReader fileReader = new FileReader(string);
                 JSONObject json = (JSONObject) parser.parse(fileReader);
                return json;
  }

}
