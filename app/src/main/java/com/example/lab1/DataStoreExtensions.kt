package com.example.lab1

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "settings")

object PreferencesKeys {
    val IS_DARK_MODE = booleanPreferencesKey("dark_mode")
}
