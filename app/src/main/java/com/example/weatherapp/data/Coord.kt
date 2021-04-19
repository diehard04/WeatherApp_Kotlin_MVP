package com.example.weatherapp.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




/**
 * Created by DieHard_04 on 19-04-2021.
 */

class Coord {
    @SerializedName("lon")
    @Expose
    private var lon: Double? = null

    @SerializedName("lat")
    @Expose
    private var lat: Double? = null

    fun getLon(): Double? {
        return lon
    }

    fun setLon(lon: Double?) {
        this.lon = lon
    }

    fun getLat(): Double? {
        return lat
    }

    fun setLat(lat: Double?) {
        this.lat = lat
    }
}