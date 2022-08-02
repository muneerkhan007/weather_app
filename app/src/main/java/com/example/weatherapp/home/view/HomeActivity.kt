package com.example.weatherapp.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.home.model.CityModel
import com.example.weatherapp.home.model.WeatherResponseModel
import com.example.weatherapp.home.viewmodel.HomeViewModel
import com.example.weatherapp.utils.getCities
import com.example.weatherapp.utils.getTemperatureInCelsius

const val TAG = "HomeActivity"

class HomeActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var tvCity: TextView? = null
    private var tvTemp: TextView? = null
    private var tvMinTemp: TextView? = null
    private var tvMaxTemp: TextView? = null
    private var tvStatus: TextView? = null
    private var tvNoData: TextView? = null
    private var llMainLayout: LinearLayout? = null
    private var selectedCityModel: CityModel? = null
    private val cityIndex: Int  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        setViewById()

        if(cityIndex < getCities().size) {
            selectedCityModel = getCities().get(cityIndex)
            selectedCityModel?.name?.let { loadWeather(it) }
        } else {
            displayNoData(getString(R.string.no_city_available))
        }
    }

    fun setViewById() {
        progressBar = findViewById(R.id.pb_home)
        tvCity = findViewById(R.id.tv_city)
        tvTemp = findViewById(R.id.tv_temp)
        tvMinTemp = findViewById(R.id.tv_min_temp)
        tvMaxTemp = findViewById(R.id.tv_max_temp)
        tvStatus = findViewById(R.id.tv_status)
        llMainLayout = findViewById(R.id.ll_mainLayout)
        tvNoData = findViewById(R.id.tv_no_data)
    }

    fun loadWeather(cityName: String) {
        var homeViewModel: HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.fetchWeather(cityName)

        homeViewModel!!.weatherModelLiveData.observe(this, androidx.lifecycle.Observer {
            if(it != null) {
                val weatherResponse = it as WeatherResponseModel
                printData(weatherResponse)
            } else {
                displayNoData(getString(R.string.something_went_wrong))
            }
        })
    }

    private fun displayNoData(message: String) {
        progressBar?.visibility = View.GONE
        llMainLayout?.visibility = View.GONE
        tvNoData?.visibility = View.VISIBLE
        showToast(message)
    }

    fun printData(weatherResponse: WeatherResponseModel) {
        llMainLayout?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE
        tvNoData?.visibility = View.GONE

        tvCity?.setText(selectedCityModel?.name)
        tvTemp?.setText(
            getTemperatureInCelsius(weatherResponse.main.temp) + "°")
        tvMinTemp?.setText("L: " + getTemperatureInCelsius(weatherResponse.main.temp_min) + "°")
        tvMaxTemp?.setText("H: " + getTemperatureInCelsius(weatherResponse.main.temp_max) + "°")
        if(weatherResponse.weather.size > 0) {
            tvStatus?.setText(weatherResponse.weather.get(0).main)
        }
    }

    private fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}