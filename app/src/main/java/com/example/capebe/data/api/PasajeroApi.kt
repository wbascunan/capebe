package com.example.capebe.data.api

import com.example.capebe.model.PasajeroApiModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PasajeroApi {

    // Lista los pasajeros del backend.
    @GET("api/pasajeros")
    suspend fun listar(): List<PasajeroApiModel>

    // Busca un pasajero por ID.
    @GET("api/pasajeros/{id}")
    suspend fun obtener(@Path("id") id: Long): PasajeroApiModel

    // Registra un pasajero.
    @POST("api/pasajeros")
    suspend fun crear(@Body pasajero: PasajeroApiModel): PasajeroApiModel
}
