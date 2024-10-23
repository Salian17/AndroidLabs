package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signin, container, false)

        Log.d("SignInFragment", "onCreateView called")

        val emailInput = view.findViewById<EditText>(R.id.email_input)
        val passwordInput = view.findViewById<EditText>(R.id.password_input)
        val signInButton = view.findViewById<Button>(R.id.button_signin)
        val registerButton = view.findViewById<Button>(R.id.button_register)

        arguments?.let {
            val email = it.getString("email")
            val password = it.getString("password")

            emailInput.setText(email)
            passwordInput.setText(password)
        }

        registerButton.setOnClickListener {
            (activity as MainActivity).loadFragment(SignUpFragment())
        }

        signInButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (isValidCredentials(email, password)) {
                (activity as MainActivity).loadFragment(HomeFragment())
            } else {
                Toast.makeText(activity, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        return email == "test@example.com" && password == "testpass"
    }
}
