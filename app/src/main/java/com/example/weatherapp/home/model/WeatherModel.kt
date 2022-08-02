package com.example.weatherapp.home.model

data class WeatherModel(
    var coord: CoordModel,
    var id: Int,
    var name: String,
    var main: TemperatureModel
) {}