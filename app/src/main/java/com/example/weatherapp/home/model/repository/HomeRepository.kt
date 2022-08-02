package com.example.weatherapp.home.model.repository

import com.example.weatherapp.home.model.WeatherResponseModel
import com.example.weatherapp.network.ApiClient
import com.example.weatherapp.utils.Constants
import io.reactivex.Observable

const val TAG = "HomeRepository"

class HomeRepository {
    val apiClient = ApiClient.getInstance()

    fun getWeatherByCityName(cityName: String): Observable<WeatherResponseModel> {
        return apiClient.getApiClient().getWeatherByCityName(cityName, Constants.APP_ID)
    }
}