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
            Header(title = "INICIO DEL OPERADOR")
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
                text = "Panel del Operador",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Resumen operacional de la jornada"
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    viewModel.cargarResumen(context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Actualizar Resumen")
            }

            if (viewModel.resumenCargado) {

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Pasajeros registrados: ${viewModel.totalPasajeros}"
                )

                Text(
                    text = "Consentimientos firmados: ${viewModel.consentimientosFirmados}"
                )

                Text(
                    text = "Vuelos registrados: ${viewModel.totalVuelos}"
                )

                Text(
                    text = "Asignaciones realizadas: ${viewModel.totalAsignaciones}"
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = {
                    onNavigateToPasajeros()
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(56.dp)
            ) {
                Text(text = "Pasajeros")
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Button(
                onClick = {
                    onNavigateToVuelos()
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(56.dp)
            ) {
                Text(text = "Vuelos")
            }
        }
    }
}