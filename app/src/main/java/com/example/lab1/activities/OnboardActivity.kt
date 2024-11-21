package com.example.lab1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.R

class OnboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        Log.d("OnboardActivity", "onCreate called")

        val startButton = findViewById<Button>(R.id.button_next)
        startButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("OnboardActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("OnboardActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("OnboardActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("OnboardActivity", "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("OnboardActivity", "onRestart called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("OnboardActivity", "onStart called")
    }
}

