package com.example.libratto.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.libratto.ui.theme.ColoresTextfield

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertaPersonalizada(texto: String, operacionExitosa: Boolean, onDismiss: () -> Unit) {
    BasicAlertDialog(
        onDismissRequest = { onDismiss() }
    ) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

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

                Button(onClick = { onDismiss() }) {
                    Text("Aceptar")
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