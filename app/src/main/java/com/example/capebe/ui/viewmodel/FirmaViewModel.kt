package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import kotlinx.coroutines.launch

class FirmaViewModel : ViewModel() {

    var puntos by mutableStateOf<List<Offset?>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun agregarPunto(punto: Offset) {
        puntos = puntos + punto
        errorMessage = null
    }

    fun terminarTrazo() {
        puntos = puntos + null
    }

    fun limpiarFirma() {
        puntos = emptyList()
        errorMessage = null
    }

    fun guardarFirma(
        context: Context,
        onSuccess: () -> Unit
    ) {

        if (puntos.filterNotNull().isEmpty()) {
            errorMessage = "Debe ingresar una firma."
            return
        }

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            val pasajero = db.pasajeroDao().obtenerUltimoPasajero()

            if (pasajero == null) {

                errorMessage = "No se encontró un pasajero registrado."

            } else {

                val firmaTexto = puntos.joinToString(";") { punto ->

                    if (punto == null) {
                        "|"
                    } else {
                        "${punto.x},${punto.y}"
                    }
                }

                db.pasajeroDao().firmarConsentimiento(
                    pasajero.id,
                    firmaTexto
                )

                errorMessage = null

                onSuccess()
            }
        }
    }
}