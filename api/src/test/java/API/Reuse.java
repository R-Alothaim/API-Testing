package API;

import io.restassured.path.json.JsonPath;

public class Reuse {
  public static void main(String[] args) {
  }
  public static JsonPath getJsonPath(String response){
    JsonPath js = new JsonPath(response);
    return js;
  }
  public static String get(String string) {
    return null;
  }

}
