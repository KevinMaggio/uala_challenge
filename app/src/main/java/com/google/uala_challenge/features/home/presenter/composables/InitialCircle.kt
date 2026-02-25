package com.google.uala_challenge.features.home.presenter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.uala_challenge.ui.theme.SoftBlue

@Composable
fun InitialCircle(
    letter: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SoftBlue,
    textColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.take(1).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}
