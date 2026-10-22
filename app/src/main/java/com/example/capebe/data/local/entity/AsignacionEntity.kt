package com.example.capebe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assignments")
data class AsignacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pasajeroId: Int,
    val vueloId: Int
)