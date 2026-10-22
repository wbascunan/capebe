package com.example.capebe.model

data class VueloModel(
    val matricula: String = "",
    val modelo: String = "",
    val piloto: String = "",
    val numeroTripulantes: String = "",
    val horometroInicial: String = "",
    val horometroFinal: String = "",
    val errorMessage: String? = null
)