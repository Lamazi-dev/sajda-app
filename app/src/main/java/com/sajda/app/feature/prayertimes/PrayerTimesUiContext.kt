package com.sajda.app.feature.prayertimes

data class PrayerTimesUiContext(
    val cityName: String,
    val monthYearLabel: String
)

val mockPrayerTimesUiContext = PrayerTimesUiContext(
    cityName = "Balıkesir",
    monthYearLabel = "Nisan 2025"
)