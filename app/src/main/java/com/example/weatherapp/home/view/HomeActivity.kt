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
import com.example.weatherapp.home.model.WeatherModel
import com.example.weatherapp.home.viewmodel.HomeViewModel

const val TAG = "HomeActivity"

class HomeActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var tvCity: TextView? = null
    private var tvTemp: TextView? = null
    private var tvMinTemp: TextView? = null
    private var tvMaxTemp: TextView? = null
    private var llMainLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        setViewById()
        loadWeather()
    }

    fun setViewById() {
        progressBar = findViewById(R.id.progress_home)
        tvCity = findViewById(R.id.tv_city)
        tvTemp = findViewById(R.id.tv_temp)
        tvMinTemp = findViewById(R.id.tv_min_temp)
        tvMaxTemp = findViewById(R.id.tv_max_temp)
        llMainLayout = findViewById(R.id.ll_mainLayout)
    }

    fun loadWeather() {
        var homeViewModel: HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.fetchWeather(4118)

        homeViewModel!!.weatherModelLiveData.observe(this, androidx.lifecycle.Observer {
            if(it != null) {
                val weatherModel = it as WeatherModel
                tvCity?.setText(weatherModel.name)
                tvTemp?.setText(weatherModel.main.temp.toString() + "°")
                tvMinTemp?.setText("L: " + weatherModel.main.temp_min.toString() + "°")
                tvMaxTemp?.setText("H: " + weatherModel.main.temp_max.toString() + "°")
                llMainLayout?.visibility = View.VISIBLE
            } else {
                showToast("Something went wrong")
            }
            progressBar?.visibility = View.GONE
        })
    }

    private fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}