package com.sajda.app.feature.today

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
import androidx.compose.ui.unit.dp
import com.sajda.app.feature.common.FavoritePreferences
import androidx.compose.ui.platform.LocalContext

@Composable
fun FavoriteHadithsScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val favoritePreferences = remember {
        FavoritePreferences(context)
    }

    val favoriteKeys by favoritePreferences.favoriteKeysFlow.collectAsState(
        initial = emptySet()
    )

    val hadithFavorites = favoriteKeys
        .filter { it.startsWith("hadith:") }
        .sorted()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAF8))
            .padding(16.dp)
    ) {
        Text(
            text = "← Favori Hadisler",
            color = Color(0xFF1F2A24),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            if (hadithFavorites.isEmpty()) {
                item {
                    Text(
                        text = "Henüz favori hadis yok",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                items(hadithFavorites) { key ->
                    Text(
                        text = key,
                        color = Color(0xFF1F2A24),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}