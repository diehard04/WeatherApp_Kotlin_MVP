package com.example.weatherapp.data

/**
 * Created by DieHard_04 on 20-04-2021.
 */

class WeatherInfoModel() {

    private var cityName:String ?= null
    private var temp:String ?= null
    private var minTemp: String ?= null
    private var maxTemp:String ?= null
    private var wind:String ?= null
    private var cloudDesc:String ?= null

    fun getCityName():String? {
        return cityName
    }

    fun setCityName(cityName:String?) {
        this.cityName =cityName
    }

    fun getTemp():String? {
        return temp
    }

    fun setTemp(temp:String?) {
        this.temp = temp
    }

    fun getMinTemp():String? {
        return minTemp
    }
    fun setMinTemp(minTemp:String?) {
        this.minTemp = minTemp
    }

    fun getMaxTemp():String? {
        return maxTemp
    }
    fun setMaxTemp(maxTemp:String?) {
        this.maxTemp= maxTemp
    }

    fun getWindSpeed():String? {
        return wind
    }
    fun setWindSpeed(wind:String?) {
        this.wind = wind
    }

    fun getCloudDesc():String? {
        return cloudDesc
    }
    fun setCloudDesc(cloudDesc:String?) {
        this.cloudDesc = cloudDesc
    }
}