package com.example.libratto.navigation

sealed class Rutas(val ruta: String) {
    object IniciarSesionView : Rutas("IniciarSesionView")
    object RegistroView : Rutas("RegistroView")
    object PrincipalView : Rutas("PrincipalView")
    object PublicarLibroView : Rutas("PublicarLibroView")
    object PerfilUsuarioView : Rutas("PerfilUsuarioView")
}