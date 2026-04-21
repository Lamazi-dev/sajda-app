package com.sajda.app.feature.quran

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sajda.app.R

@Composable
fun QuranScreen(
    onSurahClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.nav_quran),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        item {
            ContinueReadingCard()
        }

        item {
            Text(
                text = stringResource(R.string.surahs),
                style = MaterialTheme.typography.titleMedium
            )
        }

        items(surahList) { surah ->
            SurahItem(
                name = surah.name,
                number = surah.number,
                onClick = {
                    onSurahClick(surah.number)
                }
            )
        }
    }
}

@Composable
fun ContinueReadingCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.continue_reading),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Fâtiha • Ayah 3",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(R.string.tap_to_continue),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun SurahItem(
    name: String,
    number: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$number. $name",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "→",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}