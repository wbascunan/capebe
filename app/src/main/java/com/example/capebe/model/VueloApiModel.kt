package com.example.capebe.model

// Modelo de Vuelo para Spring Boot.
// No reemplaza el VueloModel de Room.
data class VueloApiModel(
    val id: Long? = null,
    val matricula: String,
    val modelo: String,
    val piloto: String,
    val tripulantes: Int,
    val horometroInicial: Double,
    val horometroFinal: Double,
    val horasVueloConsumidas: Double? = null
)
