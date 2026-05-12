package com.sajda.app.feature.today

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajda.app.R
import com.sajda.app.feature.prayertimes.PrayerName
import com.sajda.app.feature.prayertimes.findTodayPrayerTimes
import com.sajda.app.feature.prayertimes.formatDurationAsHourMinute
import com.sajda.app.feature.prayertimes.getCurrentPrayer
import com.sajda.app.feature.prayertimes.getRemainingDurationUntilNextPrayer
import com.sajda.app.feature.prayertimes.toPrayerTimeItems
import com.sajda.app.feature.prayertimes.toUiLabel
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TodayScreen(
    onDailyAyahClick: (surahNumber: Int, ayahNumber: Int) -> Unit,
    onDailyHadithClick: () -> Unit
) {
    var now by remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            now = LocalDateTime.now()
        }
    }

    val currentClock = now.format(DateTimeFormatter.ofPattern("HH:mm"))
    val currentDate = now.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAF8))
            .padding(16.dp)
    ) {
        Text(
            text = currentClock,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = mockTodayUiContext.cityName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "$currentDate • ${mockTodayUiContext.hijriDateLabel}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))
        val todayPrayerTimes = findTodayPrayerTimes(now.toLocalDate())
        val currentPrayer = getCurrentPrayer(todayPrayerTimes, now.toLocalTime())
        val currentContent = getContentForPrayer(currentPrayer.name)
        PrayerHeroCard(now = now)

        Spacer(modifier = Modifier.height(16.dp))

        AyahCard(
            ayah = currentContent.ayah,
            onClick = {
                onDailyAyahClick(
                    currentContent.ayah.surahNumber,
                    currentContent.ayah.ayahNumber
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HadithCard(
            hadith = currentContent.hadith,
            onClick = onDailyHadithClick
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PrayerHeroCard(now: LocalDateTime) {
    val todayPrayerTimes = findTodayPrayerTimes(now.toLocalDate())
    val currentTime = now.toLocalTime()

    val currentPrayer = getCurrentPrayer(todayPrayerTimes, currentTime)
    val remainingDuration = getRemainingDurationUntilNextPrayer(todayPrayerTimes, currentTime)
    val formattedTime = formatDurationAsHourMinute(remainingDuration)

    val secondsPart = String.format(
        java.util.Locale.getDefault(),
        ":%02d",
        remainingDuration.seconds % 60
    )

    val prayerTimesRow = todayPrayerTimes.toPrayerTimeItems().map { prayer ->
        PrayerTimeUiItem(
            name = prayer.name,
            label = prayer.name.toUiLabel(),
            time = prayer.time
        )
    }

    val activePrayerLabel = currentPrayer.name.toUiLabel()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1F2A24)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = activePrayerLabel,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.next_prayer),
                color = Color(0xFFBFC8C2),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(1.dp))

            Row(
                verticalAlignment = androidx.compose.ui.Alignment.Bottom
            ) {
                Text(
                    text = formattedTime,
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = secondsPart,
                    color = Color(0xFFBFC8C2),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFF344139)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                prayerTimesRow.forEach { prayer ->
                    PrayerTimeItem(
                        name = prayer.label,
                        time = prayer.time,
                        isActive = prayer.name == currentPrayer.name
                    )
                }
            }
        }
    }
}

@Composable
fun AyahCard(
    ayah: DailyAyah,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.daily_ayah),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F2A24)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${ayah.surahName} ${ayah.ayahNumber}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = ayah.translation,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF1F2A24),
                lineHeight = 26.sp
            )
        }
    }
}

@Composable
fun HadithCard(
    hadith: DailyHadith,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.daily_hadith),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F2A24)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = hadith.source,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = hadith.text,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF1F2A24),
                lineHeight = 26.sp
            )
        }
    }
}

@Composable
fun PrayerTimeItem(
    name: String,
    time: String,
    isActive: Boolean
) {
    val textColor = if (isActive) Color(0xFF7DD3A7) else Color.White

    Column {
        Text(
            text = name,
            color = textColor,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = time,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
    }
}

data class PrayerTimeUiItem(
    val name: PrayerName,
    val label: String,
    val time: String
)