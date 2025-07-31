package com.navrasaapp.navrasa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.navrasaapp.navrasa.presentation.HomeScreen
import com.navrasaapp.navrasa.ui.theme.NavrasaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavrasaTheme {
                HomeScreen()
            }
        }
    }
}
