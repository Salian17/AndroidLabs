package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class OnboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboard, container, false)

        Log.d("OnboardFragment", "onViewCreated called")

        val nextButton = view.findViewById<Button>(R.id.button_next)
        nextButton.setOnClickListener {
            (activity as MainActivity).loadFragment(SignInFragment())
        }

        return view
    }
}
