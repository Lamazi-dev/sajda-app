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
    val name: PrayerName,
    val time: String
)

fun PrayerTimes.toPrayerTimeItems(): List<PrayerTimeItem> {
    return listOf(
        PrayerTimeItem(name = PrayerName.FAJR, time = fajr),
        PrayerTimeItem(name = PrayerName.SUNRISE, time = sunrise),
        PrayerTimeItem(name = PrayerName.DHUHR, time = dhuhr),
        PrayerTimeItem(name = PrayerName.ASR, time = asr),
        PrayerTimeItem(name = PrayerName.MAGHRIB, time = maghrib),
        PrayerTimeItem(name = PrayerName.ISHA, time = isha)
    )
}