package com.example.capebe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capebe.ui.screen.ConsentimientoScreen
import com.example.capebe.ui.screen.InicioScreen
import com.example.capebe.ui.screen.LoginScreen
import com.example.capebe.ui.screen.OperadorScreen
import com.example.capebe.ui.screen.PasajerosScreen
import com.example.capebe.ui.screen.RegisterScreen
import com.example.capebe.ui.screen.RegistroPasajeroScreen
import com.example.capebe.ui.screen.RegistroVueloScreen
import com.example.capebe.ui.screen.VuelosScreen
import com.example.capebe.ui.screen.AsignacionScreen
import com.example.capebe.ui.screen.CamaraScreen

object Routes {
    const val INICIO = "inicio"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val OPERADOR = "operador"
    const val PASAJEROS = "pasajeros"
    const val REGISTRO_PASAJERO = "registro_pasajero"
    const val VUELOS = "vuelos"
    const val REGISTRO_VUELO = "registro_vuelo"
    const val CONSENTIMIENTO = "consentimiento"
    const val ASIGNACION = "asignacion"
    const val CAMARA = "camara"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.INICIO
    ) {

        composable(Routes.INICIO) {
            InicioScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                },
                onLoginSuccess = {
                    navController.navigate(Routes.OPERADOR)
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.OPERADOR) {
            OperadorScreen(
                onNavigateToPasajeros = {
                    navController.navigate(Routes.PASAJEROS)
                },
                onNavigateToVuelos = {
                    navController.navigate(Routes.VUELOS)
                }
            )
        }

        composable(Routes.PASAJEROS) {
            PasajerosScreen(
                onNavigateToRegistroPasajero = {
                    navController.navigate(Routes.REGISTRO_PASAJERO)
                },
                onNavigateToOperador = {
                    navController.navigate(Routes.OPERADOR)
                }
            )
        }

        composable(Routes.REGISTRO_PASAJERO) {
            RegistroPasajeroScreen(
                onNavigateToConsentimiento = {
                    navController.navigate(Routes.CONSENTIMIENTO)
                }
            )
        }

        composable(Routes.VUELOS) {
            VuelosScreen(
                onNavigateToRegistroVuelo = {
                    navController.navigate(Routes.REGISTRO_VUELO)
                },
                onNavigateToAsignacion = {
                    navController.navigate(Routes.ASIGNACION)
                },
                onNavigateToCamara = {
                    navController.navigate(Routes.CAMARA)
                },
                onNavigateToOperador = {
                    navController.navigate(Routes.OPERADOR)
                }
            )
        }

        composable(Routes.REGISTRO_VUELO) {
            RegistroVueloScreen(
                onNavigateToVuelos = {
                    navController.navigate(Routes.VUELOS)
                }
            )
        }

        composable(Routes.CONSENTIMIENTO) {
            ConsentimientoScreen(
                onNavigateToPasajeros = {
                    navController.navigate(Routes.PASAJEROS)
                }
            )
        }

        composable(Routes.ASIGNACION) {
            AsignacionScreen(
                onNavigateToVuelos = {
                    navController.navigate(Routes.VUELOS)
                }
            )
        }

        composable(Routes.CAMARA) {
            CamaraScreen(
                onNavigateToVuelos = {
                    navController.navigate(Routes.VUELOS)
                }
            )
        }
    }
}