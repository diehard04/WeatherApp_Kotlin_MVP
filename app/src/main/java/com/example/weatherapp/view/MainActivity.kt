package com.example.weatherapp.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weatherapp.R
import com.example.weatherapp.WeatherContract
import com.example.weatherapp.data.WeatherModel
import com.example.weatherapp.presenter.WeatherPresenter

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class MainActivity : AppCompatActivity(), LocationListener, WeatherContract.View {
    private lateinit var locationManager: LocationManager
    lateinit var activity: MainActivity
    lateinit var presenter: WeatherContract.Presenter
    lateinit var loading:ProgressBar;
    lateinit var tvCityName:AppCompatTextView
    lateinit var tvCityTemp:AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        setContentView(R.layout.activity_main)
        presenter = WeatherPresenter(this, applicationContext)
        presenter.init()
        onInitView()
    }

    fun onInitView() {
        loading = findViewById<ProgressBar>(R.id.loading)
        loading.visibility = View.INVISIBLE
        tvCityName = findViewById(R.id.tv_cite_name)
        tvCityTemp = findViewById(R.id.tv_cite_temp)
    }

    override fun handleErrorView() {
        loading.visibility = View.GONE
    }

    override fun sendWeatherReport(model: WeatherModel?) {
        loading.visibility = View.GONE
        tvCityName.text = model?.getName()
        tvCityTemp.text = model?.getMain()?.getTemp().toString() + 0x00B0.toChar() + "C"
    }

    override fun onResume() {
        super.onResume()
        getLocation();
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_ACCESS_FINE_LOCATION
            )
            return
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5f, this)

    }

    override fun onLocationChanged(location: Location) {
        presenter.getWeather(location)
        loading.visibility = View.VISIBLE
        Log.d("Latitude ", location.latitude.toString() + " Longitude " + location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //super.onStatusChanged(provider, status, extras)
    }

    override fun onProviderEnabled(provider: String) {
        //super.onProviderEnabled(provider)
    }

    override fun onProviderDisabled(provider: String) {
        //super.onProviderDisabled(provider)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }
}