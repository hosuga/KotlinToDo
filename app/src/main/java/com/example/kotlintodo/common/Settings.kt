package com.example.kotlintodo.common

import android.app.Activity
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object Settings {
    private val sharedPreferences: SharedPreferences

    init{
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Activity().applicationContext)
    }

    private val preferencesEditor =  sharedPreferences.edit()

    var nextId = sharedPreferences.getLong("lastId", 0)
        get() {
            field += 1
            preferencesEditor.putLong("lastId", field)
            preferencesEditor.apply()
            return field
        }

}