package com.example.weatherapp.model

import android.content.Context
import com.example.weatherapp.WeatherContract
import com.example.weatherapp.data.WeatherInfoModel
import com.example.weatherapp.db.DatabaseHandler
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by DieHard_04 on 20-04-2021.
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherModelImplTest : TestCase() {

    lateinit var weatherModelImpl: WeatherModelImpl

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var presenter: WeatherContract.Presenter

    @Mock
    lateinit var databaseHandler: DatabaseHandler

    @Before
    fun onSetup() {
        weatherModelImpl = WeatherModelImpl(context, presenter)
    }

    @Test
    fun testGetCurrentSavedTime() {
    }
}