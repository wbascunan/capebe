package com.example.capebe.ui.screen

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.AsignacionViewModel

@Composable
fun AsignacionScreen(
    onNavigateToVuelos: () -> Unit,
    viewModel: AsignacionViewModel = viewModel()
) {
    val context = LocalContext.current
    val state = viewModel.uiState

    val pasajeroSeleccionado =
        viewModel.pasajeros.find {
            it.id == state.pasajeroId
        }

    val vueloSeleccionado =
        viewModel.vuelos.find {
            it.id == state.vueloId
        }

    Scaffold(
        topBar = {
            Header(title = "ASIGNAR PASAJERO")
        },
        bottomBar = {
            Footer()
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {

            Text(
                text = "Asignación de vuelo",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Selecciona el pasajero y el vuelo al que será asignado.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(
                onClick = {
                    viewModel.cargarDatos(context)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5F6368)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text =
                        if (viewModel.datosCargados) {
                            "Actualizar Datos"
                        } else {
                            "Cargar Pasajeros y Vuelos"
                        }
                )
            }

            if (viewModel.datosCargados) {

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                Text(
                    text = "1. Selecciona un pasajero",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Elige quién participará en el vuelo.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                if (viewModel.pasajeros.isEmpty()) {

                    Text(
                        text = "No hay pasajeros registrados.",
                        color = Color.Gray
                    )

                } else {

                    for (pasajero in viewModel.pasajeros) {

                        val colorPasajero by animateColorAsState(
                            targetValue =
                                if (
                                    state.pasajeroId ==
                                    pasajero.id
                                ) {
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
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorPasajero
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text =
                                    if (
                                        state.pasajeroId ==
                                        pasajero.id
                                    ) {
                                        "✓ ${pasajero.nombreCompleto}"
                                    } else {
                                        pasajero.nombreCompleto
                                    }
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "2. Selecciona un vuelo",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Elige la aeronave y operación correspondiente.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                if (viewModel.vuelos.isEmpty()) {

                    Text(
                        text = "No hay vuelos registrados.",
                        color = Color.Gray
                    )

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
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorVuelo
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text =
                                    if (
                                        state.vueloId ==
                                        vuelo.id
                                    ) {
                                        "✓ ${vuelo.matricula} · ${vuelo.modelo}"
                                    } else {
                                        "${vuelo.matricula} · ${vuelo.modelo}"
                                    }
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )
                    }
                }
            }

            if (
                pasajeroSeleccionado != null ||
                vueloSeleccionado != null
            ) {

                Spacer(
                    modifier = Modifier.height(22.dp)
                )

                Text(
                    text = "Resumen de la asignación",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                if (pasajeroSeleccionado != null) {
                    Text(
                        text = "Pasajero: ${pasajeroSeleccionado.nombreCompleto}"
                    )
                }

                if (vueloSeleccionado != null) {
                    Text(
                        text = "Vuelo: ${vueloSeleccionado.matricula} · ${vueloSeleccionado.modelo}"
                    )

                    Text(
                        text = "Piloto: ${vueloSeleccionado.piloto}",
                        color = Color.Gray
                    )
                }
            }

            if (state.errorMessage != null) {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = state.errorMessage,
                    color = Color.Red
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
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
                Text(
                    text = "Confirmar Asignación",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    onNavigateToVuelos()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5F6368)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "← Volver")
            }
        }
    }
}