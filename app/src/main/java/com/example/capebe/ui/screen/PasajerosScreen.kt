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
import com.example.capebe.ui.viewmodel.GestionPasajerosViewModel

@Composable
fun PasajerosScreen(
    onNavigateToRegistroPasajero: () -> Unit,
    onNavigateToOperador: () -> Unit,
    viewModel: GestionPasajerosViewModel = viewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(title = "PASAJEROS")
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
                text = "Gestión de Pasajeros",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    viewModel.cargarPasajeros(context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Consultar Pasajeros")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (viewModel.consultaRealizada) {

                if (viewModel.pasajeros.isEmpty()) {

                    Text(
                        text = "No hay pasajeros registrados."
                    )

                } else {

                    Text(
                        text = "Pasajeros registrados: ${viewModel.pasajeros.size}"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    for (pasajero in viewModel.pasajeros) {

                        Text(
                            text = pasajero.nombreCompleto,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Cédula: ${pasajero.cedulaIdentidad}"
                        )

                        Text(
                            text = "Categoría: ${pasajero.categoria}"
                        )

                        Text(
                            text = "Peso: ${pasajero.peso} kg"
                        )

                        Text(
                            text = "Consentimiento: ${pasajero.estadoConsentimiento}"
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    onNavigateToRegistroPasajero()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar Nuevo Pasajero")
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