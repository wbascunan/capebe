package com.example.capebe.ui.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.ResumenOperacionalViewModel

@Composable
fun OperadorScreen(
    onNavigateToPasajeros: () -> Unit,
    onNavigateToVuelos: () -> Unit,
    viewModel: ResumenOperacionalViewModel = viewModel()
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {

            Text(
                text = "Panel operacional",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Gestiona pasajeros, vuelos y operaciones de la jornada.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Resumen de la jornada",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (viewModel.resumenCargado) {

                Text(
                    text = "Pasajeros registrados",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Text(
                    text = "${viewModel.totalPasajeros}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Consentimientos firmados",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Text(
                    text = "${viewModel.consentimientosFirmados} de ${viewModel.totalPasajeros}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color =
                        if (
                            viewModel.totalPasajeros > 0 &&
                            viewModel.consentimientosFirmados ==
                            viewModel.totalPasajeros
                        ) {
                            Color(0xFF2E7D32)
                        } else {
                            MaterialTheme.colorScheme.primary
                        }
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Vuelos registrados",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Text(
                    text = "${viewModel.totalVuelos}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Asignaciones realizadas",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Text(
                    text = "${viewModel.totalAsignaciones}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            } else {

                Text(
                    text = "Actualiza el resumen para ver el estado actual de la operación.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    viewModel.cargarResumen(context)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5F6368)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text =
                        if (viewModel.resumenCargado) {
                            "Actualizar Resumen"
                        } else {
                            "Cargar Resumen"
                        }
                )
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Text(
                text = "Gestión",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Selecciona el área en la que deseas trabajar.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Button(
                onClick = {
                    onNavigateToPasajeros()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
            ) {
                Text(
                    text = "Gestionar Pasajeros",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Button(
                onClick = {
                    onNavigateToVuelos()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
            ) {
                Text(
                    text = "Gestionar Vuelos",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}