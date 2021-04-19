package com.example.kotlintodo.realm

import android.util.Log
import com.example.kotlintodo.db.ToDo
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class ToDoAccessor {
    var realm = Realm.getDefaultInstance()

    var nextId: Long = 1
        get() {
            val lastId: Number? = realm.where<ToDo>().max("id")

            return if(lastId != null) { lastId.toLong() + 1 } else { 1 }

        }

    fun create(title: String) {
        realm.beginTransaction()
//        var todo = realm.createObject<ToDo>(1) // test
        var todo = realm.createObject<ToDo>(nextId)
        todo.title = title
        realm.commitTransaction()
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
        todo?.title = title
        realm.commitTransaction()
    }

    fun delete(id: Long) {
        realm.beginTransaction()
        val todo: ToDo? = find(id)
        todo?.deleteFromRealm()
        realm.commitTransaction()
    }

}