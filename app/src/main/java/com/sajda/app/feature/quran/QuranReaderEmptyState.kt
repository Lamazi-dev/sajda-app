package com.sajda.app.feature.quran

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajda.app.R

@Composable
fun QuranReaderEmptyState() {
    Text(
        text = stringResource(R.string.reader_content_coming_soon),
        color = Color.White,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = stringResource(R.string.reader_content_next_step),
        color = Color(0xFFE0E0E0),
        style = MaterialTheme.typography.bodyLarge,
        lineHeight = 28.sp
    )
}