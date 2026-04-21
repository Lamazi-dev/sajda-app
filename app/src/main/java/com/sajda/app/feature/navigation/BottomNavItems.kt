package com.sajda.app.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sajda.app.R

@Composable
fun bottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            route = Routes.HOME,
            label = stringResource(R.string.nav_home)
        ),
        BottomNavItem(
            route = Routes.QURAN,
            label = stringResource(R.string.nav_quran)
        ),
        BottomNavItem(
            route = Routes.QIBLA,
            label = stringResource(R.string.nav_qibla)
        ),
        BottomNavItem(
            route = Routes.PRAYER_TIMES,
            label = stringResource(R.string.nav_prayer_times)
        ),
        BottomNavItem(
            route = Routes.SETTINGS,
            label = stringResource(R.string.nav_settings)
        )
    )
}