package com.example.capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.VueloViewModel
import kotlin.math.round
import kotlin.math.roundToInt

@Composable
fun RegistroVueloScreen(
    onNavigateToVuelos: () -> Unit,
    viewModel: VueloViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    val horometroInicial =
        state.horometroInicial
            .replace(",", ".")
            .toDoubleOrNull()

    val horometroFinal =
        state.horometroFinal
            .replace(",", ".")
            .toDoubleOrNull()

    Scaffold(
        topBar = {
            Header(title = "REGISTRAR VUELO")
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
                text = "Nuevo vuelo",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Ingresa los datos de la aeronave y de la operación.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(18.dp))

            // DATOS DE LA AERONAVE

            Text(
                text = "Datos de la aeronave",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

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

            Text(
                text = "Identificador de la aeronave. Ejemplo: CC-ABC",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.modelo,
                onValueChange = {
                    viewModel.onModeloChanged(it)
                },
                label = {
                    Text("Modelo")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Tipo de aeronave. Ejemplo: Cessna 172",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // OPERACIÓN

            Text(
                text = "Datos de la operación",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.piloto,
                onValueChange = {
                    viewModel.onPilotoChanged(it)
                },
                label = {
                    Text("Piloto al mando")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.numeroTripulantes,
                onValueChange = {
                    viewModel.onTripulantesChanged(it)
                },
                label = {
                    Text("Número de tripulantes")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // HORÓMETRO

            Text(
                text = "Lecturas del horómetro",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "El horómetro registra las horas acumuladas de uso de la aeronave.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.horometroInicial,
                onValueChange = {
                    viewModel.onHorometroInicialChanged(it)
                },
                label = {
                    Text("Lectura inicial")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.horometroFinal,
                onValueChange = {
                    viewModel.onHorometroFinalChanged(it)
                },
                label = {
                    Text("Lectura final")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            if (
                horometroInicial != null &&
                horometroFinal != null &&
                horometroFinal > horometroInicial
            ) {

                val horas =
                    round(
                        (horometroFinal - horometroInicial) * 100
                    ) / 100

                val minutos =
                    (horas * 60).roundToInt()

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tiempo calculado: $horas h · $minutos min",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            if (state.errorMessage != null) {

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = state.errorMessage,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(8.dp))

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