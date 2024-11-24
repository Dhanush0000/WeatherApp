import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {
    //fetch weather data
    public static JSONObject getWeatherData(String locationName){
        JSONArray locationData = getLocationData(locationName);

        return null;
    }

    public static JSONArray getLocationData(String locationName){
        locationName = locationName.replaceAll(" ", "+");

        //build API url with location parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name="+
                locationName+"&count=10-&language=en&format=json";

        try {
            //calling api
            HttpURLConnection conn = fetchApiResponse(urlString);

            //200 means successful connection
            if(conn.getResponseCode() != 200){
                System.out.println("Error: Could not connect to API");
                return null;
            }
            else {
                //store API results
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                while (scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }
                scanner.close();

                conn.disconnect(); // close url connection

                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                //get the list of location
                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return locationData;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection fetchApiResponse(String urlString){
        try {
            //attempting to create a new connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();
            return conn;
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
