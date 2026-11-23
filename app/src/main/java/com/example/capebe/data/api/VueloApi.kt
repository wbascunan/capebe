package com.example.capebe.data.api

import com.example.capebe.model.VueloApiModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VueloApi {

    // GET: lista todos los vuelos.
    @GET("api/vuelos")
    suspend fun listar(): List<VueloApiModel>

    // GET: busca un vuelo por ID.
    @GET("api/vuelos/{id}")
    suspend fun obtener(@Path("id") id: Long): VueloApiModel

    // POST: registra un vuelo.
    @POST("api/vuelos")
    suspend fun crear(@Body vuelo: VueloApiModel): VueloApiModel
}
