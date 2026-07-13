package com.sajda.app.feature.today

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HadithLibraryScreen(
    onBackClick: () -> Unit,
    onFavoriteHadithsClick: () -> Unit
) {
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
                text = "← Geri",
                color = Color(0xFF1F2A24),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Hadisler",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2A24)
        )

        Text(
            text = "Konu, kaynak ve favorilerine göre hadisleri keşfet.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        HadithLibraryInfoCard(
            title = "Favori Hadisler",
            subtitle = "Kaydettiğin hadisleri görüntüle.",
            onClick = onFavoriteHadithsClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        HadithLibraryInfoCard(
            title = "Konuya Göre Hadisler",
            subtitle = "Sabır, ahlâk, ilim, dua ve daha fazlası.",
            onClick = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        HadithLibraryInfoCard(
            title = "Kaynaklar",
            subtitle = "Buhârî, Müslim ve diğer kaynaklara göre düzenleme.",
            onClick = {}
        )
    }
}

@Composable
private fun HadithLibraryInfoCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1F2A24)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF1F2A24)
            )
        }
    }
}