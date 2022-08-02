package com.example.weatherapp.home.model.repository

import com.example.weatherapp.home.model.WeatherModel
import com.example.weatherapp.network.ApiClient
import io.reactivex.Observable

const val TAG = "HomeRepository"

class HomeRepository {
    private val appId = "a567eca4b52f20847650b5f73af13af9"
    val apiClient = ApiClient.getInstance()

    fun getWeatherByCityId(cityId: Int): Observable<WeatherModel> {
        return apiClient.getApiClient().getWeatherByCityId(cityId, appId)
    }
}