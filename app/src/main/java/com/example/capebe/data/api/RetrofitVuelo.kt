package com.example.capebe.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitVuelo {

    // Conexion local que usamos con ADB Reverse.
    private const val BASE_URL = "http://127.0.0.1:8082/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val cliente = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: VueloApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VueloApi::class.java)
    }
}
