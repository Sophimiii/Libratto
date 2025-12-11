package com.example.libratto.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.libratto.R
import com.example.libratto.model.Libro
import com.example.libratto.navigation.Rutas
import com.example.libratto.viewModel.PrincipalViewModel

@Composable
fun PrincipalView(principalVM: PrincipalViewModel, controladorNavegacion: NavHostController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                //Función no disponible, propuesta de ampliación
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { controladorNavegacion.navigate(Rutas.PublicarLibroView.ruta) },
                    icon = { Icon(Icons.Default.Add, contentDescription = "Publicar Libro") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { controladorNavegacion.navigate(Rutas.PerfilUsuarioView.ruta) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Ver/Editar Perfil") }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(id = R.drawable.fondo_pantalla_libratto),
                contentDescription = "Imagen Fondo Pantalla App",
                contentScale = ContentScale.FillHeight
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0, 0, 0, 160))
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(principalVM.listaLibros) { libro ->
                        ItemLibroCard(libro = libro)
                    }
                }
            }
        }
    }
}


@Composable
fun ItemLibroCard(libro: Libro) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.libro_predeterminado),
                contentDescription = "Imagen Libro",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = libro.nombre,
                fontWeight = FontWeight.Bold,
            )

//            Text(
//                text = libro.precio
//            )
        }
    }
}