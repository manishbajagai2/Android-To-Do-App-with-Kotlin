package com.example.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener{ _ ->
            val alertDialog = AlertDialog.Builder(this)
            val textEditText = EditText(this)
            alertDialog.setTitle("Add To Do Items")
            alertDialog.setMessage("Enter items to add")
            alertDialog.setView(textEditText)
            alertDialog.setPositiveButton("Add") { dialog, _ ->
                val todoTitle= textEditText.text.toString()

                if(todoTitle.isNotEmpty()) {
                    val todo = Todo(todoTitle)
                    todoAdapter.addTodo(todo)
                    textEditText.text.clear()
                }else{
                    Toast.makeText(this, "Invalid input. Please try again", Toast.LENGTH_LONG).show()
                }
                    dialog.dismiss()
                    Toast.makeText(this, "Task added and saved", Toast.LENGTH_LONG).show()
                }

                alertDialog.setNeutralButton("Cancel"){ _, _ ->
                    Toast.makeText(applicationContext,"clicked cancel",Toast.LENGTH_LONG).show()
                }
//                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.deleteAll ->{
                todoAdapter.deleteAll()
                true
            }
            R.id.delete_fab ->{
                todoAdapter.deleteDoneTodos()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}