package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class IniciarSesionViewModel : ViewModel() {
    //Variables:
    var correo by mutableStateOf("")
    var textoErrorCorreo by mutableStateOf<String?>(null)

    var contraseña by mutableStateOf("")
    var textoErrorContraseña by mutableStateOf<String?>(null)


    //Para comprobar datos en la base de datos:
    private val autentificacion: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val baseDatos = Firebase.firestore


    //Para gestionar el mensaje a la hora de registrar un usuario:
    var mensajeAlerta by mutableStateOf<String?>(null)
    var operacionExitosa by mutableStateOf<Boolean?>(null)


    //Métodos:
    fun iniciarSesion() {

    }


    //Validaciones:
    fun validarCorreo(): Boolean {
        val expresionRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return if (correo.trim().matches(expresionRegex)) {
            textoErrorCorreo = null
            true
        } else {
            textoErrorCorreo = "El correo debe tener un formato correcto."
            false
        }
    }

    fun validarContraseña(): Boolean {
        val expresionRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_!?])[A-Za-z\\d_!?]{8,12}$")
        return if (contraseña.trim().matches(expresionRegex)) {
            textoErrorContraseña = null
            true
        } else {
            textoErrorContraseña = "Debe tener 8-12 caracteres, mayúsculas, minúsculas, número y símbolo (_!?)."
            false
        }
    }

    fun validarTodo(): Boolean {
        val correoValido = validarCorreo()
        val contraseñaValida = validarContraseña()

        return correoValido && contraseñaValida
    }
}