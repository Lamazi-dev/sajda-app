package com.sajda.app.feature.quran

val fatihaAyahs = listOf(
    Ayah(1, "بِسْمِ ٱللّٰهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ", "Rahmân ve Rahîm olan Allah'ın adıyla."),
    Ayah(2, "ٱلْحَمْدُ لِلّٰهِ رَبِّ ٱلْعَالَمِينَ", "Hamd, âlemlerin Rabbi olan Allah'a mahsustur."),
    Ayah(3, "ٱلرَّحْمَٰنِ ٱلرَّحِيمِ", "O, Rahmân'dır, Rahîm'dir."),
    Ayah(4, "مَٰلِكِ يَوْمِ ٱلدِّينِ", "Din gününün sahibidir."),
    Ayah(5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Yalnız sana ibadet eder, yalnız senden yardım dileriz."),
    Ayah(6, "ٱهْدِنَا ٱلصِّرَٰطَ ٱلْمُسْتَقِيمَ", "Bizi dosdoğru yola ilet."),
    Ayah(7, "صِرَٰطَ ٱلَّذِينَ أَنْعَمْتَ عَلَيْهِمْ", "Nimet verdiklerinin yoluna; gazaba uğrayanlarınkine ve sapmışlarınkine değil.")
)
val ikhlasAyahs = listOf(
    Ayah(1, "قُلْ هُوَ ٱللّٰهُ أَحَدٌ", "De ki: O Allah birdir."),
    Ayah(2, "ٱللّٰهُ ٱلصَّمَدُ", "Allah Samed'dir."),
    Ayah(3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "O doğurmamış ve doğmamıştır."),
    Ayah(4, "وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌ", "Hiçbir şey O'na denk olmamıştır.")
)
val mockSurahAyahs = mapOf(
    1 to fatihaAyahs,
    112 to ikhlasAyahs
    // 2 to baqaraAyahs
    // 3 to aliImranAyahs
)

fun getMockAyahsForSurah(surahNumber: Int): List<Ayah> {
    return mockSurahAyahs[surahNumber] ?: emptyList()
}