package com.example.weatherapp.model

import android.content.Context
import android.util.Log
import com.example.weatherapp.WeatherContract
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.network.ApiClient
import com.example.weatherapp.network.ApiInterface
import com.example.weatherapp.utils.Constant
import retrofit2.Call
import retrofit2.Response

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class WeatherModelImpl(private val context: Context, private val presenter: WeatherContract.Presenter) : WeatherContract.Model {

    override fun fetchWeatherReport(latitude: String, longitude: String) {
        val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        val call: Call<WeatherModel> =
            apiInterface.getWeatherReport(latitude, longitude, "metric", Constant.API_KEY)

        call.enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                var model: WeatherModel? = response.body()
                Log.d("onResponse= ", response.toString() + "")
                presenter.sendWeatherReportToPresenter(model)
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("onFailure = ", t.message + "")

                //loggedInUser.value = t.message
            }
        })
    }

}