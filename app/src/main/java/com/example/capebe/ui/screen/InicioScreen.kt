package com.example.capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header

@Composable
fun InicioScreen(
    onNavigateToLogin: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(title = "CAPEBE")
        },
        bottomBar = {
            Footer()
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Gestión y Registro de Vuelos Recreativos"
                )

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Bienvenido a CAPEBE",
                            Toast.LENGTH_SHORT
                        ).show()

                        onNavigateToLogin()
                    }
                ) {
                    Text(text = "Ingresar")
                }
            }
        }
    }
}