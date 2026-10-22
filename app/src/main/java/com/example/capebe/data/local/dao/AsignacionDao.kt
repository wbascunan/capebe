package com.example.capebe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capebe.data.local.entity.AsignacionEntity

@Dao
interface AsignacionDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registrarAsignacion(asignacion: AsignacionEntity)

    @Query("SELECT * FROM assignments")
    suspend fun obtenerAsignaciones(): List<AsignacionEntity>

    @Query("SELECT * FROM assignments WHERE pasajeroId = :pasajeroId LIMIT 1")
    suspend fun obtenerAsignacionPorPasajero(
        pasajeroId: Int
    ): AsignacionEntity?
}