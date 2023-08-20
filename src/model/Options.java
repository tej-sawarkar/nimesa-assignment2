package model;

public enum Options {
    TEMPERATURE("Temperature", 1),
    WIND_SPEED("Wind Speed", 2),
    PRESSURE("Pressure", 3),
    EXIT("Exit", 0),
    INVALID("Invalid", -1);

    public final String key;
    public final int value;

    Options(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public static Options lookUpByVal(int value) {
        if (value == Options.TEMPERATURE.value) {
            return Options.TEMPERATURE;
        } else if (value == Options.WIND_SPEED.value) {
            return Options.WIND_SPEED;
        } else if (value == Options.PRESSURE.value) {
            return Options.PRESSURE;
        } else if (value == Options.EXIT.value) {
            return Options.EXIT;
        } else return Options.INVALID;
    }

    public static Options lookUpByKey(String key) {
        if (Options.TEMPERATURE.key.equalsIgnoreCase(key)) {
            return Options.TEMPERATURE;
        } else if (Options.WIND_SPEED.key.equalsIgnoreCase(key)) {
            return Options.WIND_SPEED;
        } else if (Options.PRESSURE.key.equalsIgnoreCase(key)) {
            return Options.PRESSURE;
        } else if (Options.EXIT.key.equalsIgnoreCase(key)) {
            return Options.EXIT;
        } else return Options.INVALID;
    }
}
