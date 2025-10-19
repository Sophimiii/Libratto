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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.libratto.R
import com.example.libratto.ui.theme.ColoresTextfield
import com.example.libratto.viewModel.PublicarLibroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarPantallaPublicarLibro() {
    var mostrarDialogo by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },

                title = {
                    Text("Pantalla principal")
                }
            )

        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                    .background(Color(0, 0, 0, 150))
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Publicar Nuevo Libro",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("ISBN") },
                    //isError = registroVM.textoErrorNombre != null,
                    shape = RoundedCornerShape(12.dp),
                    colors = ColoresTextfield(),
                    modifier = Modifier.fillMaxWidth()
                )

//            if (registroVM.textoErrorNombre != null) {
//                Text(
//                    text = registroVM.textoErrorNombre ?: "",
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Red   //(99, 252, 224)
//                )
//            }

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Nombre") },
                    //isError = registroVM.textoErrorApellidos != null,
                    shape = RoundedCornerShape(12.dp),
                    colors = ColoresTextfield(),
                    modifier = Modifier.fillMaxWidth()
                )

//            if (registroVM.textoErrorApellidos != null) {
//                Text(
//                    text = registroVM.textoErrorApellidos ?: "",
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Red
//                )
//            }

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Descripción") },
                    //isError = registroVM.textoErrorCorreo != null,
                    shape = RoundedCornerShape(12.dp),
                    colors = ColoresTextfield(),
                    modifier = Modifier.fillMaxWidth()
                )

//            if (registroVM.textoErrorCorreo != null) {
//                Text(
//                    text = registroVM.textoErrorCorreo ?: "",
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Red
//                )
//            }

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Autor") },
                    //isError = registroVM.textoErrorContraseña != null,
                    shape = RoundedCornerShape(12.dp),
                    colors = ColoresTextfield(),
                    modifier = Modifier.fillMaxWidth()
                )

//            if (registroVM.textoErrorContraseña != null) {
//                Text(
//                    text = registroVM.textoErrorContraseña ?: "",
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Red
//                )
//            }

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Precio") },
                    //isError = registroVM.textoErrorConfirmacionContraseña != null,
                    shape = RoundedCornerShape(12.dp),
                    colors = ColoresTextfield(),
                    modifier = Modifier.fillMaxWidth()
                )

//            if (registroVM.textoErrorConfirmacionContraseña != null) {
//                Text(
//                    text = registroVM.textoErrorConfirmacionContraseña ?: "",
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Red
//                )
//            }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
//                    if (registroVM.validarTodo()) {
//                        registroVM.añadirUsuario(
//                            registroVM.nombre,
//                            registroVM.apellidos,
//                            registroVM.nombreUsuario,
//                            registroVM.correo,
//                            registroVM.contraseña
//                        )
//                    }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Black),
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    Text(
                        text = "PUBLICAR",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp
                    )
                } //Añadir mensaje de error si no es válido todo
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaPublicarLibro() {
    MostrarPantallaPublicarLibro()
}