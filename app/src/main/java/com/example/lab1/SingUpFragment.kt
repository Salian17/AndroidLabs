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

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("SignUpFragment", "onViewCreated called")

        val nameInput = view.findViewById<EditText>(R.id.name_input)
        val emailInput = view.findViewById<EditText>(R.id.email_input)
        val passwordInput = view.findViewById<EditText>(R.id.password_input)
        val registerButton = view.findViewById<Button>(R.id.button_register)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString("email", email)
                    putString("password", password)
                }

                val signInFragment = SignInFragment().apply {
                    arguments = bundle
                }
                (activity as MainActivity).loadFragment(signInFragment)
            } else {
                Toast.makeText(activity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
