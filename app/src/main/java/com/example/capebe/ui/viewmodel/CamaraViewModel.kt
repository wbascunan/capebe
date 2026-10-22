package com.example.capebe.ui.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CamaraViewModel : ViewModel() {

    var foto by mutableStateOf<Bitmap?>(null)
        private set

    fun actualizarFoto(nuevaFoto: Bitmap?) {
        foto = nuevaFoto
    }

    fun eliminarFoto() {
        foto = null
    }
}