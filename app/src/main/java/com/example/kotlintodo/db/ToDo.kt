package com.example.kotlintodo.db

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class ToDo : RealmObject() {
    @PrimaryKey
    open var id: Long = 0

    @Required
    open var title: String = ""

    @Index
    open var created: Date = Date(System.currentTimeMillis())
}

