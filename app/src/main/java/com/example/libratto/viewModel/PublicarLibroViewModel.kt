package com.example.libratto.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PublicarLibroViewModel : ViewModel() {
    //Variables para las operaciones
    val isbn by mutableStateOf("")
    var textoErrorISBN by mutableStateOf<String?>(null)



}