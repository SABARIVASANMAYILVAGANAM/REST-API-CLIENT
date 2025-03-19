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

        String apiKey = "83d903be83408c562d41955f0c606ca0"; // Replace with your valid API key
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
