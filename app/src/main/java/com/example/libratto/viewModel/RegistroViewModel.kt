package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.libratto.model.Usuario
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistroViewModel : ViewModel() {
    //Variables:
    var nombre by mutableStateOf("")
    var textoErrorNombre by mutableStateOf<String?>(null)

    var apellidos by mutableStateOf("")
    var textoErrorApellidos by mutableStateOf<String?>(null)

    var nombreUsuario by mutableStateOf("")
    var textoErrorNombreUsuario by mutableStateOf<String?>(null)

    var correo by mutableStateOf("")
    var textoErrorCorreo by mutableStateOf<String?>(null)

    var contraseña by mutableStateOf("")
    var textoErrorContraseña by mutableStateOf<String?>(null)

    var confirmacionContraseña by mutableStateOf("")
    var textoErrorConfirmacionContraseña by mutableStateOf<String?>(null)


    //Para guardar datos en la base de datos:
    val autenticacion = FirebaseAuth.getInstance()
    val baseDatos = Firebase.firestore


    //Variable para mostrar alerta


    //Métodos:
    fun añadirUsuario(nombre: String, apellidos: String, nombreUsuario: String, correo: String, contraseña: String) {
        autenticacion.createUserWithEmailAndPassword(correo, contraseña)


        val nuevoUsuario = Usuario(nombre, apellidos, nombreUsuario, correo, contraseña)

        baseDatos.collection("usuarios")
            .add(nuevoUsuario)
            .addOnSuccessListener {
                println("Usuario registrado con éxito")
            }
            .addOnFailureListener {
                println("Ha ocurrido un error")
            }

    }


    //Validaciones
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

    fun validarNombreUsuario(enResultado: (Boolean) -> Unit) {
        baseDatos.collection("usuarios")
            .whereEqualTo("nombreUsuario", nombreUsuario.trim())
            .get()
            .addOnSuccessListener { documentos ->
                if(documentos.isEmpty) {
                    enResultado(true)
                } else {
                    enResultado(false)
                }
            }
    }

    fun validarCorreo(): Boolean {
        val expresionRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
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
        return if(contraseña.trim().matches(expresionRegex)) {
            textoErrorContraseña = null
            true
        } else {
            textoErrorContraseña = "La contraseña debe tener entre 8 a 12 caracteres, alguna mayúscula, minúscula, número y símbolo (_!?)"
            false
        }
    }

    fun validarConfirmacionContraseña(): Boolean {
        return if(confirmacionContraseña.isNotBlank() && contraseña == confirmacionContraseña) {
            textoErrorConfirmacionContraseña = null
            true
        } else {
            textoErrorConfirmacionContraseña = "Las contraseñas no coinciden"
            false
        }
    }

    fun validarTodo(): Boolean {
        val nombreValido = validarNombre()
        val apellidosValidos = validarApellidos()
        val correoValido = validarCorreo()
        val contraseñaValida = validarContraseña()
        val confirmacionContraseñaValida = validarConfirmacionContraseña()

        return nombreValido && apellidosValidos && correoValido && contraseñaValida && confirmacionContraseñaValida
    }
}