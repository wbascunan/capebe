package com.example.capebe.data.api

import com.example.capebe.model.AsignacionApiModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AsignacionApi {

    // Obtener todas las asignaciones.
    @GET("api/asignaciones")
    suspend fun listar(): List<AsignacionApiModel>

    // Buscar una asignacion por su ID.
    @GET("api/asignaciones/{id}")
    suspend fun obtener(@Path("id") id: Long): AsignacionApiModel

    // Crear una asignacion.
    @POST("api/asignaciones")
    suspend fun crear(
        @Body asignacion: AsignacionApiModel
    ): AsignacionApiModel
}
