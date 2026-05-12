package com.sajda.app.feature.navigation

object Routes {
    const val HOME = "home"
    const val QURAN = "quran"
    const val FAVORITE_AYAHS = "favorite_ayahs"
    const val QIBLA = "qibla"
    const val PRAYER_TIMES = "prayer_times"
    const val SETTINGS = "settings"

    const val HADITH_DETAIL = "hadith_detail"

    const val QURAN_READER = "quran_reader/{surahNumber}/{ayahNumber}"

    fun quranReaderRoute(
        surahNumber: Int,
        ayahNumber: Int = 1
    ): String {
        return "quran_reader/$surahNumber/$ayahNumber"
    }
}