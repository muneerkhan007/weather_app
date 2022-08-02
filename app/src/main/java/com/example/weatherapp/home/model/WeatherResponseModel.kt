package com.example.weatherapp.home.model

data class WeatherResponseModel(
    var coord: CoordModel,
    var weather: ArrayList<WeatherModel>,
    var id: Int,
    var name: String,
    var main: TemperatureModel
) {}