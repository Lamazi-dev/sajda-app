package com.sajda.app.feature.quran

data class Ayah(
    val number: Int,
    val arabicText: String,
    val translation: String,
    val isFavorite: Boolean = false
)