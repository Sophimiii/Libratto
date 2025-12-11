package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.libratto.model.Libro
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class PrincipalViewModel : ViewModel() {
    //Para guardar datos en la base de datos:
    private val baseDatos = Firebase.firestore


    //Para controlar el estado de la carga:
    var cargando by mutableStateOf(true)


    //Para almacenar los libros:
    var listaLibros by mutableStateOf<List<Libro>>(emptyList())


    //Para gestionar el mensaje a la hora de registrar un usuario:
    var mensajeAlerta by mutableStateOf<String?>(null)
    var operacionExitosa by mutableStateOf<Boolean?>(null)


    //Métodos:
    init {
        obtenerLibros()
    }

    fun obtenerLibros() {
        cargando = true

        baseDatos.collection("libros")
            .get()
            .addOnSuccessListener { resultado ->
                listaLibros = resultado.documents.mapNotNull { documento ->
                    documento.toObject(Libro::class.java)?.copy(isbn = documento.id)
                }
                cargando = false
                operacionExitosa = true
            }
            .addOnFailureListener {
                mensajeAlerta = "Error al cargar libros: ${it.message}"
                cargando = false
                operacionExitosa = false
            }
    }
}