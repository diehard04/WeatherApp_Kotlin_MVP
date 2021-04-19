package com.example.weatherapp.network

import com.example.weatherapp.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by DieHard_04 on 19-04-2021.
 */
object ApiClient {

    lateinit var retrofit: Retrofit
    fun getClient(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val interceptor = HttpLoggingInterceptor()
        val oktHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        if (!::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(oktHttpClient)
                .build()
        }
        return retrofit
    }
}