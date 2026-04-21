package com.sajda.app.feature.prayertimes

data class PrayerTimes(
    val date: String,
    val hijriDate: String,
    val fajr: String,
    val sunrise: String,
    val dhuhr: String,
    val asr: String,
    val maghrib: String,
    val isha: String
)

data class PrayerTimeItem(
    val name: String,
    val time: String
)

fun PrayerTimes.toPrayerTimeItems(): List<PrayerTimeItem> {
    return listOf(
        PrayerTimeItem(name = "Fajr", time = fajr),
        PrayerTimeItem(name = "Sunrise", time = sunrise),
        PrayerTimeItem(name = "Dhuhr", time = dhuhr),
        PrayerTimeItem(name = "Asr", time = asr),
        PrayerTimeItem(name = "Maghrib", time = maghrib),
        PrayerTimeItem(name = "Isha", time = isha)
    )
}