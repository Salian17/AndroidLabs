package com.example.lab1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("HomeActivity", "onCreate called")

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_chats)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ChatAdapter(getFakeChats())
    }

    override fun onResume() {
        super.onResume()
        Log.d("HomeActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HomeActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("HomeActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("HomeActivity", "onRestart called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("HomeActivity", "onStart called")
    }

    private fun getFakeChats(): List<Chat> {
        return listOf(
            Chat("Jane Smith", "See you tomorrow!", "11:15 AM"),
            Chat("John Doe", "Hey, how are you?", "15:30 PM"),
        )
    }
}
