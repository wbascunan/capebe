package com.example.capebe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capebe.data.local.dao.UsuarioDao
import com.example.capebe.data.local.entity.UsuarioEntity
import com.example.capebe.data.local.dao.PasajeroDao
import com.example.capebe.data.local.entity.PasajeroEntity
import com.example.capebe.data.local.dao.VueloDao
import com.example.capebe.data.local.entity.VueloEntity

@Database(
    entities = [
        UsuarioEntity::class,
        PasajeroEntity::class,
        VueloEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun pasajeroDao(): PasajeroDao
    abstract fun vueloDao(): VueloDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database.db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}