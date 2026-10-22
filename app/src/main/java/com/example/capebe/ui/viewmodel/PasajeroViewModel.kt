package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import com.example.capebe.data.local.entity.PasajeroEntity
import com.example.capebe.model.PasajeroModel
import kotlinx.coroutines.launch

class PasajeroViewModel : ViewModel() {

    var uiState by mutableStateOf(PasajeroModel())
        private set

    fun onCedulaChanged(cedula: String) {
        uiState = uiState.copy(
            cedulaIdentidad = cedula,
            errorMessage = null
        )
    }

    fun onNombreChanged(nombre: String) {
        uiState = uiState.copy(
            nombreCompleto = nombre,
            errorMessage = null
        )
    }

    fun onCategoriaChanged(categoria: String) {
        uiState = uiState.copy(
            categoria = categoria,
            errorMessage = null
        )
    }

    fun onPesoChanged(peso: String) {
        uiState = uiState.copy(
            peso = peso,
            errorMessage = null
        )
    }

    fun onRegistrarClick(
        context: Context,
        onSuccess: () -> Unit
    ) {
        val state = uiState

        if (
            state.cedulaIdentidad.isBlank() ||
            state.nombreCompleto.isBlank() ||
            state.categoria.isBlank() ||
            state.peso.isBlank()
        ) {
            uiState = uiState.copy(
                errorMessage = "Por favor completa todos los campos."
            )
            return
        }

        val pesoPasajero = state.peso.toDoubleOrNull()

        if (pesoPasajero == null) {
            uiState = uiState.copy(
                errorMessage = "Ingresa un peso válido."
            )
            return
        }

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            val nuevoPasajero = PasajeroEntity(
                cedulaIdentidad = state.cedulaIdentidad,
                nombreCompleto = state.nombreCompleto,
                categoria = state.categoria,
                peso = pesoPasajero,
                estadoConsentimiento = "Pendiente"
            )

            db.pasajeroDao().registrarPasajero(nuevoPasajero)

            uiState = uiState.copy(
                errorMessage = null
            )

            onSuccess()
        }
    }
}