package com.example.weatherapp.presenter

import android.content.Context
import com.example.weatherapp.WeatherContract
import com.example.weatherapp.data.WeatherInfoModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by DieHard_04 on 20-04-2021.
 */

@RunWith(MockitoJUnitRunner::class)
class WeatherPresenterTest : TestCase() {

    lateinit var weatherPresenter: WeatherPresenter

    @Mock
    lateinit var model: WeatherInfoModel

    @Mock
    lateinit var view: WeatherContract.View

    @Mock
    lateinit var context: Context

    @Before
    fun onSetup() {
        weatherPresenter = spy(WeatherPresenter(view, context))
        weatherPresenter.init()
    }

    @Test
    fun testSendWeatherReportToPresenter() {
        weatherPresenter.sendWeatherReportToPresenter(model)
        verify(view).sendWeatherReport(model)
    }

    @Test
    fun testFailResponse() {
        weatherPresenter.failResponse()
        verify(view).handleErrorView()
    }
}