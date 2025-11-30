package com.example.libratto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavegacionCompleto() {
    val controladorNavegacion = rememberNavController()

//    NavHost(
//        navController = controladorNavegacion,
//        startDestination = "IniciarSesionView"
//    ) {
//        composable(Rutas.IniciarSesionView.ruta) { Rutas.IniciarSesionView(controladorNavegacion) }
//    }
}