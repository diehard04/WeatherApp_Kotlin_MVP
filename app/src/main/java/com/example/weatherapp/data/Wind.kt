package com.example.weatherapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class Wind  {
    @SerializedName("speed")
    @Expose
    private var speed: Double? = null

    @SerializedName("deg")
    @Expose
    private var deg: Int? = null

    @SerializedName("gust")
    @Expose
    private var gust: Double? = null

    fun getSpeed(): Double? {
        return speed
    }

    fun setSpeed(speed: Double?) {
        this.speed = speed
    }

    fun getDeg(): Int? {
        return deg
    }

    fun setDeg(deg: Int?) {
        this.deg = deg
    }

    fun getGust(): Double? {
        return gust
    }

    fun setGust(gust: Double?) {
        this.gust = gust
    }
}