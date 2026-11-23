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

import com.example.capebe.model.VueloApiModel
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.viewmodel.VueloApiViewModel

@Composable
fun VuelosApiScreen(
    onVolver: () -> Unit,
    vm: VueloApiViewModel = viewModel()
) {
    val vuelos by vm.vuelos.observeAsState(emptyList())
    val error by vm.error.observeAsState()
    val guardando by vm.guardando.observeAsState(false)

    var matricula by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var piloto by remember { mutableStateOf("") }
    var tripulantes by remember { mutableStateOf("") }
    var inicial by remember { mutableStateOf("") }
    var final by remember { mutableStateOf("") }
    var aviso by remember { mutableStateOf("") }

    val azul = Color(0xFF17324D)

    // Cargar vuelos al abrir la pantalla.
    LaunchedEffect(Unit) {
        vm.cargar()
    }

    Scaffold(
        topBar = { Header(title = "CAPEBE") },
        bottomBar = { Footer() }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedButton(onClick = onVolver) {
                Text("← Volver al panel")
            }

            Text(
                text = "Gestión de vuelos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Registro conectado a Spring Boot",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF2F5FA)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        "Datos de la aeronave",
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = matricula,
                        onValueChange = { matricula = it },
                        label = { Text("Matrícula (CC-ABC)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = modelo,
                        onValueChange = { modelo = it },
                        label = { Text("Modelo (Cessna 172)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        "Datos de la operación",
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = piloto,
                        onValueChange = { piloto = it },
                        label = { Text("Piloto") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = tripulantes,
                        onValueChange = { tripulantes = it },
                        label = { Text("Cantidad de tripulantes") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        "Lecturas del horómetro",
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = inicial,
                        onValueChange = { inicial = it },
                        label = { Text("Horómetro inicial") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = final,
                        onValueChange = { final = it },
                        label = { Text("Horómetro final") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        enabled = !guardando,
                        onClick = {

                            val trip = tripulantes.toIntOrNull()
                            val ini = inicial.replace(",", ".")
                                .toDoubleOrNull()
                            val fin = final.replace(",", ".")
                                .toDoubleOrNull()

                            if (matricula.isBlank() ||
                                modelo.isBlank() ||
                                piloto.isBlank() ||
                                trip == null || trip <= 0 ||
                                ini == null || fin == null ||
                                !ini.isFinite() || !fin.isFinite() ||
                                ini < 0 || fin <= ini) {

                                aviso = "Revisa los datos del vuelo"

                            } else {

                                aviso = ""

                                vm.crear(
                                    VueloApiModel(
                                        matricula = matricula.trim(),
                                        modelo = modelo.trim(),
                                        piloto = piloto.trim(),
                                        tripulantes = trip,
                                        horometroInicial = ini,
                                        horometroFinal = fin
                                    )
                                ) {
                                    matricula = ""
                                    modelo = ""
                                    piloto = ""
                                    tripulantes = ""
                                    inicial = ""
                                    final = ""

                                    aviso = "Vuelo registrado"
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = azul
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            if (guardando) "Guardando..."
                            else "Registrar vuelo"
                        )
                    }
                }
            }

            if (aviso.isNotEmpty()) {
                Text(aviso, color = Color(0xFF2E7D32))
            }

            if (error != null) {
                Text(
                    error ?: "",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Text(
                "Vuelos registrados",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { vm.cargar() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5F6368)
                )
            ) {
                Text("Actualizar vuelos")
            }

            Text("Total en PostgreSQL: ${vuelos.size}")

            vuelos.forEach { vuelo ->

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Text(
                            "${vuelo.matricula} - ${vuelo.modelo}",
                            fontWeight = FontWeight.Bold
                        )

                        Text("Piloto: ${vuelo.piloto}")
                        Text("Tripulantes: ${vuelo.tripulantes}")

                        Text(
                            "Horómetro: ${vuelo.horometroInicial} - " +
                                    "${vuelo.horometroFinal}"
                        )

                        vuelo.horasVueloConsumidas?.let {
                            Text("Horas consumidas: $it h")
                        }
                    }
                }
            }
        }
    }
}
