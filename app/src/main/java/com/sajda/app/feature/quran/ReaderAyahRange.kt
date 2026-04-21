package com.sajda.app.feature.quran

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ReaderAyahRange(
    ayahCount: Int
) {
    Text(
        text = "Ayet 1-$ayahCount",
        color = Color(0xFF8F8F8F),
        style = MaterialTheme.typography.bodyMedium
    )
}
