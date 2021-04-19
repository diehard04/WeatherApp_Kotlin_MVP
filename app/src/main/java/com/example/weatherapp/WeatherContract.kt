package com.example.weatherapp

import android.location.Location
import com.example.weatherapp.data.WeatherModel

/**
 * Created by DieHard_04 on 19-04-2021.
 */

interface WeatherContract  {

    interface View {
        fun handleErrorView()
        fun sendWeatherReport(model: WeatherModel?)
    }

    interface Presenter {
        fun init()
        fun getWeather(location:Location)
        fun sendWeatherReportToPresenter(model: WeatherModel?)
        fun failResponse()
    }

    interface Model {
        fun fetchWeatherReport(toString: String, toString1: String)
    }
}

