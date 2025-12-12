package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.libratto.R
import com.example.libratto.model.Compra
import com.example.libratto.model.Libro
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class CompraLibroViewModel : ViewModel() {
    //Para guardar datos en la base de datos:
    private val baseDatos = Firebase.firestore


    //Para obtener el libro seleccionado:
    var libroSeleccionado by mutableStateOf<Libro?>(null)


    //Para controlar el estado de la carga:
    var cargando by mutableStateOf(true)


    //Para gestionar el mensaje a la hora de registrar un usuario:
    var mensajeAlerta by mutableStateOf<String?>(null)
    var operacionExitosa by mutableStateOf<Boolean?>(null)


    //Métodos:
    fun cargarLibro(isbn: String) {
        cargando = true

        baseDatos.collection("libros")
            .whereEqualTo("isbn", isbn)
            .get()
            .addOnSuccessListener { documento ->
                if(!documento.isEmpty) {
                    libroSeleccionado = documento.first().toObject(Libro::class.java)
                }
                cargando = false
                operacionExitosa = true
            }
            .addOnFailureListener {
                cargando = false
                operacionExitosa = false
                mensajeAlerta = "Error al cargar libro: ${it.message}"
            }
    }

    fun guardarCompra(
        isbn: String,
        precio: Double,
        correoUsuario: String,
        calificacion: Int
    ) {
        val nuevaCompra = Compra(
            isbn = isbn,
            precio = precio,
            correoUsuario = correoUsuario,
            fecha = Timestamp.now(),
            calificacion = calificacion,
        )

        baseDatos.collection("compras")
            .add(nuevaCompra)
            .addOnSuccessListener {
                operacionExitosa = true
                mensajeAlerta = "Compra registrada con éxito."
            }
            .addOnFailureListener {
                operacionExitosa = false
                mensajeAlerta = "Error al registrar la compra: ${it.message}"
            }
    }
}