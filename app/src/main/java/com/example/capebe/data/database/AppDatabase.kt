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
import com.example.capebe.data.local.dao.AsignacionDao
import com.example.capebe.data.local.entity.AsignacionEntity
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        UsuarioEntity::class,
        PasajeroEntity::class,
        VueloEntity::class,
        AsignacionEntity::class
    ],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun pasajeroDao(): PasajeroDao
    abstract fun vueloDao(): VueloDao
    abstract fun asignacionDao(): AsignacionDao

    companion object {
        private val MIGRATION_5_6 = object : Migration(5, 6) {

            override fun migrate(db: SupportSQLiteDatabase) {

                // Agrega el rol sin eliminar los usuarios existentes.
                db.execSQL(
                    "ALTER TABLE users " +
                            "ADD COLUMN rol TEXT NOT NULL " +
                            "DEFAULT 'COLABORADOR'"
                )
            }
        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database.db"
                )
                    .addMigrations(MIGRATION_5_6)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}