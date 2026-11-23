package com.example.capebe.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.capebe.data.api.RetrofitVuelo
import com.example.capebe.model.VueloApiModel

import kotlinx.coroutines.launch

class VueloApiViewModel : ViewModel() {

    private val _vuelos = MutableLiveData<List<VueloApiModel>>(emptyList())
    val vuelos: LiveData<List<VueloApiModel>> = _vuelos

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private val _guardando = MutableLiveData(false)
    val guardando: LiveData<Boolean> = _guardando

    // GET: cargar vuelos desde PostgreSQL.
    fun cargar() {
        viewModelScope.launch {
            try {
                _error.value = null
                _vuelos.value = RetrofitVuelo.api.listar()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al cargar vuelos"
            }
        }
    }

    // POST: registrar un vuelo.
    fun crear(vuelo: VueloApiModel, onExito: () -> Unit) {
        viewModelScope.launch {
            _guardando.value = true
            _error.value = null

            try {
                RetrofitVuelo.api.crear(vuelo)
                cargar()
                onExito()
            } catch (e: Exception) {
                _error.value = e.message ?: "Error al guardar vuelo"
            } finally {
                _guardando.value = false
            }
        }
    }
}
