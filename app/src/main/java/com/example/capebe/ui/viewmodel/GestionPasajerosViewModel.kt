package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import com.example.capebe.data.local.entity.PasajeroEntity
import kotlinx.coroutines.launch

class GestionPasajerosViewModel : ViewModel() {

    var pasajeros by mutableStateOf<List<PasajeroEntity>>(emptyList())
        private set

    var consultaRealizada by mutableStateOf(false)
        private set

    fun cargarPasajeros(context: Context) {

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            pasajeros = db.pasajeroDao().obtenerPasajeros()

            consultaRealizada = true
        }
    }
}