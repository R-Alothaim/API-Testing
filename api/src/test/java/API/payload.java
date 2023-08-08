package API;

public class payload {
  public static String addbook(String book){
return "{\r\n" + //
        "\r\n" + //
        "\"name\":\"Learn Appium Automation with Java\",\r\n" + //
        "\"isbn\":\"bcd\",\r\n" + //
        "\"aisle\":\""+book+"\",\r\n" + //
        "\"author\":\"John foe\"\r\n" + //
        "}\r\n" + //
        "";
  }
}
