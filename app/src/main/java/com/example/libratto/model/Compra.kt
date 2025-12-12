package com.example.libratto.model

import com.google.firebase.Timestamp

data class Compra (
    val isbn: String = "",
    val precio: Double = 0.0,
    val correoUsuario: String = "",
    val fecha: Timestamp? = null,
    val calificacion: Int = 0
)