package com.example.capebe.model

// Datos que llegan desde Spring Boot.
data class PasajeroApiModel(
    val id: Long? = null,
    val cedulaIdentidad: String,
    val nombreCompleto: String,
    val categoria: String,
    val peso: Double,
    val estadoConsentimiento: String = "Pendiente",
    val firma: String = ""
)
