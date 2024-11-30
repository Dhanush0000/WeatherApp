
# WeatherApp 

WeatherApp is a simple Java-based application that retrieves and displays weather information for a given location. It uses data from public APIs and provides an easy-to-use graphical user interface (GUI).

## Features

- Search weather by city name.
- Displays current weather condition, temperature, humidity, and wind speed.
- Provides visual icons for different weather conditions (e.g., clear, cloudy, rain, snow).
- Simple and responsive GUI built with Swing.

## Requirements

- Java 17 or higher.
- Internet connection to fetch weather data.
- Public weather and geocoding APIs:
  - Open-Meteo (for weather data): https://open-meteo.com/
  - Open-Meteo Geocoding API.

## Installation

1. Clone the repository or download the source code.
   ```bash
   git clone https://github.com/GN-SR/WeatherApp.git
   ```
2. Navigate to the project directory.

3. Compile the project using a Java IDE or the command line.

4. Add your project assets (icons and images) to the `src/assets` directory:
   - `clear.png` (Clear weather icon)
   - `cloudy.png` (Cloudy weather icon)
   - `rain.png` (Rain weather icon)
   - `snow.png` (Snow weather icon)
   - `humidity.png` (Humidity icon)
   - `windspeed.png` (Windspeed icon)
   - `search.png` (Search button icon)

5. Run the application.

   ```bash
   java -cp bin AppLauncher
   ```

## Directory Structure

```
WeatherApp/
├── src/
│   ├── assets/               # Icons and images
│   ├── WeatherApp.java       # Core logic for fetching and processing weather data
│   ├── WeatherAppGUI.java    # Swing GUI implementation
│   ├── AppLauncher.java      # Entry point for the application
├── README.md                 # Documentation
└── ...
```

## Usage

1. Enter the city name in the search bar.
2. Click on the search icon or press Enter.
3. View the weather details displayed on the GUI.

## API Information

- **Open-Meteo Weather API**: Used for retrieving weather data such as temperature, weather condition, humidity, and wind speed.
- **Geocoding API**: Resolves city names to latitude and longitude.

## Known Issues

- The application depends on public APIs, so ensure the API endpoints are available and functional.
- Make sure all asset files are present in the specified `src/assets/` directory.


---


