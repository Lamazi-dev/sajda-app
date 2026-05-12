package com.sajda.app.feature.quran
import com.sajda.app.feature.common.FavoriteKeys
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import com.sajda.app.R
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.sajda.app.feature.common.FavoritePreferences
import kotlinx.coroutines.launch
@Composable
fun QuranReaderScreen(
    surahNumber: Int,
    initialSelectedAyahNumber: Int? = null,
    onBackClick: () -> Unit,
    onFavoriteAyahsClick: () -> Unit
) {
    val surah = surahList.firstOrNull { it.number == surahNumber }
    val surahName = surah?.name ?: "Fâtiha"
    val ayahCount = surah?.ayahCount ?: 7
    val ayahList = getMockAyahsForSurah(surahNumber)
    val context = LocalContext.current

    var selectedAyahNumber by remember {
        mutableStateOf(initialSelectedAyahNumber)
    }
    val favoritePreferences = remember {
        FavoritePreferences(context)
    }

    val favoriteKeys by favoritePreferences.favoriteKeysFlow.collectAsState(
        initial = emptySet()
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        QuranReaderHeader(
            surahName = surahName,
            onBackClick = onBackClick,
            onMenuClick = onFavoriteAyahsClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            item {
                ReaderAyahRange(ayahCount = ayahCount)
                Spacer(modifier = Modifier.height(36.dp))
            }

            if (ayahList.isEmpty()) {
                item {
                    QuranReaderEmptyState()
                }
            } else {
                items(ayahList) { ayah ->
                    val ayahKey = FavoriteKeys.ayahKey(
                        surahNumber = surahNumber,
                        ayahNumber = ayah.number
                    )
                    val isFavorite = favoriteKeys.contains(ayahKey)
                    AyahItem(
                        ayah = ayah.copy(
                            isFavorite = isFavorite
                        ),
                        isSelected = selectedAyahNumber == ayah.number,
                        onClick = {
                            selectedAyahNumber =
                                if (selectedAyahNumber == ayah.number) null else ayah.number
                        },
                        onFavoriteClick = {
                            coroutineScope.launch {
                                favoritePreferences.toggleFavorite(ayahKey)
                            }
                        },
                        onShareClick = {
                            val shareText = buildAyahShareText(
                                surahName = surahName,
                                ayah = ayah
                            )

                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(
                                sendIntent,
                                context.getString(R.string.share_ayah)
                            )

                            context.startActivity(shareIntent)
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
private const val SHARE_BRANDING = "via Sajda App"

private fun buildAyahShareText(
    surahName: String,
    ayah: Ayah
): String {
    return buildString {
        appendLine(ayah.arabicText)
        appendLine()
        appendLine(ayah.translation)
        appendLine()
        appendLine("— $surahName ${ayah.number}")
        appendLine(SHARE_BRANDING)
    }
}