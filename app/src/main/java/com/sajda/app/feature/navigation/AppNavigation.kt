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
import com.sajda.app.feature.qibla.QiblaScreen
import com.sajda.app.feature.quran.QuranReaderScreen
import com.sajda.app.feature.quran.QuranScreen
import com.sajda.app.feature.settings.SettingsScreen
import com.sajda.app.feature.today.TodayScreen

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
            composable(Routes.HOME) {
                TodayScreen()
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

                QuranReaderScreen(
                    surahNumber = surahNumber,
                    onBackClick = {
                        navController.popBackStack()
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
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String {
    val backStackEntry = navController.currentBackStackEntryAsState()
    return backStackEntry.value?.destination?.route ?: Routes.HOME
}