package com.sajda.app.feature.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sajda.app.R
import com.sajda.app.feature.common.FavoritePreferences
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
@Composable
fun FavoriteAyahsScreen(
    onBackClick: () -> Unit,
    onAyahClick: (surahNumber: Int, ayahNumber: Int) -> Unit
) {
    val context = LocalContext.current

    val favoritePreferences = remember {
        FavoritePreferences(context)
    }

    val favoriteKeys by favoritePreferences.favoriteKeysFlow.collectAsState(
        initial = emptySet()
    )
    val coroutineScope = rememberCoroutineScope()

    val ayahFavorites = favoriteKeys
        .filter { it.startsWith("ayah:") }
        .sortedWith(
            compareBy(
                { key ->
                    key.split(":").getOrNull(1)?.toIntOrNull() ?: Int.MAX_VALUE
                },
                { key ->
                    key.split(":").getOrNull(2)?.toIntOrNull() ?: Int.MAX_VALUE
                }
            )
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        QuranReaderHeader(
            surahName = stringResource(R.string.favorite_ayahs),
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            if (ayahFavorites.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 96.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "♡",
                            color = Color(0xFF7DD3A7),
                            style = MaterialTheme.typography.displayMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = stringResource(R.string.no_favorite_ayahs_yet),
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = stringResource(R.string.favorite_ayahs_empty_subtitle),
                            color = Color(0xFFE0E0E0),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            } else {
                items(ayahFavorites) { key ->
                    val parts = key.split(":")

                    if (parts.size == 3) {
                        val surahNumber = parts[1].toIntOrNull()
                        val ayahNumber = parts[2].toIntOrNull()

                        if (surahNumber != null && ayahNumber != null) {
                            val ayahs = getMockAyahsForSurah(surahNumber)
                            val ayah = ayahs.firstOrNull { it.number == ayahNumber }
                            val surahName =
                                surahList.firstOrNull {
                                    it.number == surahNumber
                                }?.name ?: ""
                            if (ayah != null) {
                                Text(
                                    text = stringResource(
                                        R.string.surah_ayah_label,
                                        surahName,
                                        ayahNumber
                                    ),
                                    color = Color(0xFF8F8F8F),
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                AyahItem(
                                    ayah = ayah.copy(isFavorite = true),
                                    isSelected = true,
                                    onClick = {
                                        onAyahClick(surahNumber, ayahNumber)
                                    },
                                    onFavoriteClick = {
                                        coroutineScope.launch {
                                            favoritePreferences.toggleFavorite(key)
                                        }
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}