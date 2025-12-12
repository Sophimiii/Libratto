package com.example.libratto.utilities

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.libratto.ui.theme.ColoresTextfield

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertaPersonalizada(
    texto: String,
    operacionExitosa: Boolean,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = { onDismiss() }) {
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
                    imageVector = if (operacionExitosa) Icons.Filled.CheckCircle else Icons.Filled.Error,
                    contentDescription = if (operacionExitosa) "¡Operación Exitosa!" else "¡Ha Ocurrido Un Error!",
                    tint = if (operacionExitosa) Color(0xFF4CAF50) else Color.Red,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = texto,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onDismiss() },
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertaPersonalizadaConfirmacion(
    titulo: String,
    mensaje: String,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = onCancelar) {
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
                    imageVector = Icons.Filled.QuestionMark,
                    contentDescription = "Confirmación",
                    tint = Color.Blue,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = mensaje,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
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

                    Button(
                        onClick = { onCancelar() },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Black
                        ),
                        border = BorderStroke(2.dp, Color.White)
                    ) {
                        Text("CANCELAR")
                    }
                }
            }
        }
    }
}


@Composable
fun CampoFormulario(
    valor: String,
    cambioValor: (String) -> Unit,
    validar: () -> Unit,
    textoError: String? = null,
    labelTexto: String,
    activado: Boolean = true
) {
    TextField(
        value = valor,
        onValueChange = {
            cambioValor(it)
            validar()
        },
        label = {
            Text(
                text = labelTexto,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        isError = textoError != null,
        shape = RoundedCornerShape(12.dp),
        colors = ColoresTextfield(),
        enabled = activado
    )

    if (textoError != null) {
        Text(
            text = textoError ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(255, 174, 201)
        )
    }
}


@Composable
fun CampoFormularioContraseña(
    valor: String,
    cambioValor: (String) -> Unit,
    validar: () -> Unit,
    textoError: String? = null,
    labelTexto: String,
    activado: Boolean = true
) {
    var mostrarContraseña by remember { mutableStateOf(false) }

    TextField(
        value = valor,
        onValueChange = {
            cambioValor(it)
            validar()
        },
        label = {
            Text(
                text = labelTexto,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        isError = textoError != null,
        visualTransformation = if (mostrarContraseña) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icono = if (mostrarContraseña) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { mostrarContraseña = !mostrarContraseña }) {
                Icon(
                    imageVector = icono,
                    contentDescription = if (mostrarContraseña) "Ocultar contraseña" else "Mostrar contraseña"
                )
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = ColoresTextfield(),
        enabled = activado
    )

    if (textoError != null) {
        Text(
            text = textoError,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(255, 174, 201)
        )
    }
}


@Composable
fun CampoFormularioEdicion(
    valor: String,
    cambioValor: (String) -> Unit,
    validar: () -> Unit,
    textoError: String? = null,
    labelTexto: String,
    editando: Boolean,
    onClickIcono: () -> Unit
) {
    TextField(
        value = valor,
        onValueChange = {
            if (editando) {
                cambioValor(it)
                validar()
            }
        },
        label = {
            Text(
                text = labelTexto,
                fontWeight = FontWeight.Bold
            )
        },
        enabled = editando,
        trailingIcon = {
            IconButton(onClick = onClickIcono) {
                Icon(
                    imageVector = if (editando) Icons.Default.CheckBox else Icons.Default.Edit,
                    contentDescription = "Editar Campo"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        isError = textoError != null,
        shape = RoundedCornerShape(12.dp),
        colors = ColoresTextfield(),
    )

    if (textoError != null) {
        Text(
            text = textoError ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(255, 174, 201)
        )
    }
}