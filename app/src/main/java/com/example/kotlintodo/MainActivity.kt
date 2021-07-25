package com.example.kotlintodo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo.model.EditorMode
import com.example.kotlintodo.realm.ToDoAccessor
import com.google.android.material.floatingactionbutton.FloatingActionButton

var mainActivityContext: Context? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityContext = this

        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        val addButton = findViewById<FloatingActionButton>(R.id.addButton)

//         仮データ作成
//         for (i in 1..20) ToDoAccessor.create("test${i}")

//         全件取得
        val todos = ToDoAccessor.getAll()
        Log.d("todos", todos.toString())

//         id=1 更新
//        ToDoAccessor.update(1, "update test")
//        Log.d("updated todo", ToDoAccessor.find(1).toString())

//         id=20 削除
//         ToDoAccessor.delete(20)
//         Log.d("todos after deleted 20", ToDoAccessor.getAll().toString())

//         存在しない id=21 更新
//         ToDoAccessor.update(21, "no data update test")
//         Log.d("todos after no data updated", ToDoAccessor.getAll().toString())

//         存在しない id=21 削除
//         ToDoAccessor.delete(21)
//         Log.d("todos after no data updated", ToDoAccessor.getAll().toString())

//         存在しない id=21 取得
//         ToDoAccessor.find(21)

//         存在する id=1 登録
//         テスト前にToDoAccessor.createを要修正
//        ToDoAccessor.create("create existed id data")


        val separateLine = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = ToDoListAdapter(application, this, supportFragmentManager, todos)
        list.setHasFixedSize(true)
        list.addItemDecoration(separateLine)

        addButton.setOnClickListener {
            val intent = Intent(application, EditActivity::class.java)
            intent.putExtra("editorMode", EditorMode.CREATE)
            startActivity(intent)
        }
    }
}
