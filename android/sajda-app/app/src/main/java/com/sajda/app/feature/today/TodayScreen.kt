package com.sajda.app.feature.today

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sajda.app.domain.model.PrayerTime

@Composable
fun TodayScreen() {
    val cityName = "Balikesir"
    var now by remember { mutableStateOf(java.time.LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(1000)
            now = java.time.LocalDateTime.now()
        }
    }

    val currentClock = now.format(
        java.time.format.DateTimeFormatter.ofPattern("HH:mm")
    )

    val currentDate = now.format(
        java.time.format.DateTimeFormatter.ofPattern("dd MMMM yyyy")
    )

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
            text = cityName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "$currentDate • 5 Shawwal 1447",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        PrayerHeroCard(now = now)

        Spacer(modifier = Modifier.height(16.dp))

        AyahCard()

        Spacer(modifier = Modifier.height(16.dp))

        HadithCard()

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PrayerHeroCard(now: java.time.LocalDateTime) {
    val prayerTimes = listOf(
        PrayerTime("Fajr", "05:35", false),
        PrayerTime("Sun", "06:59", false),
        PrayerTime("Dhuhr", "13:20", false),
        PrayerTime("Asr", "16:47", false),
        PrayerTime("Mag", "19:31", false),
        PrayerTime("Isha", "20:49", false)
    )

    val prayerTimesWithParsed = prayerTimes.map { prayer ->
        prayer to java.time.LocalTime.parse(prayer.time)
    }

    val currentTime = now.toLocalTime()

    val activePrayer = prayerTimesWithParsed
        .lastOrNull { (_, time) -> currentTime >= time }
        ?.first ?: prayerTimes.first()

    val currentIndex = prayerTimes.indexOfFirst { it.name == activePrayer.name }

    val nextPrayer = if (currentIndex != -1 && currentIndex < prayerTimes.size - 1) {
        prayerTimes[currentIndex + 1]
    } else {
        prayerTimes.first()
    }

    val nextPrayerLocalTime = java.time.LocalTime.parse(nextPrayer.time)

    val targetDateTime = if (nextPrayerLocalTime.isAfter(currentTime)) {
        java.time.LocalDateTime.of(now.toLocalDate(), nextPrayerLocalTime)
    } else {
        java.time.LocalDateTime.of(now.toLocalDate().plusDays(1), nextPrayerLocalTime)
    }

    var timeLeft by remember(targetDateTime) {
        mutableStateOf(
            java.time.Duration.between(java.time.LocalDateTime.now(), targetDateTime)
                .seconds
                .coerceAtLeast(0)
                .toInt()
        )
    }

    LaunchedEffect(targetDateTime) {
        while (true) {
            timeLeft = java.time.Duration.between(
                java.time.LocalDateTime.now(),
                targetDateTime
            ).seconds.coerceAtLeast(0).toInt()

            kotlinx.coroutines.delay(1000)
        }
    }

    val hours = timeLeft / 3600
    val minutes = (timeLeft % 3600) / 60
    val seconds = timeLeft % 60

    val formattedTime = String.format("%02d:%02d", hours, minutes)
    val secondsPart = String.format(":%02d", seconds)

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
                text = activePrayer.name,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Next Prayer",
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
                prayerTimes.forEach { prayer ->
                    PrayerTimeItem(
                        name = prayer.name,
                        time = prayer.time,
                        isActive = prayer.name == activePrayer.name
                    )
                }
            }
        }
    }
}

@Composable
fun AyahCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                text = "Daily Ayah",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F2A24)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Al-Kahf 96",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "\"Bring me blocks of iron,\" he said.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF1F2A24),
                lineHeight = 26.sp
            )
        }
    }
}

@Composable
fun HadithCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                text = "Daily Hadith",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F2A24)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Prophet Muhammad (ﷺ)",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "\"The best among you are those who have the best manners.\"",
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