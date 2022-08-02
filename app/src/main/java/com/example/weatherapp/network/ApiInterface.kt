package com.example.weatherapp.network

import com.example.weatherapp.home.model.WeatherResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun getWeatherByCityName(@Query("q") cityName: String, @Query("appid") appId: String): Observable<WeatherResponseModel>
}