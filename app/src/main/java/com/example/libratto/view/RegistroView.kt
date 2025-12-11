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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.libratto.viewModel.RegistroViewModel
import com.example.libratto.utilities.CampoFormulario

@Composable
fun RegistroView(registroVM: RegistroViewModel, controladorNavegacion: NavHostController) {
    registroVM.mensajeAlerta?.let { mensaje ->
        AlertaPersonalizada(
            texto = mensaje,
            operacionExitosa = registroVM.operacionExitosa == true,
            onDismiss = {
                registroVM.mensajeAlerta = null
            }
        )
    }

    LaunchedEffect(registroVM.operacionExitosa) {
        if(registroVM.operacionExitosa == true) {
            controladorNavegacion.navigate(Rutas.PrincipalView.ruta) {
                popUpTo(Rutas.RegistroView.ruta) { inclusive = true }
            }
        }
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

            CampoFormulario(
                valor = registroVM.correo,
                cambioValor = { registroVM.correo = it },
                validar = { registroVM.validarCorreo() },
                textoError = registroVM.textoErrorCorreo,
                labelTexto = "Correo Electrónico"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = registroVM.contraseña,
                cambioValor = { registroVM.contraseña = it },
                validar = { registroVM.validarContraseña() },
                textoError = registroVM.textoErrorContraseña,
                labelTexto = "Contraseña"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CampoFormulario(
                valor = registroVM.confirmacionContraseña,
                cambioValor = { registroVM.confirmacionContraseña = it },
                validar = { registroVM.validarConfirmacionContraseña() },
                textoError = registroVM.textoErrorConfirmacionContraseña,
                labelTexto = "Confirmación Contraseña"
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    registroVM.añadirUsuario(
                        registroVM.nombre,
                        registroVM.apellidos,
                        registroVM.nombreUsuario,
                        registroVM.correo,
                        registroVM.contraseña
                    )
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
            onClick = { controladorNavegacion.navigate(Rutas.IniciarSesionView.ruta) },
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