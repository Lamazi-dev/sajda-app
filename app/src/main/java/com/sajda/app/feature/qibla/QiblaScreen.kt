package com.sajda.app.feature.qibla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QiblaScreen() {
    val qiblaAngle = mockQiblaAngle
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pusula",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1C1C1C)
        )

        Spacer(modifier = Modifier.height(32.dp))

        QiblaCompass(
            rotation = -qiblaAngle.toFloat()
        )

        Spacer(modifier = Modifier.height(24.dp))

        QiblaAngleInfo(qiblaAngle = qiblaAngle)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Pusula yönü örnek veri ile gösteriliyor.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF666666)
        )
    }
}