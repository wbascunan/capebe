package com.example.capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.AsignacionViewModel
import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue

@Composable
fun AsignacionScreen(
    onNavigateToVuelos: () -> Unit,
    viewModel: AsignacionViewModel = viewModel()
) {
    val context = LocalContext.current
    val state = viewModel.uiState

    Scaffold(
        topBar = {
            Header(title = "ASIGNACIÓN DE PASAJEROS")
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
                text = "Asignar Pasajero a Vuelo",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    viewModel.cargarDatos(context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cargar Pasajeros y Vuelos")
            }

            if (viewModel.datosCargados) {

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(text = "Selecciona un pasajero")

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                if (viewModel.pasajeros.isEmpty()) {

                    Text(text = "No hay pasajeros registrados.")

                } else {

                    for (pasajero in viewModel.pasajeros) {

                        val colorPasajero by animateColorAsState(
                            targetValue =
                                if (state.pasajeroId == pasajero.id) {
                                    Color(0xFF2E7D32)
                                } else {
                                    MaterialTheme.colorScheme.primary
                                },
                            label = "colorPasajero"
                        )

                        Button(
                            onClick = {
                                viewModel.seleccionarPasajero(
                                    pasajero.id
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorPasajero
                            )
                        ) {
                            Text(
                                text =
                                    if (state.pasajeroId == pasajero.id) {
                                        "✓ ${pasajero.nombreCompleto}"
                                    } else {
                                        pasajero.nombreCompleto
                                    }
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(text = "Selecciona un vuelo")

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                if (viewModel.vuelos.isEmpty()) {

                    Text(text = "No hay vuelos registrados.")

                } else {

                    for (vuelo in viewModel.vuelos) {

                        val colorVuelo by animateColorAsState(
                            targetValue =
                                if (state.vueloId == vuelo.id) {
                                    Color(0xFF2E7D32)
                                } else {
                                    MaterialTheme.colorScheme.primary
                                },
                            label = "colorVuelo"
                        )

                        Button(
                            onClick = {
                                viewModel.seleccionarVuelo(
                                    vuelo.id
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorVuelo
                            )
                        ) {
                            Text(
                                text =
                                    if (state.vueloId == vuelo.id) {
                                        "✓ ${vuelo.matricula} - ${vuelo.modelo}"
                                    } else {
                                        "${vuelo.matricula} - ${vuelo.modelo}"
                                    }
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )
                    }
                }
            }

            if (state.pasajeroId != null) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Pasajero seleccionado: ${state.pasajeroId}"
                )
            }

            if (state.vueloId != null) {

                Text(
                    text = "Vuelo seleccionado: ${state.vueloId}"
                )
            }

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

                    viewModel.registrarAsignacion(context) {

                        Toast.makeText(
                            context,
                            "Pasajero asignado correctamente.",
                            Toast.LENGTH_SHORT
                        ).show()

                        onNavigateToVuelos()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Confirmar Asignación")
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