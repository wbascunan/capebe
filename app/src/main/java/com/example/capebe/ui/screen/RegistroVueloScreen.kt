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
import com.example.capebe.ui.viewmodel.VueloViewModel

@Composable
fun RegistroVueloScreen(
    onNavigateToVuelos: () -> Unit,
    viewModel: VueloViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(title = "REGISTRO DE VUELO")
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
                text = "Nuevo Vuelo",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = state.matricula,
                onValueChange = {
                    viewModel.onMatriculaChanged(it)
                },
                label = {
                    Text("Matrícula")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.modelo,
                onValueChange = {
                    viewModel.onModeloChanged(it)
                },
                label = {
                    Text("Modelo de Aeronave")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.piloto,
                onValueChange = {
                    viewModel.onPilotoChanged(it)
                },
                label = {
                    Text("Piloto al Mando")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.numeroTripulantes,
                onValueChange = {
                    viewModel.onTripulantesChanged(it)
                },
                label = {
                    Text("Número de Tripulantes")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.horometroInicial,
                onValueChange = {
                    viewModel.onHorometroInicialChanged(it)
                },
                label = {
                    Text("Horómetro Inicial")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.horometroFinal,
                onValueChange = {
                    viewModel.onHorometroFinalChanged(it)
                },
                label = {
                    Text("Horómetro Final")
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
                    color = Color.Red
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {

                    viewModel.onRegistrarClick(context) {

                        Toast.makeText(
                            context,
                            "Vuelo registrado correctamente.",
                            Toast.LENGTH_SHORT
                        ).show()

                        onNavigateToVuelos()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar Vuelo")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    onNavigateToVuelos()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "← Volver")
            }
        }
    }
}