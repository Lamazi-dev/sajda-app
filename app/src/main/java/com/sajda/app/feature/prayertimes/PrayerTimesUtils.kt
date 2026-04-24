package com.sajda.app.feature.prayertimes
import java.time.Duration
import java.time.LocalTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun findTodayPrayerTimes(today: LocalDate): PrayerTimes {
    val formatter = DateTimeFormatter.ofPattern("d MMMM, EEEE", Locale("tr"))

    val todayDisplayDate = today.format(formatter)

    return prayerTimesDataSource.firstOrNull { it.date == todayDisplayDate }
        ?: prayerTimesDataSource.first()
}
fun getCurrentPrayer(
    prayerTimes: PrayerTimes,
    now: LocalTime
): PrayerTimeItem {
    val parsed = prayerTimes.toPrayerTimeItems().map { item ->
        item to LocalTime.parse(item.time)
    }

    for (i in parsed.indices) {
        val current = parsed[i]
        val next = parsed.getOrNull(i + 1)

        if (next != null) {
            val currentTime = current.second
            val nextTime = next.second

            if (!now.isBefore(currentTime) && now.isBefore(nextTime)) {
                return current.first
            }
        }
    }

    // Yatsı sonrası ve imsak öncesi aralığı
    return parsed.last().first
}

fun getNextPrayer(
    prayerTimes: PrayerTimes,
    now: LocalTime
): PrayerTimeItem {
    val parsed = prayerTimes.toPrayerTimeItems().map { item ->
        item to LocalTime.parse(item.time)
    }

    for (item in parsed) {
        if (now.isBefore(item.second)) {
            return item.first
        }
    }

    // Gün bitince ertesi günün ilk vakti
    return parsed.first().first
}

fun getRemainingDurationUntilNextPrayer(
    prayerTimes: PrayerTimes,
    now: LocalTime
): Duration {
    val nextPrayer = getNextPrayer(prayerTimes, now)
    val nextPrayerTime = LocalTime.parse(nextPrayer.time)

    return if (now.isBefore(nextPrayerTime)) {
        Duration.between(now, nextPrayerTime)
    } else {
        val secondsUntilMidnight = Duration.between(now, LocalTime.MAX).seconds + 1
        val secondsAfterMidnight = Duration.between(LocalTime.MIDNIGHT, nextPrayerTime).seconds
        Duration.ofSeconds(secondsUntilMidnight + secondsAfterMidnight)
    }
}

fun formatDurationAsHourMinute(duration: Duration): String {
    val totalMinutes = duration.toMinutes().coerceAtLeast(0)
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60

    return String.format("%02d:%02d", hours, minutes)
}
