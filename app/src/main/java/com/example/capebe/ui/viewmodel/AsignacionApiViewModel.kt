package com.example.capebe.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.capebe.data.api.RetrofitAsignacion
import com.example.capebe.data.api.RetrofitPasajero
import com.example.capebe.data.api.RetrofitVuelo

import com.example.capebe.model.AsignacionApiModel
import com.example.capebe.model.PasajeroApiModel
import com.example.capebe.model.VueloApiModel

import kotlinx.coroutines.launch

import retrofit2.HttpException

class AsignacionApiViewModel : ViewModel() {

    private val _pasajeros =
        MutableLiveData<List<PasajeroApiModel>>(emptyList())
    val pasajeros: LiveData<List<PasajeroApiModel>> = _pasajeros

    private val _vuelos =
        MutableLiveData<List<VueloApiModel>>(emptyList())
    val vuelos: LiveData<List<VueloApiModel>> = _vuelos

    private val _asignaciones =
        MutableLiveData<List<AsignacionApiModel>>(emptyList())
    val asignaciones: LiveData<List<AsignacionApiModel>> =
        _asignaciones

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private val _guardando = MutableLiveData(false)
    val guardando: LiveData<Boolean> = _guardando

    // GET: cargar datos de los tres microservicios.
    fun cargar() {
        viewModelScope.launch {
            try {
                _error.value = null

                _pasajeros.value = RetrofitPasajero.api.listar()
                _vuelos.value = RetrofitVuelo.api.listar()
                _asignaciones.value = RetrofitAsignacion.api.listar()

            } catch (e: Exception) {
                _error.value = e.message ?: "Error de conexion"
            }
        }
    }

    // POST: registrar la asignacion en PostgreSQL.
    fun crear(a: AsignacionApiModel, onExito: () -> Unit) {
        viewModelScope.launch {
            _guardando.value = true
            _error.value = null

            try {
                RetrofitAsignacion.api.crear(a)
                cargar()
                onExito()

        } catch (e: Exception) {
            _error.value = when {
                e is HttpException && e.code() == 400 ->
                    "No se pudo registrar: el pasajero ya está " +
                            "asignado o los datos no son válidos."

                else ->
                    e.message ?: "No se pudo asignar el pasajero"
            }
        }
        finally {
                _guardando.value = false
            }
        }
    }
}
