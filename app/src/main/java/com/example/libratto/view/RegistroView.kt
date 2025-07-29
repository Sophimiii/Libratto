package com.example.libratto.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.libratto.viewModel.RegistroViewModel

@Composable
fun MostrarPantallaRegistro(registroVM: RegistroViewModel) {
    Box() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("test")

            TextField(
                value = registroVM.nombre,
                onValueChange = {
                    registroVM.nombre = it
                    registroVM.validarNombre()
                },
                label = { Text("Nombre") },
                isError = registroVM.textoErrorNombre != null
            )

            if(registroVM.textoErrorNombre != null) {
                Text(
                    text = registroVM.textoErrorNombre ?: "",
                    color = Color.Red
                )
            }


            TextField(
                value = registroVM.apellidos,
                onValueChange = {
                    registroVM.apellidos = it
                    registroVM.validarApellidos()
                },
                label = { Text("Apellidos") },
                isError = registroVM.textoErrorApellidos != null
            )

            if(registroVM.textoErrorApellidos != null) {
                Text(
                    text = registroVM.textoErrorApellidos ?: "",
                    color = Color.Red
                )
            }


            TextField(
                value = registroVM.correo,
                onValueChange = {
                    registroVM.correo = it
                    registroVM.validarCorreo()
                },
                label = { Text("Correo Electrónico") },
                isError = registroVM.textoErrorCorreo != null
            )

            if(registroVM.textoErrorCorreo != null) {
                Text(
                    text = registroVM.textoErrorCorreo ?: "",
                    color = Color.Red
                )
            }


            TextField(
                value = registroVM.contraseña,
                onValueChange = {
                    registroVM.contraseña = it
                    registroVM.validarContraseña()
                },
                label = { Text("Contraseña") },
                isError = registroVM.textoErrorContraseña != null
            )

            if(registroVM.textoErrorContraseña != null) {
                Text(
                    text = registroVM.textoErrorContraseña ?: "",
                    color = Color.Red
                )
            }


            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Confirmación de Contraseña") },
            )

            Button(
                onClick = { registroVM.aniadirUsuario(registroVM.nombre, registroVM.apellidos, registroVM.correo, registroVM.contraseña) }
            ) {
                Text("Añadir nuevo usuario")
            }
        }

        val listaUsuarios by registroVM.usuarios.collectAsState()

        LazyColumn {
            items(listaUsuarios) { usuario ->
                Text(usuario.toString())
            }
        }
    }
}