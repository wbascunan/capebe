package com.example.capebe.ui.navigation

import androidx.compose.runtime.Composable
import com.example.capebe.ui.screen.InvitadoScreen
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
import com.example.capebe.ui.screen.PasajerosApiScreen
import com.example.capebe.ui.screen.VuelosApiScreen
import com.example.capebe.ui.screen.AsignacionesApiScreen
import com.example.capebe.model.RolUsuario
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable

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
    const val PASAJEROS_API = "pasajeros_api"
    const val VUELOS_API = "vuelos_api"
    const val ASIGNACIONES_API = "asignaciones_api"
    const val INVITADO = "invitado"
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    // Nombre del usuario que inicio sesion.
    var nombreUsuario by rememberSaveable {
        mutableStateOf("")
    }

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
                onLoginSuccess = { nombre, rol ->

                    // El rol determina que panel se abre.
                    val destino = when (rol) {
                        RolUsuario.INVITADO.name -> Routes.INVITADO
                        RolUsuario.COLABORADOR.name -> Routes.OPERADOR
                        else -> null
                    }

                    if (destino != null) {
                        nombreUsuario = nombre

                        navController.navigate(destino) {
                            popUpTo(Routes.LOGIN) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
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
                nombreUsuario = nombreUsuario,
                onNavigateToPasajeros = {
                    navController.navigate(Routes.PASAJEROS)
                },
                onNavigateToPasajerosApi = {
                    navController.navigate(Routes.PASAJEROS_API)
                },
                onNavigateToVuelos = {
                    navController.navigate(Routes.VUELOS)
                },

                onNavigateToVuelosApi = {
                    navController.navigate(Routes.VUELOS_API)
                },

                onNavigateToAsignacionesApi = {
                    navController.navigate(Routes.ASIGNACIONES_API)
                },
                onCerrarSesion = {
                    nombreUsuario = ""
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.INICIO) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
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
                },
                onNavigateToPasajeros = {
                    navController.navigate(Routes.PASAJEROS)
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

        composable(Routes.PASAJEROS_API) {
            PasajerosApiScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.VUELOS_API) {
            VuelosApiScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ASIGNACIONES_API) {
            AsignacionesApiScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.INVITADO) {
            InvitadoScreen(
                nombreUsuario = nombreUsuario,
                onCerrarSesion = {
                    nombreUsuario = ""

                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.INICIO) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }


    }
}