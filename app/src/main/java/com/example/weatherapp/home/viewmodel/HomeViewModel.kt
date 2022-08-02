package com.example.weatherapp.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.home.model.repository.HomeRepository
import com.example.weatherapp.home.model.WeatherModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val homeRepository: HomeRepository = HomeRepository()
    val weatherModelLiveData: MutableLiveData<WeatherModel> = MutableLiveData()

    fun fetchWeather(cityId: Int) {
        homeRepository.getWeatherByCityId(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WeatherModel> {
                override fun onNext(weatherModel: WeatherModel) {
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