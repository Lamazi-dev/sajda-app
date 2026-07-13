package com.sajda.app.feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sajda.app.feature.prayertimes.PrayerTimesScreen
import com.sajda.app.feature.prayertimes.findTodayPrayerTimes
import com.sajda.app.feature.prayertimes.getCurrentPrayer
import com.sajda.app.feature.qibla.QiblaScreen
import com.sajda.app.feature.quran.FavoriteAyahsScreen
import com.sajda.app.feature.quran.QuranReaderScreen
import com.sajda.app.feature.quran.QuranScreen
import com.sajda.app.feature.settings.SettingsScreen
import com.sajda.app.feature.today.FavoriteHadithsScreen
import com.sajda.app.feature.today.HadithDetailScreen
import com.sajda.app.feature.today.HadithLibraryScreen
import com.sajda.app.feature.today.TodayScreen
import com.sajda.app.feature.today.getContentForPrayer
import java.time.LocalDateTime

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            SajdaBottomBar(
                currentRoute = currentRoute(navController),
                onItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(Routes.HOME)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.FAVORITE_AYAHS) {
                FavoriteAyahsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onAyahClick = { surahNumber, ayahNumber ->
                        navController.navigate(
                            Routes.quranReaderRoute(
                                surahNumber = surahNumber,
                                ayahNumber = ayahNumber
                            )
                        )
                    }
                )
            }

            composable(Routes.HOME) {
                TodayScreen(
                    onDailyAyahClick = { surahNumber, ayahNumber ->
                        navController.navigate(
                            Routes.quranReaderRoute(
                                surahNumber = surahNumber,
                                ayahNumber = ayahNumber
                            )
                        )
                    },
                    onDailyHadithClick = {
                        navController.navigate(Routes.HADITH_DETAIL)
                    },
                    onHadithLibraryClick = {
                        navController.navigate(Routes.HADITH_LIBRARY)
                    }
                )
            }

            composable(Routes.QURAN) {
                QuranScreen(
                    onSurahClick = { surahNumber ->
                        navController.navigate(Routes.quranReaderRoute(surahNumber))
                    }
                )
            }

            composable(Routes.QURAN_READER) { backStackEntry ->
                val surahNumber =
                    backStackEntry.arguments?.getString("surahNumber")?.toIntOrNull() ?: 1

                val ayahNumber =
                    backStackEntry.arguments?.getString("ayahNumber")?.toIntOrNull() ?: 1

                QuranReaderScreen(
                    surahNumber = surahNumber,
                    initialSelectedAyahNumber = ayahNumber,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onFavoriteAyahsClick = {
                        navController.navigate(Routes.FAVORITE_AYAHS)
                    }
                )
            }

            composable(Routes.QIBLA) {
                QiblaScreen()
            }

            composable(Routes.PRAYER_TIMES) {
                PrayerTimesScreen()
            }

            composable(Routes.SETTINGS) {
                SettingsScreen()
            }

            composable(Routes.HADITH_DETAIL) {
                val now = LocalDateTime.now()
                val todayPrayerTimes = findTodayPrayerTimes(now.toLocalDate())
                val currentPrayer = getCurrentPrayer(todayPrayerTimes, now.toLocalTime())
                val currentContent = getContentForPrayer(currentPrayer.name)

                HadithDetailScreen(
                    hadith = currentContent.hadith,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onFavoriteHadithsClick = {
                        navController.navigate(Routes.FAVORITE_HADITHS)
                    }
                )
            }

            composable(Routes.FAVORITE_HADITHS) {
                FavoriteHadithsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Routes.HADITH_LIBRARY) {
                HadithLibraryScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onFavoriteHadithsClick = {
                        navController.navigate(Routes.FAVORITE_HADITHS)
                    }
                )
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String {
    val backStackEntry = navController.currentBackStackEntryAsState()
    return backStackEntry.value?.destination?.route ?: Routes.HOME
}