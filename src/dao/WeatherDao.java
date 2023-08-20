package dao;

import model.Options;
import model.ServerResponse;
import model.weather.Main;
import model.weather.WeatherData;
import network.WeatherReportClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherDao {
    private static ServerResponse serverResponse;
    private static WeatherDao weatherDao;

    private WeatherDao() {
        callApi();
    }

    public static void init() {
        if (weatherDao == null) {
            weatherDao = new WeatherDao();
        }
    }

    public static void callApi() {
        try {
            serverResponse = WeatherReportClient.getInstance().fetchWeatherData();
        } catch (Exception e) {
            System.out.println("Oops, Failed to fetch weather report");
        }
    }

    public static void getWeatherData(String dataType) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the date with time (format: YYYY-MM-DD HH:MM:SS): ");
            String inputDateTime = scanner.nextLine();
            String datePattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
            Pattern pattern = Pattern.compile(datePattern);
            Matcher matcher = pattern.matcher(inputDateTime);
            if (!matcher.matches()) {
                System.out.println("Invalid date format!");
                return;
            }

            if (serverResponse != null) {
                List<WeatherData> list = serverResponse.getWeatherDataList();

                boolean present = false;
                for (WeatherData weatherData : list) {
                    String dateTime = weatherData.getDateText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    LocalDateTime forecast = LocalDateTime.parse(dateTime, formatter);
                    String forecastDate = forecast.toLocalDate().toString();
                    String forecastHour = String.valueOf(forecast.getHour());

                    LocalDateTime input = LocalDateTime.parse(inputDateTime, formatter);
                    String inputDate = input.toLocalDate().toString();
                    String inputTime = String.valueOf(input.getHour());

                    if (forecastDate.equals(inputDate) && forecastHour.equals(inputTime)) {
                        double value = getValue(weatherData, dataType);
                        System.out.println(dataType + ": " + value);
                        present = true;
                        break;
                    }
                }

                if (!present) {
                    System.out.println("Oops, we don't have weather report for specified date & time.");
                }
            } else {
                System.out.println("Oops, failed to get weather report.");
            }
        } catch (Exception e) {
            System.out.println("Oops, failed to get report due to invalid input.");
        }
    }

    private static double getValue(WeatherData weatherData, String dataType) {
        Options options = Options.lookUpByKey(dataType);
        Main main = weatherData.getMain();

        return switch (options) {
            case TEMPERATURE -> main.getTemp();
            case WIND_SPEED -> weatherData.getWind().getSpeed();
            case PRESSURE -> main.getPressure();
            default -> 0.0;
        };
    }
}