package com.example.kotlintodo.realm

import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import com.example.kotlintodo.db.ToDo
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.lang.Exception


object ToDoAccessor {
    private val sharedPreferences: SharedPreferences
    private val lastId: Long

    init{
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Realm.getApplicationContext())
        lastId = sharedPreferences.getLong("lastId", 0)
    }

    private val preferencesEditor =  sharedPreferences.edit()

    private val realm: Realm = Realm.getDefaultInstance()

    private var nextId = lastId
        get() {
            field += 1
            preferencesEditor.putLong("lastId", field)
            preferencesEditor.apply()
            return field
        }

    fun create(title: String): Boolean {
        try {
            realm.beginTransaction()
//            var todo = realm.createObject<ToDo>(1) // test
            var todo = realm.createObject<ToDo>(nextId)
            todo.title = title
            realm.commitTransaction()
        } catch (e: Exception) {
            Log.e(this.toString(), e.toString())
            return false
        }

        return true
    }


    fun getAll(): OrderedRealmCollection<ToDo> {
        Log.d("lastId:", lastId.toString())
        return realm.where<ToDo>().sort("created").findAll()
    }

    fun find(id: Long): ToDo? {
        return realm.where<ToDo>().equalTo("id", id).findFirst()
    }

    fun update(id: Long, title: String) {
        realm.beginTransaction()

        val todo: ToDo? = find(id)

        if (todo != null) {
            todo.title = title
        } else {
            Log.d(this.toString(), "failed to update nul")
        }

        realm.commitTransaction()
    }

    fun delete(id: Long) {
        realm.beginTransaction()

        val todo: ToDo? = find(id)

        if (todo != null) {
            todo.deleteFromRealm()
        } else {
            Log.d(this.toString(), "failed to delete nul")
        }

        realm.commitTransaction()
    }

}