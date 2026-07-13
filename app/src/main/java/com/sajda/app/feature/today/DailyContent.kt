package com.sajda.app.feature.today

data class DailyAyah(
    val id: Int,
    val surahNumber: Int,
    val surahName: String,
    val ayahNumber: Int,
    val arabicText: String,
    val translation: String,
    val explanation: String?,
    val source: String
)

data class DailyHadith(
    val id: Int,
    val arabicText: String?,
    val textTr: String,
    val textEn: String,
    val explanationTr: String?,
    val explanationEn: String?,
    val source: String,
    val topic: String,
    val isFavorite: Boolean = false
)

data class DailyContent(
    val ayah: DailyAyah,
    val hadith: DailyHadith
)