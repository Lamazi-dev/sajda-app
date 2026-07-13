package com.sajda.app.feature.common

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.favoriteDataStore by preferencesDataStore(
    name = "favorite_preferences"
)

class FavoritePreferences(
    private val context: Context
) {
    private val favoriteKeys = stringSetPreferencesKey("favorite_keys")

    val favoriteKeysFlow: Flow<Set<String>> =
        context.favoriteDataStore.data.map { preferences ->
            preferences[favoriteKeys] ?: emptySet()
        }

    fun isFavoriteFlow(key: String): Flow<Boolean> =
        favoriteKeysFlow.map { favoriteKeys ->
            favoriteKeys.contains(key)
        }

    suspend fun toggleFavorite(key: String) {
        context.favoriteDataStore.edit { preferences ->
            val currentKeys = preferences[favoriteKeys] ?: emptySet()

            preferences[favoriteKeys] =
                if (currentKeys.contains(key)) {
                    currentKeys - key
                } else {
                    currentKeys + key
                }
        }
    }
}