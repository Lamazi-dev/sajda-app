package com.sajda.app.feature.qibla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.rotate
@Composable
fun QiblaCompass(
    rotation: Float
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.rotate(rotation)
    ) {
        Spacer(
            modifier = Modifier
                .size(260.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8E8E8))
        )

        Text(
            text = "↑",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF2E7D32),
            modifier = Modifier.padding(bottom = 120.dp)
        )

        Spacer(
            modifier = Modifier
                .size(18.dp)
                .clip(CircleShape)
                .background(Color(0xFF2E7D32))
        )
    }
}
