package com.example.capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.PasajeroViewModel

@Composable
fun RegistroPasajeroScreen(
    onNavigateToConsentimiento: () -> Unit,
    viewModel: PasajeroViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(title = "REGISTRO DE PASAJERO")
        },
        bottomBar = {
            Footer()
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Nuevo Pasajero",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = state.cedulaIdentidad,
                onValueChange = {
                    viewModel.onCedulaChanged(it)
                },
                label = {
                    Text("Cédula de Identidad")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = state.nombreCompleto,
                onValueChange = {
                    viewModel.onNombreChanged(it)
                },
                label = {
                    Text("Nombre Completo")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Categoría"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    viewModel.onCategoriaChanged("Socio Club")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Socio Club")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    viewModel.onCategoriaChanged(
                        "Socio Bienestar BancoEstado"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Socio Bienestar BancoEstado")
            }

            if (state.categoria.isNotBlank()) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Seleccionado: ${state.categoria}"
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedTextField(
                value = state.peso,
                onValueChange = {
                    viewModel.onPesoChanged(it)
                },
                label = {
                    Text("Peso verificado")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (state.errorMessage != null) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = state.errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(
                onClick = {

                    viewModel.onRegistrarClick(context) {

                        Toast.makeText(
                            context,
                            "Pasajero registrado correctamente.",
                            Toast.LENGTH_SHORT
                        ).show()

                        onNavigateToConsentimiento()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar Pasajero")
            }
        }
    }
}