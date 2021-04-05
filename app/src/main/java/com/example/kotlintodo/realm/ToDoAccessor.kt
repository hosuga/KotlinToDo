package com.example.kotlintodo.realm

import com.example.kotlintodo.db.ToDo
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class ToDoAccessor {
    var realm = Realm.getDefaultInstance()


    fun generateId(): Int {
        var nextId = 1

        val lastId = realm.where<ToDo>().max("id")

        if(lastId != null) {
            nextId = lastId.toInt() + 1
        }

        return nextId
    }


    fun create(title:String) {
        var todo = realm.createObject<ToDo>(generateId())
        todo.title = title
        realm.copyToRealm(todo)
    }


    fun getAll(): RealmResults<ToDo>? {
        val todos = realm.where<ToDo>().findAll()
        return todos
    }

}