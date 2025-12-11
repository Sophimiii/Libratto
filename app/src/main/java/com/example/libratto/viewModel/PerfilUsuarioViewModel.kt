package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class PerfilUsuarioViewModel : ViewModel() {
    //Variables:
    var nombre by mutableStateOf("")
    var textoErrorNombre by mutableStateOf<String?>(null)

    var apellidos by mutableStateOf("")
    var textoErrorApellidos by mutableStateOf<String?>(null)

    var nombreUsuario by mutableStateOf("")
    var textoErrorNombreUsuario by mutableStateOf<String?>(null)


    // Para controlar si se está editando cada campo
    var editandoNombre by mutableStateOf(false)
    var editandoApellidos by mutableStateOf(false)
    var editandoNombreUsuario by mutableStateOf(false)


    //Para guardar datos en la base de datos:
    private val baseDatos = Firebase.firestore


    //Para gestionar el mensaje a la hora de registrar un usuario:
    var mensajeAlerta by mutableStateOf<String?>(null)
    var operacionExitosa by mutableStateOf<Boolean?>(null)


    //Métodos:
    fun cargarDatosUsuario() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        baseDatos.collection("usuarios")
            .document(uid)
            .get()
            .addOnSuccessListener { documento ->
                if(documento.exists()) {
                    nombre = documento.getString("nombre") ?: ""
                    apellidos = documento.getString("apellidos") ?: ""
                    nombreUsuario = documento.getString("nombreUsuario") ?: ""
                }
            }
    }

    fun guardarCambios(campo: String, valor: String) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: run {
            mensajeAlerta = "Usuario no logueado."
            operacionExitosa = false
            return
        }

        val datosActualizar = hashMapOf<String, Any>(campo to valor)

        baseDatos.collection("usuarios")
            .document(uid)
            .update(datosActualizar)
            .addOnSuccessListener {
                operacionExitosa = true
                mensajeAlerta = "Campo actualizado correctamente."
            }
            .addOnFailureListener {
                operacionExitosa = false
                mensajeAlerta = "Error al actualizar: ${it.message}"
            }
    }

    fun cerrarSesion(onCerrar: () -> Unit) {
        FirebaseAuth.getInstance().signOut()
        onCerrar()
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


    fun validarNombreUsuario(): Boolean {
        val expresionRegex = Regex("^[A-Za-z0-9_]+$")
        return if (nombreUsuario.trim().matches(expresionRegex)) {
            textoErrorNombreUsuario = null
            true
        } else {
            textoErrorNombreUsuario = "Solo se permiten letras, números y guiones bajos."
            false
        }
    }
}