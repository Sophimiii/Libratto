package com.example.libratto.model

data class Libro(
    val isbn: String = "",
    val nombre: String = "",
    val descripcion: String = "",
    val autor: String = "",
    val precio: Double = 0.0,
    val imagen: String = ""
)