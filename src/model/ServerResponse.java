package model;

import com.google.gson.annotations.SerializedName;
import model.weather.WeatherData;

import java.util.List;

public class ServerResponse {
    @SerializedName("cod")
    private int cod;
    @SerializedName("message")
    private double message;
    @SerializedName("cnt")
    private int cnt;
    @SerializedName("list")
    private List<WeatherData> weatherDataList;

    public ServerResponse(int cod, double message, int cnt, List<WeatherData> weatherDataList) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.weatherDataList = weatherDataList;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }
}
