package com.sajda.app.feature.today

data class TodayUiContext(
    val cityName: String,
    val hijriDateLabel: String
)

val mockTodayUiContext = TodayUiContext(
    cityName = "Balikesir",
    hijriDateLabel = "5 Shawwal 1447"
)