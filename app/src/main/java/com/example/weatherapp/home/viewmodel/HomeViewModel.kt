package com.example.weatherapp.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.home.model.WeatherResponseModel
import com.example.weatherapp.home.model.repository.HomeRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val homeRepository: HomeRepository = HomeRepository()
    val weatherModelLiveData: MutableLiveData<WeatherResponseModel> = MutableLiveData()

    fun fetchWeather(cityName: String) {
        homeRepository.getWeatherByCityName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WeatherResponseModel> {
                override fun onNext(weatherModel: WeatherResponseModel) {
                    weatherModelLiveData.value = weatherModel
                }
                override fun onError(e: Throwable) {
                    Log.d("HomeViewModel", "onError: "+ e.message)
                    weatherModelLiveData.value = null
                }
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
            })
    }
}