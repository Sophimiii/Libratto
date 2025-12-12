package com.example.libratto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.libratto.navigation.NavegacionCompleta
import com.example.libratto.ui.theme.LibrattoTheme
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
                NavegacionCompleta()
            }
        }
    }
}