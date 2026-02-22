package com.google.uala_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.uala_challenge.presenter.screen.CitiesScreen
import com.google.uala_challenge.ui.theme.Uala_challengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Uala_challengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CitiesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
