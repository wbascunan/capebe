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
import com.example.capebe.ui.viewmodel.PasajeroViewModel

@Composable
fun RegistroPasajeroScreen(
    onNavigateToConsentimiento: () -> Unit,
    onNavigateToPasajeros: () -> Unit,
    viewModel: PasajeroViewModel = viewModel()
) {
    val state = viewModel.uiState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(title = "REGISTRAR PASAJERO")
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
                text = "Nuevo pasajero",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Ingresa los datos de la persona que participará en la operación.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // IDENTIFICACIÓN

            Text(
                text = "Identificación",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.cedulaIdentidad,
                onValueChange = {
                    viewModel.onCedulaChanged(it)
                },
                label = {
                    Text("Cédula de identidad")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Documento utilizado para identificar al pasajero.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.nombreCompleto,
                onValueChange = {
                    viewModel.onNombreChanged(it)
                },
                label = {
                    Text("Nombre completo")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // CATEGORÍA

            Text(
                text = "Categoría",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Selecciona la categoría correspondiente.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    viewModel.onCategoriaChanged("Socio Club")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (state.categoria == "Socio Club") {
                            Color(0xFF2E7D32)
                        } else {
                            MaterialTheme.colorScheme.primary
                        }
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text =
                        if (state.categoria == "Socio Club") {
                            "✓ Socio Club"
                        } else {
                            "Socio Club"
                        }
                )
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
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (
                            state.categoria ==
                            "Socio Bienestar BancoEstado"
                        ) {
                            Color(0xFF2E7D32)
                        } else {
                            MaterialTheme.colorScheme.primary
                        }
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text =
                        if (
                            state.categoria ==
                            "Socio Bienestar BancoEstado"
                        ) {
                            "✓ Socio Bienestar BancoEstado"
                        } else {
                            "Socio Bienestar BancoEstado"
                        }
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // DATOS OPERACIONALES

            Text(
                text = "Datos operacionales",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = state.peso,
                onValueChange = {
                    viewModel.onPesoChanged(it)
                },
                label = {
                    Text("Peso verificado")
                },
                suffix = {
                    Text("kg")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Ingresa el peso verificado del pasajero.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
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
                modifier = Modifier.height(18.dp)
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
                Text(text = "Continuar a Consentimiento")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    onNavigateToPasajeros()
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