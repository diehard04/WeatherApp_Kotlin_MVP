package com.example.weatherapp.model
import android.content.Context
import android.util.Log
import com.example.weatherapp.WeatherContract
import com.example.weatherapp.data.WeatherInfoModel
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.db.DatabaseHandler
import com.example.weatherapp.network.ApiClient
import com.example.weatherapp.network.ApiInterface
import com.example.weatherapp.utils.Constant
import retrofit2.Call
import retrofit2.Response

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class WeatherModelImpl(private val context: Context, private val presenter: WeatherContract.Presenter) : WeatherContract.Model {
    val TAG: String = WeatherInfoModel::class.java.name

    override fun fetchWeatherReportFromNetwork(latitude: String, longitude: String) {
        val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val call: Call<WeatherModel> = apiInterface.getWeatherReport(latitude, longitude, "metric", Constant.API_KEY)

        call.enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                val model: WeatherModel? = response.body()
                val weatherInfoModel = WeatherInfoModel()
                weatherInfoModel.setCityName(model!!.getName())
                weatherInfoModel.setTemp(model.getMain()?.getTemp()?.toString())
                weatherInfoModel.setMinTemp(model.getMain()?.getTempMin()?.toString())
                weatherInfoModel.setMaxTemp(model.getMain()?.getTempMax()?.toString())
                weatherInfoModel.setWindSpeed(model.getWind()?.getSpeed()?.toString())
                weatherInfoModel.setCloudDesc(model.getWeather()?.get(0)?.getDescription())
                saveIntoDataBase(weatherInfoModel)
                Log.d("onResponse= ", response.toString() + "")
                presenter.sendWeatherReportToPresenter(weatherInfoModel)
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("onFailure = ", t.message + "")
                //loggedInUser.value = t.message
            }
        })
    }

    override fun getCurrentSavedTime(): String? {
        val databaseHandler: DatabaseHandler = DatabaseHandler(context)
        return databaseHandler.getCurrentSavedTime()
    }

    override fun getSavedWeatherReportFromDB(): WeatherInfoModel? {
        val databaseHandler: DatabaseHandler = DatabaseHandler(context)
        return databaseHandler.getSavedWeatherReport()
    }

    private fun saveIntoDataBase(weatherInfoModel: WeatherInfoModel) {
        val databaseHandler: DatabaseHandler = DatabaseHandler(context)
        if (weatherInfoModel != null) {
            val status: Long
            if (!databaseHandler.isRecordExist()) {
                status = databaseHandler.addWeatherReport(weatherInfoModel, System.currentTimeMillis().toString())
            } else {
                status = databaseHandler.updateWeatherReport(weatherInfoModel, System.currentTimeMillis().toString()).toLong()
            }
            if (status > -1) {
                Log.d("saveIntoDataBase ", "record save")
            }
        }
        Log.d(TAG, " saveIntoDataBase " + databaseHandler.getCurrentSavedTime())
    }
}