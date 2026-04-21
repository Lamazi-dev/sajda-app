package com.sajda.app.feature.navigation

object Routes {
    const val HOME = "home"
    const val QURAN = "quran"
    const val QIBLA = "qibla"
    const val PRAYER_TIMES = "prayer_times"
    const val SETTINGS = "settings"
    const val QURAN_READER = "quran_reader/{surahNumber}"
    fun quranReaderRoute(surahNumber: Int): String {
        return "quran_reader/$surahNumber"
    }
}