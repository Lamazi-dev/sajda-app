package com.sajda.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sajda.app.feature.today.TodayScreen
import com.sajda.app.ui.theme.SajdaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SajdaTheme {
                TodayScreen()
            }
        }
    }
}