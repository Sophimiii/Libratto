package com.example.libratto.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.libratto.model.Libro
import com.example.libratto.model.Usuario
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage

class PublicarLibroViewModel : ViewModel() {
    //Variables para las operaciones:
    var isbn by mutableStateOf("")
    var textoErrorISBN by mutableStateOf<String?>(null)

    var nombre by mutableStateOf("")
    var textoErrorNombre by mutableStateOf<String?>(null)

    var descripcion by mutableStateOf("")
    var textoErrorDescripcion by mutableStateOf<String?>(null)

    var autor by mutableStateOf("")
    var textoErrorAutor by mutableStateOf<String?>(null)

    var precio by mutableStateOf("")
    var textoErrorPrecio by mutableStateOf<String?>(null)


    //Para gestionar la imagen:
    var imagenUri by mutableStateOf<Uri?>(null)


    //Para guardar datos en la base de datos:
    private val baseDatos = Firebase.firestore
    private val almacenamiento = Firebase.storage


    //Métodos:
    fun añadirLibro(isbn: String, nombre: String, descripcion: String, autor: String, precio: String) {


        val nuevoLibro = Libro(isbn, nombre, descripcion, autor, precio.toDouble())

        baseDatos.collection("libros")
            .add(nuevoLibro)
            .addOnSuccessListener {
                println("Libro registrado con éxito")
            }
            .addOnFailureListener {
                println("Ha ocurrido un error")
            }
    }

    fun editarLibro() {

    }

    fun eliminarLibro() {

    }


    //Validaciones:
    fun validarISBN(): Boolean {
        val limpio = isbn.replace("-", "").replace(" ","")
        val expresionRegex = Regex("""^(?:\d{9}[\dXx]|\d{13})$""")

        return if(isbn.matches(expresionRegex)) {
            textoErrorISBN = null
            true
        } else {
            textoErrorISBN = "El ISBN no tiene un formato válido."
            false
        }
    }

    fun validarNombre(): Boolean {
        val expresionRegex = Regex("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+( [A-ZÁÉÍÓÚÑa-záéíóúñ]+)?$")
        return if(nombre.trim().matches(expresionRegex)) {
            textoErrorNombre = null
            true
        } else {
            textoErrorNombre = "El nombre solo puede contener letras."
            false
        }
    }

    fun validarAutor(): Boolean {
        val expresionRegex = Regex("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+( [A-ZÁÉÍÓÚÑa-záéíóúñ]+)?$")
        return if(autor.trim().matches(expresionRegex)) {
            textoErrorAutor = null
            true
        } else {
            textoErrorAutor = "El nombre del autor solo puede contener letras."
            false
        }
    }

    fun validarPrecio(): Boolean {
        return if(!precio.trim().isEmpty()) {
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