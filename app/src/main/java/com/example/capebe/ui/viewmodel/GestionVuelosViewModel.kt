package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import com.example.capebe.data.local.entity.VueloEntity
import kotlinx.coroutines.launch

class GestionVuelosViewModel : ViewModel() {

    var vuelos by mutableStateOf<List<VueloEntity>>(emptyList())
        private set

    var consultaRealizada by mutableStateOf(false)
        private set

    fun cargarVuelos(context: Context) {

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            vuelos = db.vueloDao().obtenerVuelos()

            consultaRealizada = true
        }
    }
}