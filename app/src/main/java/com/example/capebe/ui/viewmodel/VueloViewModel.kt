package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import com.example.capebe.data.local.entity.VueloEntity
import com.example.capebe.model.VueloModel
import kotlinx.coroutines.launch
import kotlin.math.round

class VueloViewModel : ViewModel() {

    var uiState by mutableStateOf(VueloModel())
        private set

    fun onMatriculaChanged(matricula: String) {
        uiState = uiState.copy(
            matricula = matricula,
            errorMessage = null
        )
    }

    fun onModeloChanged(modelo: String) {
        uiState = uiState.copy(
            modelo = modelo,
            errorMessage = null
        )
    }

    fun onPilotoChanged(piloto: String) {
        uiState = uiState.copy(
            piloto = piloto,
            errorMessage = null
        )
    }

    fun onTripulantesChanged(numeroTripulantes: String) {
        uiState = uiState.copy(
            numeroTripulantes = numeroTripulantes,
            errorMessage = null
        )
    }

    fun onHorometroInicialChanged(horometroInicial: String) {
        uiState = uiState.copy(
            horometroInicial = horometroInicial,
            errorMessage = null
        )
    }

    fun onHorometroFinalChanged(horometroFinal: String) {
        uiState = uiState.copy(
            horometroFinal = horometroFinal,
            errorMessage = null
        )
    }

    fun onRegistrarClick(
        context: Context,
        onSuccess: () -> Unit
    ) {
        val state = uiState

        if (
            state.matricula.isBlank() ||
            state.modelo.isBlank() ||
            state.piloto.isBlank() ||
            state.numeroTripulantes.isBlank() ||
            state.horometroInicial.isBlank() ||
            state.horometroFinal.isBlank()
        ) {
            uiState = uiState.copy(
                errorMessage = "Completa todos los campos."
            )
            return
        }

        val tripulantes =
            state.numeroTripulantes.toIntOrNull()

        val horometroInicial =
            state.horometroInicial
                .replace(",", ".")
                .toDoubleOrNull()

        val horometroFinal =
            state.horometroFinal
                .replace(",", ".")
                .toDoubleOrNull()

        if (
            tripulantes == null ||
            horometroInicial == null ||
            horometroFinal == null
        ) {
            uiState = uiState.copy(
                errorMessage = "Revisa los valores numéricos ingresados."
            )
            return
        }

        if (tripulantes <= 0) {
            uiState = uiState.copy(
                errorMessage = "El número de tripulantes debe ser mayor a 0."
            )
            return
        }

        if (horometroInicial < 0 || horometroFinal < 0) {
            uiState = uiState.copy(
                errorMessage = "Las lecturas del horómetro no pueden ser negativas."
            )
            return
        }

        if (horometroFinal <= horometroInicial) {
            uiState = uiState.copy(
                errorMessage = "La lectura final debe ser mayor a la inicial."
            )
            return
        }

        val diferencia = horometroFinal - horometroInicial

        val horasConsumidas =
            round(diferencia * 100) / 100

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            val nuevoVuelo = VueloEntity(
                matricula = state.matricula,
                modelo = state.modelo,
                piloto = state.piloto,
                numeroTripulantes = tripulantes,
                horometroInicial = horometroInicial,
                horometroFinal = horometroFinal,
                horasVueloConsumidas = horasConsumidas
            )

            db.vueloDao().registrarVuelo(nuevoVuelo)

            uiState = uiState.copy(
                errorMessage = null
            )

            onSuccess()
        }
    }
}