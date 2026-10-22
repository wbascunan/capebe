package com.example.capebe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capebe.data.local.entity.VueloEntity

@Dao
interface VueloDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registrarVuelo(vuelo: VueloEntity)

    @Query("SELECT * FROM flights")
    suspend fun obtenerVuelos(): List<VueloEntity>

    @Query("SELECT * FROM flights WHERE id = :id LIMIT 1")
    suspend fun obtenerVueloPorId(id: Int): VueloEntity?
}