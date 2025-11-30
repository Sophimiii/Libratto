package com.example.libratto.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libratto.model.Libro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class PrincipalViewModel : ViewModel() {

    // Texto de la barra de búsqueda
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    // Lista original (fake por ahora, luego Firebase)
    private val _libros = MutableStateFlow<List<Libro>>(emptyList())
    val libros: StateFlow<List<Libro>> = _libros

    // Resultado filtrado según búsqueda
    val librosFiltrados: StateFlow<List<Libro>> =
        combine(_searchText, _libros) { search, lista ->
            if (search.isBlank()) lista
            else lista.filter { it.nombre.contains(search, ignoreCase = true) }
        } as StateFlow<List<Libro>>

    init {
        cargarLibrosFake()
    }

    private fun cargarLibrosFake() {
        viewModelScope.launch {
            val fake = List(20) { index ->
                Libro(
                    nombre = "Libro $index",
                    precio = 10.99,
                    isbn = TODO(),
                    descripcion = TODO(),
                    autor = TODO(),
                    //imagen = "https://picsum.photos/300?random=$index"
                )
            }
            _libros.value = fake
        }
    }

    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
    }
}