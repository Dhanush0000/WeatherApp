import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {
    public static JSONArray getLocationData(String locationName) {
        try {
            String endpoint = "https://geocoding-api.open-meteo.com/v1/search?name=" + locationName;
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(new InputStreamReader(connection.getInputStream()));
            return (JSONArray) response.get("results");
        } catch (Exception e) {
            System.out.println("Error fetching location data: " + e.getMessage());
            return null;
        }
    }

    public static JSONObject getWeatherData(String locationName) {
        JSONArray locationData = getLocationData(locationName);
        if (locationData == null || locationData.isEmpty()) {
            System.out.println("Location data is missing or invalid");
            return null;
        }

        JSONObject locationInfo = (JSONObject) locationData.get(0);
        double latitude = (double) locationInfo.get("latitude");
        double longitude = (double) locationInfo.get("longitude");

        try {
            String endpoint = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +
                    "&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m&forecast_days=1&timezone=auto";
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(new InputStreamReader(connection.getInputStream()));
            JSONObject hourly = (JSONObject) response.get("hourly");

            if (hourly == null) {
                System.out.println("Hourly data is missing from API response");
                return null;
            }

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray weatherCodes = (JSONArray) hourly.get("weathercode");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray humidities = (JSONArray) hourly.get("relativehumidity_2m");
            JSONArray windSpeeds = (JSONArray) hourly.get("windspeed_10m");

            if (weatherCodes == null || temperatures == null || humidities == null || windSpeeds == null) {
                System.out.println("Incomplete data in the API response");
                return null;
            }

            int index = findIndexOfCurrentTime(times);
            if (index < 0 || index >= weatherCodes.size()) {
                System.out.println("Invalid index for current time");
                return null;
            }

            JSONObject weatherData = new JSONObject();
            weatherData.put("weather_condition", convertWeatherCode((long) weatherCodes.get(index)));
            weatherData.put("temperature", temperatures.get(index));
            weatherData.put("humidity", humidities.get(index));
            weatherData.put("windspeed", windSpeeds.get(index));

            return weatherData;
        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
            return null;
        }
    }

    private static int findIndexOfCurrentTime(JSONArray times) {
        // Mock implementation for testing
        return 0; // Assume the first index corresponds to current time
    }

    public static String convertWeatherCode(long code) {
        switch ((int) code) {
            case 0:
                return "Clear";
            case 1:
            case 2:
            case 3:
                return "Cloudy";
            case 45:
            case 48:
                return "Foggy";
            case 51:
            case 53:
            case 55:
            case 61:
            case 63:
            case 65:
            case 80:
            case 81:
            case 82:
                return "Rain";
            case 71:
            case 73:
            case 75:
            case 77:
                return "Snow";
            default:
                return "Unknown";
        }
    }
}
