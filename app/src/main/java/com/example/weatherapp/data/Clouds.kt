package com.example.weatherapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class Clouds  {
    @SerializedName("all")
    @Expose
    private var all: Int? = null

    fun getAll(): Int? {
        return all
    }

    fun setAll(all: Int?) {
        this.all = all
    }
}