package com.example.weatherapp

import android.location.Location
import com.example.weatherapp.data.WeatherModel

/**
 * Created by DieHard_04 on 19-04-2021.
 */

interface WeatherContract  {

    interface View {
        fun onInitView()
        fun handleWeatherView(showWeatherView: Boolean)
        fun handleErrorView(showErrorView: Boolean)
        fun setCityCurrentTemperature(cityName: String?, temperature: String?)
    }

    interface Presenter {
        fun init()
        fun handleTemperatureResponse(weatherModel: WeatherModel?)
        fun getWeather(location:Location)
    }

    interface Model {
        fun fetchInvalidCityMessage(): String?
        fun getFormattedDate(inputDateString: String?): String?
        fun fetchWeatherReport(toString: String, toString1: String)
    }
}

