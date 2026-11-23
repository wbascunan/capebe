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

import com.example.capebe.ui.components.Header
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.viewmodel.VueloApiViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

@Composable
fun InvitadoScreen(
    onCerrarSesion: () -> Unit,
    nombreUsuario : String,
    vm: VueloApiViewModel = viewModel()
) {
    val vuelos by vm.vuelos.observeAsState(emptyList())
    val error by vm.error.observeAsState()
    val context = LocalContext.current
    // Consultar vuelos al entrar como invitado.
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

            // Identificacion del usuario invitado.
            Text(
                text = "Bienvenido, $nombreUsuario",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Surface(
                color = Color(0xFFE1EBF5),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "INVITADO",
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    ),
                    color = Color(0xFF17324D),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Modo consulta: puedes ver vuelos, " +
                        "pero no registrar operaciones.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Panel de invitado",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Bienvenido al Club Aéreo CAPEBE.",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Puedes consultar los vuelos registrados.",
                color = Color.Gray
            )

            HorizontalDivider()

            Text(
                text = "Vuelos disponibles para consulta",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { vm.cargar() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar vuelos")
            }

            if (error != null) {
                Text(
                    text = error ?: "",
                    color = MaterialTheme.colorScheme.error
                )
            }

            if (vuelos.isEmpty() && error == null) {
                Text("No hay vuelos para mostrar.")
            }
// Consulta de vuelos sin permitir modificaciones.
            vuelos.forEach { vuelo ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "${vuelo.matricula} - ${vuelo.modelo}",
                            fontWeight = FontWeight.Bold
                        )

                        Text("Piloto: ${vuelo.piloto}")
                        Text("Tripulantes: ${vuelo.tripulantes}")

                        vuelo.horasVueloConsumidas?.let { horas ->
                            Text("Horas de vuelo: $horas")
                        }

                        // Recurso nativo: compartir informacion del vuelo.
                        Button(
                            onClick = {
                                val mensaje = """
                                    Club Aéreo CAPEBE
                                    Matrícula: ${vuelo.matricula}
                                    Modelo: ${vuelo.modelo}
                                """.trimIndent()

                                val intent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, mensaje)
                                }

                                context.startActivity(
                                    Intent.createChooser(
                                        intent,
                                        "Compartir información del vuelo"
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Compartir vuelo")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Salir de la sesion local.
            OutlinedButton(
                onClick = onCerrarSesion,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}