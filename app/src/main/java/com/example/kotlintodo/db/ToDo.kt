package com.example.kotlintodo.db

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class ToDo : RealmObject() {
    @PrimaryKey
    var id: Long = 0

    @Required
    var title: String = ""

    @Index
    var created: Date = Date()
}
