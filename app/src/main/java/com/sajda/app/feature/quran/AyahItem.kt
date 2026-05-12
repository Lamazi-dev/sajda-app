package com.sajda.app.feature.quran
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import com.sajda.app.R
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.ui.draw.scale
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
@Composable
fun AyahItem(
    ayah: Ayah,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    val favoriteScale by animateFloatAsState(
        targetValue = if (ayah.isFavorite) 1.25f else 1f,
        animationSpec = spring(),
        label = "favoriteScale"
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.ayah_number, ayah.number),
            color = Color(0xFF8A8A8A),
            style = MaterialTheme.typography.bodySmall
        )

        if (isSelected) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "↗",
                    color = Color(0xFF8A8A8A),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .alpha(0.7f)
                        .padding(vertical = 6.dp)
                        .clickable(
                            onClick = onShareClick
                        )
                )

                Text(
                    text = if (ayah.isFavorite) "♥" else "♡",
                    color = if (ayah.isFavorite) Color(0xFF7DD3A7) else Color(0xFF8A8A8A),
                    fontSize = 22.sp,
                    modifier = Modifier
                        .scale(favoriteScale)
                        .padding(vertical = 6.dp)
                        .clickable(
                            onClick = onFavoriteClick
                        )
                )
            }
        }
    }

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