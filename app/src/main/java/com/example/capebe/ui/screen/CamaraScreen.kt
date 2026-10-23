package com.example.capebe.ui.screen

import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.CamaraViewModel

@Composable
fun CamaraScreen(
    onNavigateToVuelos: () -> Unit,
    viewModel: CamaraViewModel = viewModel()
) {
    val context = LocalContext.current

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.TakePicturePreview()
        ) { bitmap ->

            viewModel.actualizarFoto(bitmap)
        }

    val imageLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.GetContent()
        ) { uri ->

            if (uri != null) {

                val inputStream =
                    context.contentResolver
                        .openInputStream(uri)

                val bitmap =
                    BitmapFactory.decodeStream(
                        inputStream
                    )

                viewModel.actualizarFoto(bitmap)

                inputStream?.close()
            }
        }

    Scaffold(
        topBar = {
            Header(title = "EVIDENCIA OPERACIONAL")
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
                text = "Registro de evidencia",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Adjunta una fotografía como respaldo de la operación.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Capturar nueva evidencia",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Utiliza la cámara del dispositivo para tomar una fotografía.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Button(
                onClick = {
                    cameraLauncher.launch(null)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Tomar Fotografía")
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "O seleccionar una existente",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Puedes utilizar una imagen disponible en el dispositivo.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Button(
                onClick = {
                    imageLauncher.launch("image/*")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5F6368)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Seleccionar Imagen")
            }

            if (viewModel.foto != null) {

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Evidencia seleccionada",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Image(
                    bitmap =
                        viewModel.foto!!
                            .asImageBitmap(),
                    contentDescription =
                        "Evidencia operacional",
                    contentScale =
                        ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "✓ Evidencia cargada correctamente",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = {
                        viewModel.eliminarFoto()
                    },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                Color(0xFF5F6368)
                        ),
                    modifier =
                        Modifier.fillMaxWidth()
                ) {
                    Text(text = "Quitar Evidencia")
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

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