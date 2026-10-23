package com.example.capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.capebe.ui.components.Footer
import com.example.capebe.ui.components.Header
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.capebe.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color

@Composable
fun InicioScreen(
    onNavigateToLogin: () -> Unit
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_capebe),
                    contentDescription = "Logo CAPEBE",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(220.dp)
                        .shadow(12.dp, shape = CircleShape)
                        .clip(CircleShape)
                        .border(
                            4.dp,
                            Color(0xFF17324D),
                            CircleShape
                        )
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Gestión y Registro de Vuelos Recreativos"
                )

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Bienvenido a CAPEBE",
                            Toast.LENGTH_SHORT
                        ).show()

                        onNavigateToLogin()
                    }
                ) {
                    Text(text = "Ingresar")
                }
            }
        }
    }
}