package model.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherData {
    @SerializedName("dt")
    private long milliseconds;
    @SerializedName("dt_txt")
    private String dateText;
    @SerializedName("main")
    private Main main;
    @SerializedName("wind")
    private Wind wind;

    public WeatherData(long milliseconds, String dateText, Main main, Wind wind) {
        this.milliseconds = milliseconds;
        this.dateText = dateText;
        this.main = main;
        this.wind = wind;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public String getDateText() {
        return dateText;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }
}
