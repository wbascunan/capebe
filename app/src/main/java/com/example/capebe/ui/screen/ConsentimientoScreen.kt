package com.example.capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import com.example.capebe.ui.viewmodel.FirmaViewModel

@Composable
fun ConsentimientoScreen(
    onNavigateToPasajeros: () -> Unit,
    viewModel: FirmaViewModel = viewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(title = "CONSENTIMIENTO Y FIRMA")
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
                text = "Consentimiento de Vuelo",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "El pasajero declara aceptar las condiciones del vuelo recreativo."
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Firma del pasajero"
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(Color.White)
                    .pointerInput(Unit) {

                        detectDragGestures(

                            onDragStart = { offset ->
                                viewModel.agregarPunto(offset)
                            },

                            onDragEnd = {
                                viewModel.terminarTrazo()
                            },

                            onDragCancel = {
                                viewModel.terminarTrazo()
                            },

                            onDrag = { change, _ ->
                                viewModel.agregarPunto(
                                    change.position
                                )
                            }
                        )
                    }
            ) {

                var puntoAnterior: Offset? = null

                for (punto in viewModel.puntos) {

                    if (punto == null) {

                        puntoAnterior = null

                    } else {

                        if (puntoAnterior != null) {

                            drawLine(
                                color = Color.Black,
                                start = puntoAnterior!!,
                                end = punto,
                                strokeWidth = 5f
                            )
                        }

                        puntoAnterior = punto
                    }
                }
            }

            if (viewModel.errorMessage != null) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = viewModel.errorMessage!!,
                    color = Color.Red
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    viewModel.limpiarFirma()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Limpiar Firma")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {

                    viewModel.guardarFirma(context) {

                        Toast.makeText(
                            context,
                            "Consentimiento firmado correctamente.",
                            Toast.LENGTH_SHORT
                        ).show()

                        onNavigateToPasajeros()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Guardar Firma")
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = {
                    onNavigateToPasajeros()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "← Volver")
            }
        }
    }
}