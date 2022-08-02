package com.example.weatherapp.utils

import org.junit.Test
internal class AppUtilsKtTest {

    @Test
    fun temperature_to_celsius_validator() {
        assert(getTemperatureInCelsius(295.51) == "22")
    }

    @Test
    fun check_cities_not_null() {
        assert(getCities().size > 0)
    }
}