package com.example.capebe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passengers")
data class PasajeroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cedulaIdentidad: String,
    val nombreCompleto: String,
    val categoria: String,
    val peso: Double,
    val estadoConsentimiento: String = "Pendiente"
)