package com.example.kotlintodo.realm

import android.util.Log
import com.example.kotlintodo.db.ToDo
import com.example.kotlintodo.common.Settings
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.lang.Exception


object ToDoAccessor {
    private val realm: Realm = Realm.getDefaultInstance()

    fun create(title: String): Boolean {
        try {
            realm.beginTransaction()
//            var todo = realm.createObject<ToDo>(1) // test
            var todo = realm.createObject<ToDo>(Settings.nextId)
            todo.title = title
            realm.commitTransaction()
        } catch (e: Exception) {
            Log.e(this.toString(), e.toString())
            return false
        }

        return true
    }


    fun getAll(): OrderedRealmCollection<ToDo> {
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

    fun deleteAll() {
        realm.beginTransaction()

        realm.deleteAll()

        realm.commitTransaction()
    }

}