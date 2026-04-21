package com.sajda.app.feature.quran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
@Composable
fun QuranReaderScreen(
    surahNumber: Int,
    onBackClick: () -> Unit
) {
    val surah = surahList.firstOrNull { it.number == surahNumber }
    val surahName = surah?.name ?: "Fâtiha"
    val ayahCount = surah?.ayahCount ?: 7
    val ayahList = getMockAyahsForSurah(surahNumber)
    var selectedAyahNumber by remember { mutableStateOf<Int?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        QuranReaderHeader(
            surahName = surahName,
            onBackClick = onBackClick
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
                    AyahItem(
                        ayah = ayah,
                        isSelected = selectedAyahNumber == ayah.number,
                        onClick = {
                            selectedAyahNumber =
                                if (selectedAyahNumber == ayah.number) null else ayah.number
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