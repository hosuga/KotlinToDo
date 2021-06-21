package com.example.kotlintodo.model

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import io.realm.Realm

object NextId {
    private val sharedPreferences: SharedPreferences
    val default: Long  // TODO: rename

    init{
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Realm.getApplicationContext()) //TODO: remove realm
        default = sharedPreferences.getLong("lastId", 0)
    }

    private val preferencesEditor =  sharedPreferences.edit()

    // TODO: rename
    var self = default
        get() {
            field += 1
            preferencesEditor.putLong("lastId", field)
            preferencesEditor.apply()
            return field
        }
}