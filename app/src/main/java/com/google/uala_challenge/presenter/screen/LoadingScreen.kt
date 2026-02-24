package com.google.uala_challenge.presenter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.uala_challenge.ui.theme.TransparentBlack

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TransparentBlack),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}