package com.example.libratto.navigation

sealed class Rutas(val ruta: String) {
    object IniciarSesionView : Rutas("IniciarSesionView")
    object RegistroView : Rutas("RegistroView")
    object PrincipalView : Rutas("PrincipalView")
    object PublicarLibroView : Rutas("PublicarLibroView")
    object PerfilUsuarioView : Rutas("PerfilUsuarioView")
    object CompraLibroView : Rutas("CompraLibroView") {
        fun crearRuta(isbn: String) = "CompraLibroView/$isbn"
        const val ARG_ISBN = "isbn"
    }
}