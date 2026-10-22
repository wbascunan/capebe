package com.example.capebe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capebe.data.local.entity.PasajeroEntity

@Dao
interface PasajeroDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registrarPasajero(pasajero: PasajeroEntity)

    @Query("SELECT * FROM passengers")
    suspend fun obtenerPasajeros(): List<PasajeroEntity>

    @Query("SELECT * FROM passengers WHERE id = :id LIMIT 1")
    suspend fun obtenerPasajeroPorId(id: Int): PasajeroEntity?

    @Query("SELECT * FROM passengers ORDER BY id DESC LIMIT 1")
    suspend fun obtenerUltimoPasajero(): PasajeroEntity?

    @Query(
        "UPDATE passengers SET estadoConsentimiento = 'Firmado', firma = :firma WHERE id = :id"
    )
    suspend fun firmarConsentimiento(id: Int, firma: String)
}