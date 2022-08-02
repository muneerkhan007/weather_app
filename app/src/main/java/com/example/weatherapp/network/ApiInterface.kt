package com.example.weatherapp.network

import com.example.weatherapp.home.model.WeatherResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun getWeatherByCityId(@Query("q") cityId: Int, @Query("appid") appId: String): Observable<WeatherResponseModel>
}