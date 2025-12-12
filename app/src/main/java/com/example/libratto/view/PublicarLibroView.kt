package com.example.libratto.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.libratto.R
import com.example.libratto.navigation.Rutas
import com.example.libratto.utilities.AlertaPersonalizada
import com.example.libratto.utilities.CampoFormulario
import com.example.libratto.viewModel.PublicarLibroViewModel

@Composable
fun PublicarLibroView(publicarLibroVM: PublicarLibroViewModel, controladorNavegacion: NavHostController) {
    publicarLibroVM.mensajeAlerta?.let { mensaje ->
        AlertaPersonalizada(
            texto = mensaje,
            operacionExitosa = publicarLibroVM.operacionExitosa == true,
            onDismiss = {
                publicarLibroVM.mensajeAlerta = null
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
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
            Text(
                text = "Publicar Nuevo Libro",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.libro_predeterminado),
                contentDescription = "Imagen del libro",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(30.dp))

            CampoFormulario(
                valor = publicarLibroVM.isbn,
                cambioValor = { publicarLibroVM.isbn = it },
                validar = { publicarLibroVM.validarISBN() },
                textoError = publicarLibroVM.textoErrorISBN,
                labelTexto = "ISBN"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = publicarLibroVM.nombre,
                cambioValor = { publicarLibroVM.nombre = it },
                validar = { publicarLibroVM.validarNombre() },
                textoError = publicarLibroVM.textoErrorNombre,
                labelTexto = "Nombre"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = publicarLibroVM.descripcion,
                cambioValor = { publicarLibroVM.descripcion = it },
                validar = { },
                textoError = null,
                labelTexto = "Descripción"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = publicarLibroVM.autor,
                cambioValor = { publicarLibroVM.autor = it },
                validar = { publicarLibroVM.validarAutor() },
                textoError = publicarLibroVM.textoErrorAutor,
                labelTexto = "Autor"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = publicarLibroVM.precio,
                cambioValor = { publicarLibroVM.precio = it },
                validar = { publicarLibroVM.validarPrecio() },
                textoError = publicarLibroVM.textoErrorPrecio,
                labelTexto = "Precio"
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    publicarLibroVM.añadirLibro(
                        publicarLibroVM.isbn,
                        publicarLibroVM.nombre,
                        publicarLibroVM.descripcion,
                        publicarLibroVM.autor,
                        publicarLibroVM.precio.toDouble()
                    )
                },
                modifier = Modifier.width(200.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                border = BorderStroke(2.dp, Color.White)
            ) {
                Text(
                    text = "PUBLICAR",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }
        }

        IconButton(
            onClick = { controladorNavegacion.navigate(Rutas.PrincipalView.ruta) },
            modifier = Modifier
                .padding(28.dp)
                .padding(top = 60.dp)
                .align(Alignment.TopStart),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
            )
        }
    }
}