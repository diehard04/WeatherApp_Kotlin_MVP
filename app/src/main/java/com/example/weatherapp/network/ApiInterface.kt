package com.example.weatherapp.network

import com.example.weatherapp.data.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by DieHard_04 on 19-04-2021.
 */

interface ApiInterface {

    @GET("2.5/weather")
    fun getWeatherReport(
        @Query("lat") lat: String?, @Query("lon") long: String?, @Query("units") metric: String?,
        @Query("appid") apikey: String?
    ): Call<WeatherModel>
}