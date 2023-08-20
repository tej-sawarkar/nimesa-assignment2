import dao.WeatherDao;
import model.Options;

import java.util.Scanner;

public class WeatherReport {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Options option;

        do {
            WeatherDao.init();
            showMenu();
            option = Options.lookUpByVal(scanner.nextInt());

            switch (option) {
                case EXIT -> System.out.println("Exit!");
                case TEMPERATURE -> WeatherDao.getWeatherData(Options.TEMPERATURE.key);
                case WIND_SPEED -> WeatherDao.getWeatherData(Options.WIND_SPEED.key);
                case PRESSURE -> WeatherDao.getWeatherData(Options.PRESSURE.key);
                default -> System.out.println("Invalid option! Try again.");
            }
        } while (option != Options.EXIT);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("\n1. Temperature");
        System.out.println("2. Wind Speed");
        System.out.println("3. Pressure");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
}