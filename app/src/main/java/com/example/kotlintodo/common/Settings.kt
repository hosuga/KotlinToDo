package com.example.kotlintodo.common

import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import io.realm.Realm

object Settings {
    private val sharedPreferences: SharedPreferences

    init{
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Realm.getApplicationContext())
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