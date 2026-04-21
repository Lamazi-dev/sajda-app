package com.sajda.app.feature.prayertimes
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.sajda.app.R
@Composable
fun PrayerDayCard(
    date: String,
    hijriDate: String,
    times: PrayerTimes,
    isToday: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(
                width = if (isToday) 1.dp else 0.dp,
                color = if (isToday) Color(0xFF3E5B4A) else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = if (isToday) Color(0xFF1E2D24) else Color(0xFF2A2A2A),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Text(
                text = hijriDate,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.18f)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            PrayerTimeColumn(
                label = stringResource(R.string.prayer_fajr),
                time = times.fajr,
                modifier = Modifier.weight(1f)
            )
            PrayerTimeColumn(
                label = stringResource(R.string.prayer_sunrise),
                time = times.sunrise,
                modifier = Modifier.weight(1f)
            )
            PrayerTimeColumn(
                label = stringResource(R.string.prayer_dhuhr),
                time = times.dhuhr,
                modifier = Modifier.weight(1f)
            )
            PrayerTimeColumn(
                label = stringResource(R.string.prayer_asr),
                time = times.asr,
                modifier = Modifier.weight(1f)
            )
            PrayerTimeColumn(
                label = stringResource(R.string.prayer_maghrib),
                time = times.maghrib,
                modifier = Modifier.weight(1f)
            )
            PrayerTimeColumn(
                label = stringResource(R.string.prayer_isha),
                time = times.isha,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PrayerTimeColumn(
    label: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.widthIn(min = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = time,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}