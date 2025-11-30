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
    private val autenticacion: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val baseDatos = Firebase.firestore


    //Para gestionar el mensaje a la hora de registrar un usuario:
    var mensajeAlerta by mutableStateOf<String?>(null)
    var operacionExitosa by mutableStateOf<Boolean?>(null)


    //Métodos:
    fun añadirUsuario(nombre: String, apellidos: String, nombreUsuario: String, correo: String, contraseña: String) {
        autenticacion.createUserWithEmailAndPassword(correo, contraseña)
            .addOnSuccessListener { resultado ->
                val uid = resultado.user?.uid ?: return@addOnSuccessListener
                val nuevoUsuario = Usuario(nombre, apellidos, nombreUsuario, correo, contraseña)

                baseDatos.collection("usuarios")
                    .document(uid)
                    .set(nuevoUsuario)
                    .addOnSuccessListener {
                        operacionExitosa = true
                        mensajeAlerta = "Usuario registrado con éxito."
                    }
                    .addOnFailureListener {
                        operacionExitosa = false
                        mensajeAlerta = "Error al registrar el usuario: ${it.message}."
                    }
            }
            .addOnFailureListener {
                operacionExitosa = false
                mensajeAlerta = "Error al crear el usuario: ${it.message}."
            }
    }


    //Validaciones
    fun validarNombre(): Boolean {
        val expresionRegex = Regex("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+(?: [A-ZÁÉÍÓÚÑa-záéíóúñ]+)*$")
        return if (nombre.trim().matches(expresionRegex)) {
            textoErrorNombre = null
            true
        } else {
            textoErrorNombre = "El nombre solo puede contener letras y espacios."
            false
        }
    }


    fun validarApellidos(): Boolean {
        val expresionRegex = Regex("^[A-ZÁÉÍÓÚÑa-záéíóúñ]+(?: [A-ZÁÉÍÓÚÑa-záéíóúñ]+)*$")
        return if (apellidos.trim().matches(expresionRegex)) {
            textoErrorApellidos = null
            true
        } else {
            textoErrorApellidos = "Los apellidos solo pueden contener letras y espacios."
            false
        }
    }


    fun validarNombreUsuario(enResultado: (Boolean) -> Unit) {
        val expresionRegex = Regex("^[A-Za-z0-9_]+$")
        val usuario = nombreUsuario.trim()

        if (!usuario.matches(expresionRegex)) {
            textoErrorNombreUsuario = "Solo se permiten letras, números y guiones bajos."
            enResultado(false)
            return
        }

        baseDatos.collection("usuarios")
            .whereEqualTo("nombreUsuario", usuario)
            .get()
            .addOnSuccessListener { documentos ->
                if (documentos.isEmpty) {
                    textoErrorNombreUsuario = null
                    enResultado(true)
                } else {
                    textoErrorNombreUsuario = "Este nombre de usuario ya está en uso."
                    enResultado(false)
                }
            }
            .addOnFailureListener {
                textoErrorNombreUsuario = "Error al verificar el nombre de usuario."
                enResultado(false)
            }
    }


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


    fun validarConfirmacionContraseña(): Boolean {
        return if (confirmacionContraseña.isNotBlank() && contraseña == confirmacionContraseña) {
            textoErrorConfirmacionContraseña = null
            true
        } else {
            textoErrorConfirmacionContraseña = "Las contraseñas no coinciden."
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

//FUNCIONAAAAA

/*
Cosas a arreglar:

3.- CONFIGURATION NOT FOUND de Firebase
4.- Botón de registrarse (que tenga el formato de los demás)
5.- Verificar chatgpt para el error otra vez
6.- Añadir botón de volver en la cinta de arriba con un Scaffold
 */