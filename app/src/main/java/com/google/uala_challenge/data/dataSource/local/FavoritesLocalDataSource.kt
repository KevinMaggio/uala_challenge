package com.google.uala_challenge.data.dataSource.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import androidx.core.content.edit
import com.google.uala_challenge.core.constants.KEY_FAVORITE_IDS
import com.google.uala_challenge.core.constants.PREFS_NAME

class FavoritesLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val prefs
        get() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    suspend fun getFavoriteIds(): Set<Int> = withContext(Dispatchers.IO) {
        prefs.getStringSet(KEY_FAVORITE_IDS, null)
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet()
            ?: emptySet()
    }

    suspend fun saveFavoriteIds(ids: Set<Int>) = withContext(Dispatchers.IO) {
        val current = prefs.getStringSet(KEY_FAVORITE_IDS, null)
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet()
            ?: emptySet()
        if (current != ids) {
            prefs.edit {
                putStringSet(KEY_FAVORITE_IDS, ids.map { it.toString() }.toSet())
            }
        }
    }
}
