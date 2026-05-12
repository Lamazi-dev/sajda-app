package com.sajda.app.feature.common

object FavoriteKeys {

    fun hadithKey(hadithId: Int): String {
        return "hadith:$hadithId"
    }

    fun ayahKey(
        surahNumber: Int,
        ayahNumber: Int
    ): String {
        return "ayah:$surahNumber:$ayahNumber"
    }
}