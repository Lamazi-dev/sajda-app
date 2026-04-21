package com.sajda.app.feature.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
@Composable
fun AyahItem(
    ayah: Ayah,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Text(
        text = "Ayet ${ayah.number}",
        color = Color(0xFF8A8A8A),
        style = MaterialTheme.typography.bodySmall
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = ayah.arabicText,
        color = if (isSelected) Color(0xFF4CAF50) else Color.White,
        fontSize = 32.sp,
        lineHeight = 48.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        textAlign = TextAlign.End
    )

    Spacer(modifier = Modifier.height(18.dp))

    Text(
        text = ayah.translation,
        color = Color(0xFFDADADA),
        style = MaterialTheme.typography.bodyLarge,
        lineHeight = 30.sp
    )

    Spacer(modifier = Modifier.height(20.dp))

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFF1E1E1E))
    )

    Spacer(modifier = Modifier.height(20.dp))
}