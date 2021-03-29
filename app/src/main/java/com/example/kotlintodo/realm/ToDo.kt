package com.example.kotlintodo.realm

import com.example.kotlintodo.db.ToDo
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class ToDo {
    var realm = Realm.getDefaultInstance()

    fun read(): RealmResults<ToDo>? {
        val todos = realm.where<ToDo>().findAll()
        return todos
    }

}