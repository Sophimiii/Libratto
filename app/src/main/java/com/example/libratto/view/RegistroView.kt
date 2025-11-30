package com.example.libratto.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.libratto.R
import com.example.libratto.ui.theme.ColoresTextfield
import com.example.libratto.viewModel.RegistroViewModel
import com.example.libratto.utilities.CampoFormulario

@Composable
fun MostrarPantallaRegistro(registroVM: RegistroViewModel) {
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
                text = "Registrar Nuevo Usuario",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(30.dp))

            CampoFormulario(
                valor = registroVM.nombre,
                cambioValor = { registroVM.nombre = it },
                validar = { registroVM.validarNombre() },
                textoError = registroVM.textoErrorNombre,
                labelTexto = "Nombre"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = registroVM.apellidos,
                cambioValor = { registroVM.apellidos = it },
                validar = { registroVM.validarApellidos() },
                textoError = registroVM.textoErrorApellidos,
                labelTexto = "Apellidos"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = registroVM.nombreUsuario,
                cambioValor = { registroVM.nombreUsuario = it },
                validar = { registroVM.validarNombreUsuario{} },
                textoError = registroVM.textoErrorNombreUsuario,
                labelTexto = "Nombre de Usuario"
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Autor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    ),
                //isError = registroVM.textoErrorContraseña != null,
                shape = RoundedCornerShape(12.dp),
                colors = ColoresTextfield(),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    ),
                //isError = registroVM.textoErrorConfirmacionContraseña != null,
                shape = RoundedCornerShape(12.dp),
                colors = ColoresTextfield(),
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
            onClick = { },
            modifier = Modifier
                .padding(20.dp)
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