package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("HomeFragment", "onViewCreated called")

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_chats)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ChatAdapter(getFakeChats())
    }

    private fun getFakeChats(): List<Chat> {
        return listOf(
            Chat("Jane Smith", "See you tomorrow!", "11:15 AM"),
            Chat("John Doe", "Hey, how are you?", "15:30 PM"),
        )
    }
}
