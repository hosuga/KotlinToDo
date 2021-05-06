package com.example.kotlintodo.common

import androidx.preference.PreferenceManager
import com.example.kotlintodo.realm.ToDoAccessor
import io.realm.Realm.getApplicationContext


val sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())

// FIXME:
val toDoAccessor = ToDoAccessor.getInstance()
