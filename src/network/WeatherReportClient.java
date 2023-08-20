package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ServerResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class WeatherReportClient {
    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/%s?q=%s&appid=%s";
    private static final String HOURLY = "hourly";
    private static WeatherReportClient weatherReportClient;
    private final Gson gson;

    private WeatherReportClient() {
        gson = new GsonBuilder().create();
    }

    public static WeatherReportClient getInstance() {
        if (weatherReportClient == null) {
            weatherReportClient = new WeatherReportClient();
        }
        return weatherReportClient;
    }

    public ServerResponse fetchWeatherData() throws Exception {
        String query = "London,us";
        String appid = "b6907d289e10d714a6e88b30761fae22";

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format(BASE_URL, HOURLY, query, appid)))
                .build();

        System.out.println("Requesting weather data....");
        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(httpRequest, BodyHandlers.ofString());
        String responseBody = future.thenApply((Function<HttpResponse, String>) httpResponse -> {
            System.out.println("Weather data received....");
            return httpResponse.body().toString();
        }).get();
        return gson.fromJson(responseBody, ServerResponse.class);
    }
}
