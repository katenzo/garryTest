package com.katenzo.garrytest;

public class TemperatureConverter {

    public static double fahrenheitToCelcius(double f) {

        return (f - 32) * 5 / 9.0;
    }


    public static double celciusToFahrenheit(double temperature) {
        return 9 / 5.0 * temperature + 32;
    }
}
