package com.example.lab1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        Log.d("SignUpActivity", "onCreate called")

        val nameInput = findViewById<EditText>(R.id.name_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val registerButton = findViewById<Button>(R.id.button_register)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            val user = User(name, email, password)
            val intent = Intent().apply {
                putExtra("user", user)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SignUpActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignUpActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SignUpActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignUpActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignUpActivity", "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SignUpActivity", "onRestart called")
    }
}