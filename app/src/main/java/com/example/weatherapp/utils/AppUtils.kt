package com.example.weatherapp.utils

import com.example.weatherapp.home.model.CityModel

fun getTemperatureInCelsius(temperature: Double) = (temperature - 273.15).toInt().toString()

fun getCities(): List<CityModel> {
    val cityModelList = mutableListOf<CityModel>()
    cityModelList.add(CityModel(4118, "Toronto"))
    cityModelList.add(CityModel(44418, "London"))
    cityModelList.add(CityModel(2487956, "San Francisco"))
    cityModelList.add(CityModel(2459115, "New York"))
    cityModelList.add(CityModel(2379574, "Chicago"))
    cityModelList.add(CityModel(2442047, "Los Angeles"))
    cityModelList.add(CityModel(1118370, "Tokyo"))
    cityModelList.add(CityModel(1105779, "Sydney"))

    return cityModelList
}
