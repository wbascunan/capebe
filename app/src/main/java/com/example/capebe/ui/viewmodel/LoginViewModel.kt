package com.example.capebe.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capebe.data.database.AppDatabase
import com.example.capebe.model.LoginModel
import kotlinx.coroutines.launch
import com.example.capebe.model.RolUsuario

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginModel())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onEmailChanged(email: String) {
        uiState = uiState.copy(email = email)
        errorMessage = null
    }

    fun onPasswordChanged(password: String) {
        uiState = uiState.copy(password = password)
        errorMessage = null
    }

    fun onLoginClick(
        context: Context,
        onSuccess: (String, String) -> Unit
    ) {
        val email = uiState.email
        val password = uiState.password

        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Ingresa correo y contraseña."
            return
        }

        viewModelScope.launch {

            val db = AppDatabase.getDatabase(context)

            val user = db.usuarioDao().getUserByEmail(email)

            if (user != null && user.password == password) {

                // Validar el rol guardado en SQLite.
                when (user.rol) {

                    RolUsuario.INVITADO.name,
                    RolUsuario.COLABORADOR.name -> {
                        errorMessage = null

                        // Antes onSuccess() no entregaba el rol.
                        onSuccess(user.name, user.rol)
                    }

                    else -> {
                        errorMessage = "El usuario tiene un rol no válido."
                    }
                }

            } else {
                errorMessage = "Correo o contraseña incorrectos."
            }
        }
    }
}