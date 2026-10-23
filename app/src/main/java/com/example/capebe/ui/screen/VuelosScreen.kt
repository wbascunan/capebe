package com.example.capebe.ui.screen

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.GestionVuelosViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlin.math.roundToInt
import androidx.compose.material3.ButtonDefaults

@Composable
fun VuelosScreen(
    onNavigateToRegistroVuelo: () -> Unit,
    onNavigateToAsignacion: () -> Unit,
    onNavigateToOperador: () -> Unit,
    onNavigateToCamara: () -> Unit,
    viewModel: GestionVuelosViewModel = viewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(title = "VUELOS")
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
                text = "Gestión de Vuelos",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    viewModel.cargarVuelos(context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Actualizar Vuelos")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (viewModel.consultaRealizada) {

                if (viewModel.vuelos.isEmpty()) {

                    Text(
                        text = "No hay vuelos registrados."
                    )

                } else {

                    Text(
                        text = "Vuelos registrados: ${viewModel.vuelos.size}"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    for (vuelo in viewModel.vuelos) {

                        val minutos =
                            (vuelo.horasVueloConsumidas * 60)
                                .roundToInt()

                        val horasTexto =
                            String.format(
                                "%.2f",
                                vuelo.horasVueloConsumidas
                            )
                                .trimEnd('0')
                                .trimEnd('.', ',')

                        Text(
                            text = vuelo.matricula,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = vuelo.modelo,
                            color = Color.Gray
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Piloto: ${vuelo.piloto}"
                        )

                        Text(
                            text = "Tripulantes: ${vuelo.numeroTripulantes}"
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Horómetro: ${vuelo.horometroInicial} → ${vuelo.horometroFinal}"
                        )

                        Text(
                            text = "Tiempo de vuelo: $horasTexto h · $minutos min",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    onNavigateToRegistroVuelo()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar Nuevo Vuelo")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    onNavigateToAsignacion()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Asignar Pasajeros")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    onNavigateToCamara()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Evidencia operacional")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    onNavigateToOperador()
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