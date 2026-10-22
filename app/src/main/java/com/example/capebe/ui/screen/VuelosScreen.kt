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

@Composable
fun VuelosScreen(
    onNavigateToRegistroVuelo: () -> Unit,
    onNavigateToOperador: () -> Unit,
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
                Text(text = "Consultar Vuelos")
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

                        Text(
                            text = vuelo.matricula,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Modelo: ${vuelo.modelo}"
                        )

                        Text(
                            text = "Piloto: ${vuelo.piloto}"
                        )

                        Text(
                            text = "Tripulantes: ${vuelo.numeroTripulantes}"
                        )

                        Text(
                            text = "Horómetro inicial: ${vuelo.horometroInicial}"
                        )

                        Text(
                            text = "Horómetro final: ${vuelo.horometroFinal}"
                        )

                        Text(
                            text = "Horas consumidas: ${vuelo.horasVueloConsumidas}"
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
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
                    onNavigateToOperador()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "← Volver")
            }
        }
    }
}