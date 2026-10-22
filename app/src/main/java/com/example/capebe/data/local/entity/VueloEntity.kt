package com.example.capebe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class VueloEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val matricula: String,
    val modelo: String,
    val piloto: String,
    val numeroTripulantes: Int,
    val horometroInicial: Double,
    val horometroFinal: Double,
    val horasVueloConsumidas: Double
)