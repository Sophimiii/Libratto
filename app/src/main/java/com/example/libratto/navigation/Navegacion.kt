package com.example.libratto.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.libratto.view.CompraLibroView
import com.example.libratto.view.IniciarSesionView
import com.example.libratto.view.PerfilUsuarioView
import com.example.libratto.view.PrincipalView
import com.example.libratto.view.PublicarLibroView
import com.example.libratto.view.RegistroView
import com.example.libratto.viewModel.IniciarSesionViewModel
import com.example.libratto.viewModel.PerfilUsuarioViewModel
import com.example.libratto.viewModel.PrincipalViewModel
import com.example.libratto.viewModel.PublicarLibroViewModel
import com.example.libratto.viewModel.RegistroViewModel

@Composable
fun NavegacionCompleta() {
    val controladorNavegacion = rememberNavController()

    NavHost(
        navController = controladorNavegacion,
        startDestination = Rutas.IniciarSesionView.ruta
    ) {
        composable(Rutas.IniciarSesionView.ruta) {
            val inicioSesionVM: IniciarSesionViewModel = viewModel()
            IniciarSesionView(inicioSesionVM, controladorNavegacion)
        }

        composable(Rutas.RegistroView.ruta) {
            val registroVM: RegistroViewModel = viewModel()
            RegistroView(registroVM, controladorNavegacion)
        }

        composable(Rutas.PrincipalView.ruta) {
            val principalVM: PrincipalViewModel = viewModel()
            PrincipalView(principalVM, controladorNavegacion)
        }

        composable(Rutas.PublicarLibroView.ruta) {
            val publicarLibroVM: PublicarLibroViewModel = viewModel()
            PublicarLibroView(publicarLibroVM, controladorNavegacion)
        }

        composable(Rutas.PerfilUsuarioView.ruta) {
            val perfilUsuarioVM: PerfilUsuarioViewModel = viewModel()
            PerfilUsuarioView(perfilUsuarioVM, controladorNavegacion)
        }

        composable(
            route = "${Rutas.CompraLibroView.ruta}/{${Rutas.CompraLibroView.ARG_ISBN}}",
            arguments = listOf(navArgument(Rutas.CompraLibroView.ARG_ISBN) { type = NavType.StringType })
        ) { backStackEntry ->
            val isbn = backStackEntry.arguments?.getString(Rutas.CompraLibroView.ARG_ISBN) ?: ""
            CompraLibroView(isbn = isbn, controladorNavegacion = controladorNavegacion)
        }
    }
}