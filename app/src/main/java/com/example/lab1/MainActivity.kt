package com.example.lab1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Загружаем OnboardFragment по умолчанию при первом запуске
        if (savedInstanceState == null) {
            loadFragment(OnboardFragment())
        }
    }

    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun navigateToSignIn() {
        loadFragment(SignInFragment())
    }

    fun navigateToSignUp() {
        loadFragment(SignUpFragment())
    }

    fun navigateToHome() {
        loadFragment(HomeFragment())
    }

    fun navigateToOnboard() {
        loadFragment(OnboardFragment())
    }
}
