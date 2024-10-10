package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        Log.d("SignInActivity", "onCreate called")

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val signInButton = findViewById<Button>(R.id.button_signin)

        val registerButton = findViewById<Button>(R.id.button_register)
        registerButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        signInButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email == "test@example.com" && password == "testpass") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("SingInActivity", "onActivityResult called")

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val user = data?.getParcelableExtra<User>("user")

            findViewById<EditText>(R.id.name_input).setText(user?.name.orEmpty())
            findViewById<EditText>(R.id.email_input).setText(user?.email.orEmpty())
            findViewById<EditText>(R.id.password_input).setText(user?.password.orEmpty())
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("SignInActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SignInActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SignInActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SignInActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SignInActivity", "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SignInActivity", "onRestart called")
    }

    companion object {
        private const val REQUEST_CODE = 1001
    }
}

