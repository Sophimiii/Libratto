package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PublicarLibroViewModel : ViewModel() {
    //Variables para las operaciones
    var isbn by mutableStateOf("")
    var textoErrorISBN by mutableStateOf<String?>(null)

    val nombre by mutableStateOf("")
    var textoErrorNombre by mutableStateOf<String?>(null)

    val descripcion by mutableStateOf("")
    var textoErrorDescripcion by mutableStateOf<String?>(null)

    val autor by mutableStateOf("")
    var textoErrorAutor by mutableStateOf<String?>(null)

    val precio by mutableStateOf(0.0)
    var textoErrorPrecio by mutableStateOf<Double?>(0.0)

    fun añadirLibro() {

    }

    fun editarLibro() {

    }

    fun eliminarLibro() {

    }
}