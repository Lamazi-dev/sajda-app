package com.sajda.app.feature.prayertimes

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sajda.app.R

@Composable
fun PrayerName.toUiLabel(): String {
    return when (this) {
        PrayerName.FAJR -> stringResource(R.string.prayer_fajr)
        PrayerName.SUNRISE -> stringResource(R.string.prayer_sunrise)
        PrayerName.DHUHR -> stringResource(R.string.prayer_dhuhr)
        PrayerName.ASR -> stringResource(R.string.prayer_asr)
        PrayerName.MAGHRIB -> stringResource(R.string.prayer_maghrib)
        PrayerName.ISHA -> stringResource(R.string.prayer_isha)
    }
}