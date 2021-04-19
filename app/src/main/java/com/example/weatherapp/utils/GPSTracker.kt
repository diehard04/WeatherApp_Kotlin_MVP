package com.example.weatherapp.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by DieHard_04 on 19-04-2021.
 */

class GPSTracker(private val mContext: Context) : Service(), LocationListener {

    // Declaring a Location Manager
    protected var locationManager: LocationManager? = null

    // flag for GPS status
    var isGPSEnabled = false

    // flag for network status
    var isNetworkEnabled = false

    // flag for GPS status
    var canGetLocation = false
    private lateinit var location:Location
    var latitude:Double ?= 0.0
    var longitude:Double ?= 0.0
    var time = 0f
    var speed = 0f
    var accuracy = 0f
    var MY_PERMISSION_ACCESS_COURSE_LOCATION = 100


    fun getLocation(): Location? {
        try {
            locationManager = mContext
                    .getSystemService(Context.LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)

            // getting network status
            isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGPSEnabled || !isNetworkEnabled) {
                Log.d("Unable to get location:", "no network provider is enabled")
            } else {
                canGetLocation = true
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                                    ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                        Log.d("Permission", "failed")
                        ActivityCompat.requestPermissions((mContext as Activity), arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                                MY_PERMISSION_ACCESS_COURSE_LOCATION)
                    }
                    locationManager!!.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                    Log.d("Network", "Network")
                    if (locationManager != null) {
                        location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                            time = location!!.time.toFloat()
                            speed = location!!.speed
                            accuracy = location!!.accuracy
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                                        ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                            Log.d("Permission", "failed")
                            ActivityCompat.requestPermissions((mContext as Activity), arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                                    MY_PERMISSION_ACCESS_COURSE_LOCATION)
                        }
                        locationManager!!.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                        Log.d("GPS Enabled", "GPS Enabled")
                        if (locationManager != null) {
                            location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                                time = location!!.time.toFloat()
                                speed = location!!.speed
                                accuracy = location!!.accuracy
                            } else {
                                Log.d("location manager", "null")
                            }
                        } else {
                            Log.d("location manager", "null")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return location
    }


    /**
     * Function to get latitude
     */
    fun getLatitude(): Double {
        latitude = location.latitude
        // return latitude
        return latitude as Double
    }

    /**
     * Function to get longitude
     */
    fun getLongitude(): Double {
        longitude = location.longitude

        // return longitude
        return longitude as Double
    }

    fun speed(): Float {
        if (location != null) {
            speed = location!!.speed
            /*accuracy=location.getAccuracy();
                time=location.getTime();*/
        }
        return speed
    }

    fun accuracy(): Float {
        if (location != null) {
            accuracy = location!!.accuracy
        }
        return accuracy
    }

    fun time(): Float {
        if (location != null) {
            time = location!!.time.toFloat()
        }
        return time
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    fun canGetLocation(): Boolean {
        return canGetLocation
    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext)

        // Setting Dialog Title
        alertDialog.setTitle("GPS not enabled !")

        // Setting Dialog Message
        alertDialog.setMessage("You need to enable GPS for further process.")

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings") { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext.startActivity(intent)
        }

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }

        // Showing Alert Message
        alertDialog.show()
    }

    override fun onLocationChanged(location: Location) {
        // TODO Auto-generated method stub
    }

    override fun onProviderDisabled(provider: String) {
        // TODO Auto-generated method stub
    }

    override fun onProviderEnabled(provider: String) {
        // TODO Auto-generated method stub
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // TODO Auto-generated method stub
    }

    override fun onBind(arg0: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }

    companion object {
        // The minimum distance to change Updates in meters
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10 // 10 meters

        // The minimum time between updates in milliseconds
        private const val MIN_TIME_BW_UPDATES = 1000 * 60 * 1 // 1 minute
                .toLong()
    }

    //Constructor
    init {
        getLocation()
    }
}