package com.example.weatherapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class Sys {
    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("sunrise")
    @Expose
    private var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    private var sunset: Int? = null

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getSunrise(): Int? {
        return sunrise
    }

    fun setSunrise(sunrise: Int?) {
        this.sunrise = sunrise
    }

    fun getSunset(): Int? {
        return sunset
    }

    fun setSunset(sunset: Int?) {
        this.sunset = sunset
    }
}