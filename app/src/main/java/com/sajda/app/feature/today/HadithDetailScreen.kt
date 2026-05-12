package com.sajda.app.feature.today

import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajda.app.R
import com.sajda.app.feature.common.FavoriteKeys
import com.sajda.app.feature.common.FavoritePreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
@Composable
fun HadithDetailScreen(
    hadith: DailyHadith,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val favoritePreferences = remember { FavoritePreferences(context) }
    val hadithKey = FavoriteKeys.hadithKey(hadith.id)

    var isFavorite by remember(hadithKey) {
        mutableStateOf(false)
    }
    var menuExpanded by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(hadithKey) {
        val favorites = favoritePreferences.favoriteKeysFlow.first()
        isFavorite = favorites.contains(hadithKey)
    }

    val favoriteScale by animateFloatAsState(
        targetValue = if (isFavorite) 1.25f else 1f,
        animationSpec = spring(),
        label = "hadithFavoriteScale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAF8))
            .padding(16.dp)
    ) {
        TextButton(
            onClick = onBackClick
        ) {
            Text(
                text = stringResource(R.string.back),
                color = Color(0xFF1F2A24),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.daily_hadith),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2A24)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(
                    onClick = {
                        val shareText = buildHadithShareText(hadith)

                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shareText)
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(
                            sendIntent,
                            context.getString(R.string.share_hadith)
                        )

                        context.startActivity(shareIntent)
                    }
                ) {
                    Text(
                        text = "↗",
                        fontSize = 24.sp,
                        color = Color(0xFF1F2A24),
                        modifier = Modifier.alpha(0.7f)
                    )
                }

                IconButton(
                    onClick = {
                        isFavorite = !isFavorite

                        coroutineScope.launch {
                            favoritePreferences.toggleFavorite(hadithKey)
                        }
                    }
                ) {
                    Text(
                        text = if (isFavorite) "♥" else "♡",
                        fontSize = 26.sp,
                        color = if (isFavorite) Color(0xFF7DD3A7) else Color(0xFF1F2A24),
                        modifier = Modifier.scale(favoriteScale)
                    )
                }

                IconButton(
                    onClick = {
                        menuExpanded = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color(0xFF1F2A24)
                    )
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = {
                        menuExpanded = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    tint = Color(0xFF7DD3A7)
                                )

                                Text(
                                    text = stringResource(R.string.favorite_hadiths)
                                )
                            }
                        },
                        onClick = {
                            menuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = hadith.source,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )

                hadith.arabicText?.let { arabicText ->
                    Text(
                        text = arabicText,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF1F2A24),
                        lineHeight = 40.sp
                    )
                }

                Text(
                    text = hadith.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF1F2A24),
                    lineHeight = 30.sp
                )

                hadith.explanation?.let { explanation ->
                    Text(
                        text = explanation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF4A4A4A),
                        lineHeight = 26.sp
                    )
                }
            }
        }
    }
}

private const val SHARE_BRANDING = "via Sajda App"

private fun buildHadithShareText(hadith: DailyHadith): String {
    return buildString {
        appendLine(hadith.text)
        appendLine()
        appendLine("— ${hadith.source}")
        appendLine(SHARE_BRANDING)
    }
}