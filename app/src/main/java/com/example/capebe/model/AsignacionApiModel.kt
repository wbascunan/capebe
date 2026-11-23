package com.example.capebe.model

// Datos de una asignacion recibida desde Spring Boot.
data class AsignacionApiModel(
    val id: Long? = null,
    val pasajeroId: Long,
    val vueloId: Long
)
