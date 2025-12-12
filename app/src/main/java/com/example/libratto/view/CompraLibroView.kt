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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.libratto.R
import com.example.libratto.navigation.Rutas
import com.example.libratto.utilities.AlertaPersonalizada
import com.example.libratto.utilities.AlertaPersonalizadaConfirmacion
import com.example.libratto.viewModel.CompraLibroViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CompraLibroView(isbn: String, controladorNavegacion: NavHostController, compraLibroVM: CompraLibroViewModel = viewModel()) {
    var mostrarAlertaCompra by remember { mutableStateOf(false) }

    var mostrarAlertaCalificacion by remember { mutableStateOf(false) }
    var calificacion by remember { mutableIntStateOf(3) }

    LaunchedEffect(isbn) {
        compraLibroVM.cargarLibro(isbn)
    }

    compraLibroVM.mensajeAlerta?.let { mensaje ->
        AlertaPersonalizada(
            texto = mensaje,
            operacionExitosa = compraLibroVM.operacionExitosa == true,
            onDismiss = {
                compraLibroVM.mensajeAlerta = null
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
                text = "Detalles del Libro",
                fontSize = 40.sp,
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

            compraLibroVM.libroSeleccionado?.let { libro ->
                Column {
                    if(libro.isbn.isNotEmpty()) {
                        Text(
                            text = "ISBN: ${libro.isbn}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }

                    if(libro.nombre.isNotEmpty()) {
                        Text(
                            text = "Nombre: ${libro.nombre}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }

                    if(libro.descripcion.isNotEmpty()) {
                        Text(
                            text = "Descripción: ${libro.descripcion}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }

                    if(libro.autor.isNotEmpty()) {
                        Text(
                            text = "Autor: ${libro.autor}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }

                    if(libro.precio.toString().isNotEmpty()) {
                        Text(
                            text = "Precio: ${libro.precio}€",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    mostrarAlertaCompra = true
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
                    text = "COMPRAR",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            }

            if (mostrarAlertaCompra) {
                AlertaPersonalizadaConfirmacion(
                    titulo = "Confirmar Compra",
                    mensaje = "¿Desea comprar el libro?",
                    onConfirmar = {
                        mostrarAlertaCompra = false
                        mostrarAlertaCalificacion = true
                    },
                    onCancelar = { mostrarAlertaCompra = false }
                )
            }

            if (mostrarAlertaCalificacion) {
                AlertaCalificacion(
                    calificacion = calificacion,
                    onCambioCalificacion = { calificacion = it },
                    onConfirmar = {
                        val libro = compraLibroVM.libroSeleccionado
                        val correo = FirebaseAuth.getInstance().currentUser?.email

                        if (libro != null && correo != null) {
                            compraLibroVM.guardarCompra(
                                isbn = libro.isbn,
                                precio = libro.precio,
                                correoUsuario = correo,
                                calificacion = calificacion,
                            )
                        }

                        mostrarAlertaCalificacion = false
                    }
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


//Esta va aquí porque no se reutiliza
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertaCalificacion(
    calificacion: Int,
    onCambioCalificacion: (Int) -> Unit,
    onConfirmar: () -> Unit,
) {
    BasicAlertDialog(onDismissRequest = {}) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Calificación Compra",
                    tint = Color(252, 203, 78),
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Califique su compra",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                var expanded by remember { mutableStateOf(false) }

                Box {
                    OutlinedTextField(
                        value = calificacion.toString(),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Calificación") },
                        trailingIcon = {
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        (1..5).forEach { number ->
                            DropdownMenuItem(
                                text = { Text(number.toString()) },
                                onClick = {
                                    onCambioCalificacion(number)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onConfirmar() },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black
                    ),
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    Text("ACEPTAR")
                }
            }
        }
    }
}