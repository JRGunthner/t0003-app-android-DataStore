package com.jgtche.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencesds")

class PreferencesManager(private val context: Context) {
    suspend fun save(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { pref -> pref[preferencesKey] = value}
    }

    suspend fun read(key: String): String {
        val preferencesKey = stringPreferencesKey(key)
        val data = context.dataStore.data.first()

        return data[preferencesKey] ?: ""
    }

    suspend fun remove(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { pref -> pref.remove(preferencesKey)}
    }
}