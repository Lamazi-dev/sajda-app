package com.sajda.app.feature.today

import com.sajda.app.feature.prayertimes.PrayerName

val dailyAyahPool = listOf(
    DailyAyah(
        id = 1,
        surahNumber = 18,
        surahName = "Al-Kahf",
        ayahNumber = 96,
        arabicText = "آتُونِي زُبَرَ الْحَدِيدِ",
        translation = "Bring me blocks of iron.",
        explanation = null,
        source = "Quran 18:96"
    ),
    DailyAyah(
        id = 2,
        surahNumber = 4,
        surahName = "An-Nisa",
        ayahNumber = 71,
        arabicText = "يَا أَيُّهَا الَّذِينَ آمَنُوا خُذُوا حِذْرَكُمْ",
        translation = "O believers! Take your precautions.",
        explanation = null,
        source = "Quran 4:71"
    )
)

val mockDailyContent = DailyContent(
    ayah = dailyAyahPool.random(),
    hadith = DailyHadith(
        arabicText = null,
        id = 1,
        text = "The best among you are those who have the best manners.",
        explanation = "Islam gives strong importance to character, kindness, and good treatment of others.",
        source = "Sahih al-Bukhari"
    )
)

val fajrContent = mockDailyContent
val sunriseContent = mockDailyContent
val dhuhrContent = mockDailyContent
val asrContent = mockDailyContent
val maghribContent = mockDailyContent
val ishaContent = mockDailyContent

fun getContentForPrayer(prayerName: PrayerName): DailyContent {
    return when (prayerName) {
        PrayerName.FAJR -> fajrContent
        PrayerName.SUNRISE -> sunriseContent
        PrayerName.DHUHR -> dhuhrContent
        PrayerName.ASR -> asrContent
        PrayerName.MAGHRIB -> maghribContent
        PrayerName.ISHA -> ishaContent
    }
}