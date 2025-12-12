package com.example.libratto.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.libratto.R
import com.example.libratto.model.Libro
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import okio.IOException
import java.util.UUID

class PublicarLibroViewModel : ViewModel() {
    //Variables para las operaciones:
    var isbn by mutableStateOf("")
    var textoErrorISBN by mutableStateOf<String?>(null)

    var nombre by mutableStateOf("")
    var textoErrorNombre by mutableStateOf<String?>(null)

    var descripcion by mutableStateOf("")

    var autor by mutableStateOf("")
    var textoErrorAutor by mutableStateOf<String?>(null)

    var precio by mutableStateOf("")
    var textoErrorPrecio by mutableStateOf<String?>(null)

    //Para guardar datos en la base de datos:
    private val baseDatos = Firebase.firestore
    //Para gestionar el mensaje a la hora de registrar un usuario:
    var mensajeAlerta by mutableStateOf<String?>(null)
    var operacionExitosa by mutableStateOf<Boolean?>(null)


    //Métodos:
    fun añadirLibro(
        isbn: String,
        nombre: String,
        descripcion: String,
        autor: String,
        precio: Double
    ) {
        if (!validarTodo()) return

        val nuevoLibro = Libro(
            isbn = isbn,
            nombre = nombre,
            descripcion = descripcion,
            autor = autor,
            precio = precio,
            imagen = R.drawable.libro_predeterminado.toString()
        )

        baseDatos.collection("libros")
            .document(isbn)
            .set(nuevoLibro)
            .addOnSuccessListener {
                operacionExitosa = true
                mensajeAlerta = "Libro añadido con éxito."
            }
            .addOnFailureListener {
                operacionExitosa = false
                mensajeAlerta = "Error al guardar el libro: ${it.message}"
            }
    }


    //Validaciones:
    fun validarISBN(): Boolean {
        val limpio = isbn.replace("-", "").replace(" ", "")
        val expresionRegex = Regex("""^(?:\d{9}[\dXx]|\d{13})$""")

        return if (limpio.matches(expresionRegex) && limpio.isNotEmpty()) {
            textoErrorISBN = null
            true
        } else {
            textoErrorISBN = "El ISBN no tiene un formato válido."
            false
        }
    }

    fun validarNombre(): Boolean {
        val expresionRegex = Regex("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+( [A-ZÁÉÍÓÚÑa-záéíóúñ]+)*$")
        return if (nombre.trim().matches(expresionRegex)) {
            textoErrorNombre = null
            true
        } else {
            textoErrorNombre = "El nombre solo puede contener letras."
            false
        }
    }

    fun validarAutor(): Boolean {
        val expresionRegex = Regex("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+( [A-ZÁÉÍÓÚÑa-záéíóúñ]+)*$")
        return if (autor.trim().matches(expresionRegex)) {
            textoErrorAutor = null
            true
        } else {
            textoErrorAutor = "El nombre del autor solo puede contener letras."
            false
        }
    }

    fun validarPrecio(): Boolean {
        return if (precio.trim().isNotEmpty()) {
            textoErrorPrecio = null
            true
        } else {
            textoErrorPrecio = "El formato del precio no es válido, debe ser xx.x"
            false
        }
    }

    fun validarTodo(): Boolean {
        val isbnValido = validarISBN()
        val nombreValido = validarNombre()
        val autorValido = validarAutor()
        val precioValido = validarPrecio()

        return isbnValido && nombreValido && autorValido && precioValido

    }
}