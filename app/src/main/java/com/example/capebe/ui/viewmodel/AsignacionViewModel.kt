package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import com.example.capebe.data.local.entity.AsignacionEntity
import com.example.capebe.data.local.entity.PasajeroEntity
import com.example.capebe.data.local.entity.VueloEntity
import com.example.capebe.model.AsignacionModel
import kotlinx.coroutines.launch

class AsignacionViewModel : ViewModel() {

    var uiState by mutableStateOf(AsignacionModel())
        private set

    var pasajeros by mutableStateOf<List<PasajeroEntity>>(emptyList())
        private set

    var vuelos by mutableStateOf<List<VueloEntity>>(emptyList())
        private set

    var datosCargados by mutableStateOf(false)
        private set

    fun cargarDatos(context: Context) {

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            pasajeros = db.pasajeroDao().obtenerPasajeros()

            vuelos = db.vueloDao().obtenerVuelos()

            datosCargados = true
        }
    }

    fun seleccionarPasajero(id: Int) {

        uiState = uiState.copy(
            pasajeroId = id,
            errorMessage = null
        )
    }

    fun seleccionarVuelo(id: Int) {

        uiState = uiState.copy(
            vueloId = id,
            errorMessage = null
        )
    }

    fun registrarAsignacion(
        context: Context,
        onSuccess: () -> Unit
    ) {

        val pasajeroId = uiState.pasajeroId
        val vueloId = uiState.vueloId

        if (pasajeroId == null || vueloId == null) {

            uiState = uiState.copy(
                errorMessage = "Selecciona un pasajero y un vuelo."
            )

            return
        }

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            val asignacionExistente =
                db.asignacionDao()
                    .obtenerAsignacionPorPasajero(pasajeroId)

            if (asignacionExistente != null) {

                uiState = uiState.copy(
                    errorMessage = "El pasajero ya tiene un vuelo asignado."
                )

            } else {

                val nuevaAsignacion = AsignacionEntity(
                    pasajeroId = pasajeroId,
                    vueloId = vueloId
                )

                db.asignacionDao()
                    .registrarAsignacion(nuevaAsignacion)

                uiState = uiState.copy(
                    errorMessage = null
                )

                onSuccess()
            }
        }
    }
}