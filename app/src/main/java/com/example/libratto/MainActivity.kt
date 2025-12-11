package com.example.libratto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libratto.navigation.NavegacionCompleto
import com.example.libratto.ui.theme.LibrattoTheme
import com.example.libratto.view.PerfilUsuarioView
import com.example.libratto.view.PrincipalView
import com.example.libratto.view.PublicarLibroView
import com.example.libratto.viewModel.IniciarSesionViewModel
import com.example.libratto.viewModel.PerfilUsuarioViewModel
import com.example.libratto.viewModel.PublicarLibroViewModel
import com.example.libratto.viewModel.RegistroViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }

        enableEdgeToEdge()
        setContent {
            LibrattoTheme {
//                val registroVM : RegistroViewModel by viewModels()
//                MostrarPantallaRegistro(registroVM)

//                val inicioVM : IniciarSesionViewModel by viewModels()
//                MostrarPantallaIniciarSesion(inicioVM)

                NavegacionCompleto()

//                val publicarLibroVM : PublicarLibroViewModel by viewModels()
//                PublicarLibroView(publicarLibroVM)

//                val perfilUsuarioVM : PerfilUsuarioViewModel by viewModels()
//                PerfilUsuarioView(perfilUsuarioVM)
            }
        }
    }
}