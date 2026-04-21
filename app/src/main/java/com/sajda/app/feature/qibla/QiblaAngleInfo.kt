package com.sajda.app.feature.qibla

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun QiblaAngleInfo(
    qiblaAngle: Int
) {
    Text(
        text = "Kıble yönü • ${qiblaAngle}°",
        style = MaterialTheme.typography.titleMedium,
        color = Color(0xFF2C2C2C)
    )
}