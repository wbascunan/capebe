package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import kotlinx.coroutines.launch

class ResumenOperacionalViewModel : ViewModel() {

    var totalPasajeros by mutableStateOf(0)
        private set

    var consentimientosFirmados by mutableStateOf(0)
        private set

    var totalVuelos by mutableStateOf(0)
        private set

    var totalAsignaciones by mutableStateOf(0)
        private set

    var resumenCargado by mutableStateOf(false)
        private set

    fun cargarResumen(context: Context) {

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            val pasajeros =
                db.pasajeroDao().obtenerPasajeros()

            val vuelos =
                db.vueloDao().obtenerVuelos()

            val asignaciones =
                db.asignacionDao().obtenerAsignaciones()

            totalPasajeros = pasajeros.size

            consentimientosFirmados =
                pasajeros.count {
                    it.estadoConsentimiento == "Firmado"
                }

            totalVuelos = vuelos.size

            totalAsignaciones = asignaciones.size

            resumenCargado = true
        }
    }
}