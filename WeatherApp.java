import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();
        scanner.close();

        // Fetch API key from environment variable
        String apiKey = System.getenv("WEATHER_API_KEY");

        // Check if API key is available
        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("Error: API key not found. Please set WEATHER_API_KEY environment variable.");
            return;
        }

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        fetchWeatherData(apiUrl);
    }

    public static void fetchWeatherData(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("\nWeather Data Received:");
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
        }
    }
}
