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

import com.example.capebe.model.AsignacionApiModel
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.viewmodel.AsignacionApiViewModel

@Composable
fun AsignacionesApiScreen(
    onVolver: () -> Unit,
    vm: AsignacionApiViewModel = viewModel()
) {
    val pasajeros by vm.pasajeros.observeAsState(emptyList())
    val vuelos by vm.vuelos.observeAsState(emptyList())
    val asignaciones by vm.asignaciones.observeAsState(emptyList())
    val error by vm.error.observeAsState()
    val guardando by vm.guardando.observeAsState(false)

    var pasajeroId by remember { mutableStateOf<Long?>(null) }
    var vueloId by remember { mutableStateOf<Long?>(null) }
    var aviso by remember { mutableStateOf("") }

    // Filtrar pasajeros que ya tienen una asignacion.
    val disponibles = pasajeros.filter { p ->
        p.id != null && asignaciones.none { a ->
            a.pasajeroId == p.id
        }
    }


    val azul = Color(0xFF17324D)
    val verde = Color(0xFF2E7D32)

    // Cargar los datos de los microservicios.
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
                "Asignación de pasajeros",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Conectado a Spring Boot y PostgreSQL",
                color = Color.Gray
            )

            // Seleccionar pasajero.
            Text(
                "Seleccionar pasajero",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            if (pasajeros.isEmpty()) {
                Text("No hay pasajeros disponibles en el backend.")
            }

            // Mostrar solo pasajeros sin asignacion.
            if (disponibles.isEmpty()) {
                Text(
                    text = if (pasajeros.isEmpty()) {
                        "No hay pasajeros registrados en PostgreSQL."
                    } else {
                        "Todos los pasajeros ya tienen una asignación. " +
                                "Registra uno nuevo desde Pasajeros Spring Boot."
                    },
                    color = Color.Gray
                )
            }

            disponibles.forEach { p ->
                val id = p.id ?: return@forEach

                Button(
                    onClick = {
                        pasajeroId = id
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (pasajeroId == id) verde else azul
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        (if (pasajeroId == id) "✓ " else "") +
                                p.nombreCompleto
                    )
                }
            }


            // Seleccionar vuelo.
            Text(
                "Seleccionar vuelo",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            if (vuelos.isEmpty()) {
                Text("No hay vuelos disponibles en el backend.")
            }

            vuelos.forEach { v ->
                if (v.id != null) {
                    Button(
                        onClick = { vueloId = v.id },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (vueloId == v.id) verde else azul
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            (if (vueloId == v.id) "✓ " else "") +
                                    "${v.matricula} - ${v.modelo}"
                        )
                    }
                }
            }

            // Registrar asignacion.
            Button(
                enabled = !guardando,
                onClick = {
                    val pId = pasajeroId
                    val vId = vueloId

                    if (pId == null || vId == null) {
                        aviso = "Selecciona pasajero y vuelo"
                    } else {
                        aviso = ""

                        vm.crear(
                            AsignacionApiModel(
                                pasajeroId = pId,
                                vueloId = vId
                            )
                        ) {
                            pasajeroId = null
                            vueloId = null
                            aviso = "Asignación registrada"
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
                    else "Registrar asignación"
                )
            }

            if (aviso.isNotEmpty()) {
                Text(aviso)
            }

            if (error != null) {
                Text(
                    error ?: "",
                    color = MaterialTheme.colorScheme.error
                )
            }

            HorizontalDivider()

            Text(
                "Asignaciones registradas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Button(onClick = { vm.cargar() }) {
                Text("Actualizar")
            }

            Text("Total: ${asignaciones.size}")

            // Mostrar nombres en vez de solo ID.
            asignaciones.forEach { a ->

                val nombre = pasajeros
                    .find { it.id == a.pasajeroId }
                    ?.nombreCompleto ?: "Pasajero ${a.pasajeroId}"

                val matricula = vuelos
                    .find { it.id == a.vueloId }
                    ?.matricula ?: "Vuelo ${a.vueloId}"

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(nombre, fontWeight = FontWeight.Bold)
                        Text("Vuelo: $matricula")
                        Text("Asignación N° ${a.id}")
                    }
                }
            }
        }
    }
}
