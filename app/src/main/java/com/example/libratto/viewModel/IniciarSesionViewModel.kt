package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

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
        if(validarTodo()) {
            viewModelScope.launch {
                autentificacion
                    .signInWithEmailAndPassword(correo.trim(), contraseña.trim())
                    .addOnCompleteListener { tarea ->
                        if (tarea.isSuccessful) {
                            operacionExitosa = true
                            mensajeAlerta = null
                        } else {
                            operacionExitosa = false
                            mensajeAlerta = manejoErroresSesion(tarea.exception)
                        }
                    }
            }
        } else {
            operacionExitosa = false
            return
        }
    }

    private fun manejoErroresSesion(e: Exception?): String {
        return when (e) {
            is FirebaseAuthInvalidUserException ->
                "No existe una cuenta con este correo."

            is FirebaseAuthInvalidCredentialsException ->
                "El correo o la contraseña son incorrectos. Inténtelo de nuevo."

            is FirebaseAuthException ->
                "Error de autenticación: ${e.message}"

            else -> "Ha ocurrido un error inesperado"
        }
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
        val expresionRegex =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_!?])[A-Za-z\\d_!?]{8,12}$")
        return if (contraseña.trim().matches(expresionRegex)) {
            textoErrorContraseña = null
            true
        } else {
            textoErrorContraseña =
                "Debe tener 8-12 caracteres, mayúsculas, minúsculas, número y símbolo (_!?)."
            false
        }
    }

    fun validarTodo(): Boolean {
        val correoValido = validarCorreo()
        val contraseñaValida = validarContraseña()

        return correoValido && contraseñaValida
    }
}