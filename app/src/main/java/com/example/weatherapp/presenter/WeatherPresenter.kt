package com.example.weatherapp.presenter

import android.content.Context
import android.location.Location
import com.example.weatherapp.WeatherContract
import com.example.weatherapp.data.WeatherInfoModel
import com.example.weatherapp.model.WeatherModelImpl

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class WeatherPresenter(private val view: WeatherContract.View, private val context: Context) : WeatherContract.Presenter {

    lateinit var model: WeatherContract.Model
    override fun init() {
        model = WeatherModelImpl(context, this)
    }

    override fun getWeather(location: Location) {
        if (isNetworkCallNeeded()){
            model.fetchWeatherReportFromNetwork(location.latitude.toString(), location.longitude.toString())
        } else {
            model.getSavedWeatherReportFromDB()
            sendWeatherReportToPresenter(model.getSavedWeatherReportFromDB())
        }
    }

    private fun isNetworkCallNeeded(): Boolean {
        val savedTime = model.getCurrentSavedTime()?.toLong()
        val currentTime = System.currentTimeMillis()
        if (currentTime - savedTime!! > 7200000) {
            return true
        }
        return false
    }

    override fun sendWeatherReportToPresenter(model: WeatherInfoModel?) {
        view.sendWeatherReport(model)
    }

    override fun failResponse() {
        view.handleErrorView()
    }
}