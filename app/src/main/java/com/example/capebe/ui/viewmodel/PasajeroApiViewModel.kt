package com.example.capebe.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.api.RetrofitPasajero
import com.example.capebe.model.PasajeroApiModel
import kotlinx.coroutines.launch

class PasajeroApiViewModel : ViewModel() {

    private val _lista = MutableLiveData<List<PasajeroApiModel>>(emptyList())
    val lista: LiveData<List<PasajeroApiModel>> = _lista

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private val _guardando = MutableLiveData(false)
    val guardando: LiveData<Boolean> = _guardando

    // GET: obtiene los pasajeros desde Spring Boot.
    fun cargar() {
        viewModelScope.launch {
            try {
                _error.value = null
                _lista.value = RetrofitPasajero.api.listar()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error de conexion"
            }
        }
    }

    // POST: guarda un pasajero en Spring Boot.
    fun crear(pasajero: PasajeroApiModel, onExito: () -> Unit) {
        viewModelScope.launch {
            _guardando.value = true
            _error.value = null

            try {
                RetrofitPasajero.api.crear(pasajero)
                cargar()
                onExito()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al guardar"
            } finally {
                _guardando.value = false
            }
        }
    }
}
