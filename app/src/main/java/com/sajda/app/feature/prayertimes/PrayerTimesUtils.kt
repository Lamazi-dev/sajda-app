package com.sajda.app.feature.prayertimes

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
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
    val parsedPrayerTimes = prayerTimes.toParsedPrayerTimes()

    for (index in 0 until parsedPrayerTimes.lastIndex) {
        val current = parsedPrayerTimes[index]
        val next = parsedPrayerTimes[index + 1]

        if (!now.isBefore(current.time) && now.isBefore(next.time)) {
            return current.item
        }
    }

    return parsedPrayerTimes.last().item
}

fun getNextPrayer(
    prayerTimes: PrayerTimes,
    now: LocalTime
): PrayerTimeItem {
    val parsedPrayerTimes = prayerTimes.toParsedPrayerTimes()

    return parsedPrayerTimes.firstOrNull { parsedPrayer ->
        now.isBefore(parsedPrayer.time)
    }?.item ?: parsedPrayerTimes.first().item
}

fun getRemainingDurationUntilNextPrayer(
    prayerTimes: PrayerTimes,
    now: LocalTime
): Duration {
    val parsedPrayerTimes = prayerTimes.toParsedPrayerTimes()

    val nextPrayerTime = parsedPrayerTimes.firstOrNull { parsedPrayer ->
        now.isBefore(parsedPrayer.time)
    }?.time

    return if (nextPrayerTime != null) {
        Duration.between(now, nextPrayerTime)
    } else {
        Duration.between(now, LocalTime.MAX)
            .plusSeconds(1)
            .plus(Duration.between(LocalTime.MIDNIGHT, parsedPrayerTimes.first().time))
    }
}

fun formatDurationAsHourMinute(duration: Duration): String {
    val totalMinutes = duration.toMinutes().coerceAtLeast(0)
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60

    return String.format(
        Locale.getDefault(),
        "%02d:%02d",
        hours,
        minutes
    )
}

private fun PrayerTimes.toParsedPrayerTimes(): List<ParsedPrayerTime> {
    return toPrayerTimeItems().map { item ->
        ParsedPrayerTime(
            item = item,
            time = LocalTime.parse(item.time)
        )
    }
}

private data class ParsedPrayerTime(
    val item: PrayerTimeItem,
    val time: LocalTime
)