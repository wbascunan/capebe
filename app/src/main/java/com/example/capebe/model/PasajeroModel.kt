package com.example.capebe.model

data class PasajeroModel(
    val cedulaIdentidad: String = "",
    val nombreCompleto: String = "",
    val categoria: String = "",
    val peso: String = "",
    val errorMessage: String? = null
)