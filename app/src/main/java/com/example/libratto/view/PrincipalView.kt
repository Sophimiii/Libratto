package com.example.libratto.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.libratto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarPantallaPrincipal() {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Buscar")
                        },
                        placeholder = { Text("Buscar libro...") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        },
        bottomBar = {
//            NavigationBar {
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { /* Perfil */ },
//                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { /* Añadir libro */ },
//                    icon = { Icon(Icons.Default.Add, contentDescription = "Añadir libro") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { /* Carrito */ },
//                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") }
//                )
//            }
        }
    ) { padding ->

        // Datos falsos para vista (sin lógica)
        val librosFake = List(20) { index ->
            LibroFake(
                nombre = "Libro $index",
                precio = "$${10 + index}",
                imagen = "https://picsum.photos/300?random=$index"
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(librosFake) { libro ->
                ItemLibroCuadro(libro)
            }
        }
    }
}

data class LibroFake(
    val nombre: String,
    val precio: String,
    val imagen: String
)

@Composable
fun ItemLibroCuadro(libro: LibroFake) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(libro.imagen.toInt()),
                contentDescription = libro.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = libro.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = libro.precio,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaPrincipal() {
    MostrarPantallaPrincipal()
}