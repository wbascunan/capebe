package com.example.capebe.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.capebe.model.RolUsuario

@Entity(tableName = "users")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,

    // Rol del usuario guardado localmente en SQLite.
    // Los usuarios anteriores conservaran COLABORADOR.
    @ColumnInfo(defaultValue = "'COLABORADOR'")
    val rol: String = RolUsuario.COLABORADOR.name
)