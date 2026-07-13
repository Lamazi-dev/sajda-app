package com.sajda.app.feature.today
import com.sajda.app.feature.today.data.HadithRepository
import java.util.Locale
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sajda.app.feature.common.FavoriteKeys
import com.sajda.app.feature.common.FavoritePreferences

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

    val allHadiths = HadithRepository.getAllHadiths()

    val favoriteHadiths = allHadiths.filter { hadith ->
        favoriteKeys.contains(FavoriteKeys.hadithKey(hadith.id))
    }

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
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clickable { onBackClick() }
        )

        LazyColumn {
            if (favoriteHadiths.isEmpty()) {
                item {
                    Text(
                        text = "Henüz favori hadis yok",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                items(favoriteHadiths) { hadith ->
                    val isTurkish = Locale.getDefault().language == "tr"
                    val hadithText = if (isTurkish) hadith.textTr else hadith.textEn
                    val hadithExplanation = if (isTurkish) hadith.explanationTr else hadith.explanationEn
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = hadith.source,
                                color = Color(0xFF6B756F),
                                style = MaterialTheme.typography.labelMedium
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = hadithText,
                                color = Color(0xFF1F2A24),
                                style = MaterialTheme.typography.bodyLarge
                            )

                            hadithExplanation?.let { explanation ->
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = explanation,
                                    color = Color(0xFF6B756F),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}