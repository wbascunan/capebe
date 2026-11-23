package com.example.capebe.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.capebe.model.PasajeroApiModel
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.viewmodel.PasajeroApiViewModel

@Composable
fun PasajerosApiScreen(
    onVolver: () -> Unit,
    vm: PasajeroApiViewModel = viewModel()
) {
    val pasajeros by vm.lista.observeAsState(emptyList())
    val error by vm.error.observeAsState()
    val guardando by vm.guardando.observeAsState(false)

    var cedula by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Socio Club") }
    var aviso by remember { mutableStateOf("") }

    val azul = Color(0xFF17324D)
    val verde = Color(0xFF2E7D32)

    // Cargar pasajeros desde el backend.
    LaunchedEffect(Unit) {
        vm.cargar()
    }

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
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Regresar al panel del operador.
            OutlinedButton(
                onClick = onVolver
            ) {
                Text("← Volver al panel")
            }

            Text(
                text = "Gestión de pasajeros",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Registro conectado a Spring Boot y PostgreSQL",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            // Formulario de pasajeros.
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF2F5FA)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Nuevo pasajero",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = cedula,
                        onValueChange = { cedula = it },
                        label = { Text("Cédula de identidad") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre completo") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "Categoría de socio",
                        fontWeight = FontWeight.Bold
                    )

                    // Se marca en verde la opción seleccionada.
                    Button(
                        onClick = {
                            categoria = "Socio Club"
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (categoria == "Socio Club")
                                    verde else azul
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            if (categoria == "Socio Club")
                                "✓ Socio Club" else "Socio Club"
                        )
                    }

                    Button(
                        onClick = {
                            categoria = "Socio Bienestar BancoEstado"
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (categoria == "Socio Bienestar BancoEstado")
                                    verde else azul
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            if (categoria == "Socio Bienestar BancoEstado")
                                "✓ Socio Bienestar BancoEstado"
                            else
                                "Socio Bienestar BancoEstado"
                        )
                    }

                    OutlinedTextField(
                        value = peso,
                        onValueChange = { peso = it },
                        label = { Text("Peso verificado (kg)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Validar y registrar mediante Retrofit.
                    Button(
                        enabled = !guardando,
                        onClick = {
                            val kg = peso.replace(",", ".")
                                .toDoubleOrNull()

                            if (cedula.isBlank() ||
                                nombre.isBlank() ||
                                kg == null ||
                                !kg.isFinite() ||
                                kg <= 0) {

                                aviso = "Revisa los datos ingresados"

                            } else {
                                aviso = ""

                                vm.crear(
                                    PasajeroApiModel(
                                        cedulaIdentidad = cedula.trim(),
                                        nombreCompleto = nombre.trim(),
                                        categoria = categoria,
                                        peso = kg
                                    )
                                ) {
                                    cedula = ""
                                    nombre = ""
                                    peso = ""
                                    aviso = "Pasajero registrado"
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = azul
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            if (guardando)
                                "Guardando..."
                            else
                                "Registrar pasajero"
                        )
                    }
                }
            }

            // Mensajes del formulario.
            if (aviso.isNotEmpty()) {
                Text(
                    text = aviso,
                    color = verde
                )
            }

            // Error de conexión o del backend.
            if (error != null) {
                Text(
                    text = error ?: "",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pasajeros registrados",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { vm.cargar() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5F6368)
                )
            ) {
                Text("Actualizar pasajeros")
            }

            Text(
                text = "Total en PostgreSQL: ${pasajeros.size}",
                style = MaterialTheme.typography.bodyMedium
            )

            // Lista recibida desde Spring Boot.
            pasajeros.forEach { p ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement =
                            Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = p.nombreCompleto,
                            fontWeight = FontWeight.Bold
                        )

                        Text("Cédula: ${p.cedulaIdentidad}")
                        Text("Categoría: ${p.categoria}")
                        Text("Peso: ${p.peso} kg")
                        Text(
                            "Consentimiento: ${p.estadoConsentimiento}"
                        )
                    }
                }
            }
        }
    }
}
