import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class WeatherApp {
    //fetch weather data
    public static JSONObject getWeatherData(String locationName){
        JSONArray locationData = getLocationData(locationName);
    }

    private static JSONArray getLocationData(String locationName){
        locationName = locationName.replaceAll(" ", "+");

        //build API url with location parameter
        String urlString = "https://geocoding-api.com-meteo.com/v1/search?name=" +
                locationName + "&count=1-&language=en&format=json";

        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
