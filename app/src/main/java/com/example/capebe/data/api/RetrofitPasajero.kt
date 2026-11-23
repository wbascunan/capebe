package com.example.capebe.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitPasajero {

    // 10.0.2.2 conecta el emulador con el backend de la mac.
    private const val BASE_URL = "http://127.0.0.1:8081/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val cliente = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: PasajeroApi by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.PASAJEROS)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PasajeroApi::class.java)
    }
}
