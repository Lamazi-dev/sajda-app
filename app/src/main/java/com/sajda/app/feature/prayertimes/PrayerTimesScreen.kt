package com.sajda.app.feature.prayertimes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sajda.app.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
@Composable
fun PrayerTimesScreen() {
    val today = LocalDate.now()
    val todayFormatter = DateTimeFormatter.ofPattern("d MMMM, EEEE", Locale("tr"))
    val todayDisplayDate = today.format(todayFormatter)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.prayer_times_title),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${mockPrayerTimesUiContext.cityName} • ${mockPrayerTimesUiContext.monthYearLabel}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray
            )
        }

        itemsIndexed(mockPrayerTimesList) { _, prayerTimes ->
            val isToday = prayerTimes.date == todayDisplayDate

            PrayerDayCard(
                date = prayerTimes.date,
                hijriDate = prayerTimes.hijriDate,
                times = prayerTimes,
                isToday = isToday
            )
        }
    }
}