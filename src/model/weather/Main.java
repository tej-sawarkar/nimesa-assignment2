package model.weather;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private double temp;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("sea_level")
    private double seaLevel;
    @SerializedName("grnd_level")
    private double groundLevel;
    @SerializedName("humidity")
    private double humidity;
    @SerializedName("temp_kf")
    private double tempKf;

    public Main(double temp, double tempMin, double tempMax, double pressure, double seaLevel, double groundLevel, double humidity, double tempKf) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.groundLevel = groundLevel;
        this.humidity = humidity;
        this.tempKf = tempKf;
    }

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTempKf() {
        return tempKf;
    }
}