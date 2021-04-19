package com.example.weatherapp.presenter

import android.content.Context
import android.location.Location
import com.example.weatherapp.WeatherContract
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.model.WeatherModelImpl

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class WeatherPresenter(private val view: WeatherContract.View, private val context: Context) : WeatherContract.Presenter{

    lateinit var model:WeatherContract.Model

    override fun init() {
        model = WeatherModelImpl(context)
    }

    override fun handleTemperatureResponse(weatherModel: WeatherModel?) {
        TODO("Not yet implemented")
    }

    override fun getWeather(location: Location) {
        if (location != null) {
            model.fetchWeatherReport(location.latitude.toString(), location.longitude.toString())
        }
    }
}