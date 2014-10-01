package com.katenzo.garrytest;

import junit.framework.TestCase;

import java.util.HashMap;

public class TemperatureConverterTest extends TestCase {

    private static final HashMap<Double, Double> sConversionTableDouble = new HashMap<Double, Double>();

    static {
        sConversionTableDouble.put(0.0, 32.0);
        sConversionTableDouble.put(100.0, 212.0);
        sConversionTableDouble.put(-1.0, 30.20);
        sConversionTableDouble.put(-100.0, -148.0);
        sConversionTableDouble.put(32.0, 89.60);
        sConversionTableDouble.put(-40.0, -40.0);
        sConversionTableDouble.put(-273.0, -459.40);

    }

    public void testFahrenheitToCelcius() {
        for (double c : sConversionTableDouble.keySet()) {
            final double f = sConversionTableDouble.get(c);
            final double ca = TemperatureConverter.fahrenheitToCelcius(f);
            final double delta = Math.abs(ca - c);
            assertTrue(delta < 0.0005);
        }

    }

    public void testCelciusToFahrenheit() throws Exception {
        for (double c : sConversionTableDouble.keySet()) {
            final double f = sConversionTableDouble.get(c);
            final double fa = TemperatureConverter.celciusToFahrenheit(c);
            final double delta = Math.abs(fa - f);
            assertTrue("delta= " + delta + " for f= " + f + "fa= " + fa, delta < 0.0005);
        }

    }
}