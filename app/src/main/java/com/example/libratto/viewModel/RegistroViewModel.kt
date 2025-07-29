package com.example.libratto.viewModel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.libratto.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistroViewModel : ViewModel() {
    //Variables:
    var nombre by mutableStateOf("")
    var textoErrorNombre by mutableStateOf<String?>(null)

    var apellidos by mutableStateOf("")
    var textoErrorApellidos by mutableStateOf<String?>(null)

    var correo by mutableStateOf("")
    var textoErrorCorreo by mutableStateOf<String?>(null)

    var contraseña by mutableStateOf("")
    var textoErrorContraseña by mutableStateOf<String?>(null)

    var confirmacionContraseña by mutableStateOf("")
    var textoErrorConfirmacionContraseña by mutableStateOf<String?>(null)


    //Para guardar datos temporalmente:
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios = _usuarios.asStateFlow()


    //Métodos:
    fun aniadirUsuario(nombre: String, apellidos: String, correo: String, contraseña: String) {
        val nuevoUsuario = Usuario(
            nombre = nombre,
            apellidos = apellidos,
            correo = correo,
            contraseña = contraseña
        )

        _usuarios.value += nuevoUsuario
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

    fun validarApellidos(): Boolean {
        val expresionRegex = Regex("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+(?: [A-ZÁÉÍÓÚÑa-záéíóúñ]+)*$")
        return if(apellidos.trim().matches(expresionRegex)) {
            textoErrorApellidos = null
            true
        } else {
            textoErrorApellidos = "Los apellidos solo pueden contener letras."
            false
        }
    }

    fun validarCorreo(): Boolean {
        val expresionRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return if(correo.trim().matches(expresionRegex)) {
            textoErrorCorreo = null
            true
        } else {
            textoErrorCorreo = "El correo debe tener un formato correcto."
            false
        }
    }

    fun validarContraseña(): Boolean {
        val expresionRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_!?])[A-Za-z\\d_!?]{8,12}$")
        return if(contraseña.matches(expresionRegex)) {
            textoErrorContraseña = null
            true
        } else {
            textoErrorContraseña = "La contraseña debe tener entre 8 a 12 caracteres, alguna mayúscula, minúscula, número y símbolo (_!?)"
            false
        }
    }

    fun validarConfirmacionContraseña(): Boolean {
        return if(contraseña == confirmacionContraseña) {
            textoErrorConfirmacionContraseña = null
            true
        } else {
            textoErrorConfirmacionContraseña = "Las contraseñas no coinciden"
            false
        }
    }

//    fun validarTodo(): Boolean {
//
//    }
}