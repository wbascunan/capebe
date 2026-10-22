package com.example.capebe.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capebe.data.local.dao.UsuarioDao
import com.example.capebe.data.local.entity.UsuarioEntity
import com.example.capebe.data.local.dao.PasajeroDao
import com.example.capebe.data.local.entity.PasajeroEntity

@Database(
    entities = [
        UsuarioEntity::class,
        PasajeroEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun pasajeroDao(): PasajeroDao

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